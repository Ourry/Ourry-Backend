package com.bluewhaletech.Ourry.application.article;

import com.bluewhaletech.Ourry.domain.*;
import com.bluewhaletech.Ourry.domain.article.*;
import com.bluewhaletech.Ourry.domain.article.exception.CategoryNotFoundException;
import com.bluewhaletech.Ourry.domain.article.exception.QuestionAlreadyAnsweredException;
import com.bluewhaletech.Ourry.domain.article.exception.QuestionLoadingFailedException;
import com.bluewhaletech.Ourry.domain.article.exception.QuestionNotFoundException;
import com.bluewhaletech.Ourry.domain.article.repository.QuestionJpaRepository;
import com.bluewhaletech.Ourry.domain.article.repository.QuestionRepository;
import com.bluewhaletech.Ourry.domain.article.repository.ReplyJpaRepository;
import com.bluewhaletech.Ourry.domain.member.Member;
import com.bluewhaletech.Ourry.domain.member.exception.MemberNotFoundException;
import com.bluewhaletech.Ourry.domain.member.repository.MemberJpaRepository;
import com.bluewhaletech.Ourry.domain.article.repository.ReplyRepository;
import com.bluewhaletech.Ourry.domain.poll.PollChoice;
import com.bluewhaletech.Ourry.domain.poll.Poll;
import com.bluewhaletech.Ourry.domain.poll.Solution;
import com.bluewhaletech.Ourry.domain.poll.exception.AnswerToOneselfException;
import com.bluewhaletech.Ourry.domain.poll.exception.PollChoiceNotFoundException;
import com.bluewhaletech.Ourry.domain.poll.exception.PollNotFoundException;
import com.bluewhaletech.Ourry.domain.poll.exception.SolutionNotFoundException;
import com.bluewhaletech.Ourry.domain.poll.repository.*;
import com.bluewhaletech.Ourry.infrastructure.jwt.JwtProvider;
import com.bluewhaletech.Ourry.presentation.article.dto.*;
import com.bluewhaletech.Ourry.presentation.poll.SolutionDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ArticleService {
    private final JwtProvider tokenProvider;
    private final MemberJpaRepository memberJpaRepository;
    private final QuestionRepository questionRepository;
    private final QuestionJpaRepository questionJpaRepository;
    private final PollChoiceRepository choiceRepository;
    private final PollChoiceJpaRepository choiceJpaRepository;
    private final PollRepository pollRepository;
    private final PollJpaRepository pollJpaRepository;
    private final SolutionRepository solutionRepository;
    private final SolutionJpaRepository solutionJpaRepository;
    private final ReplyRepository replyRepository;
    private final ReplyJpaRepository replyJpaRepository;
    private final EnumManagement enumManagement;

    @Autowired
    public ArticleService(JwtProvider tokenProvider, MemberJpaRepository memberJpaRepository, QuestionRepository questionRepository, QuestionJpaRepository questionJpaRepository, PollChoiceRepository choiceRepository, PollChoiceJpaRepository choiceJpaRepository, PollRepository pollRepository, PollJpaRepository pollJpaRepository, SolutionRepository solutionRepository, SolutionJpaRepository solutionJpaRepository, ReplyRepository replyRepository, ReplyJpaRepository replyJpaRepository, EnumManagement enumManagement) {
        this.tokenProvider = tokenProvider;
        this.memberJpaRepository = memberJpaRepository;
        this.questionRepository = questionRepository;
        this.questionJpaRepository = questionJpaRepository;
        this.choiceRepository = choiceRepository;
        this.choiceJpaRepository = choiceJpaRepository;
        this.pollRepository = pollRepository;
        this.pollJpaRepository = pollJpaRepository;
        this.solutionRepository = solutionRepository;
        this.solutionJpaRepository = solutionJpaRepository;
        this.replyRepository = replyRepository;
        this.replyJpaRepository = replyJpaRepository;
        this.enumManagement = enumManagement;
    }

    public List<QuestionDTO> getQuestionList() {
        List<Question> questions = Optional.ofNullable(questionRepository.findAll())
                .orElseThrow(() -> new QuestionLoadingFailedException("질문 목록을 불러오는 과정에서 오류가 발생했습니다."));
        return bringQuestionList(questions);
    }

    public List<QuestionDTO> getQuestionList(int categoryId) {
        /* 카테고리 존재유무 확인 */
        if(categoryId < 0 || categoryId >= ArticleCategory.values().length) {
            throw new CategoryNotFoundException("잘못된 카테고리 유형입니다. 다시 확인해주세요.");
        }
        ArticleCategory category = enumManagement.getArticleCategoryMap().get(categoryId);
        List<Question> questions = Optional.ofNullable(questionJpaRepository.findByCategory(category))
                .orElseThrow(() -> new QuestionLoadingFailedException("질문 목록을 불러오는 과정에서 오류가 발생했습니다."));
        return bringQuestionList(questions);
    }

    public QuestionDetailDTO getQuestionDetail(String accessToken, int questionId) {
        /* Access Token으로부터 이메일 가져오기 */
        String email = tokenProvider.getTokenSubject(accessToken.substring(7));

        /* 회원 존재유무 확인 */
        Member member = Optional.ofNullable(memberJpaRepository.findByEmail(email))
                .orElseThrow(() -> new MemberNotFoundException("회원 정보가 존재하지 않습니다."));

        /* 질문 존재유무 확인 */
        Question question = Optional.ofNullable(questionRepository.findOne((long) questionId))
                .orElseThrow(() -> new QuestionNotFoundException("질문 정보가 존재하지 않습니다."));

        /* 회원 투표유무 확인 */
        char polled = 'A'; // 작성자(A)
        if(member == null) {
            polled = 'N'; // 미투표(N)
        } else if(!questionJpaRepository.existsByMemberAndQuestionId(member, (long) questionId)) {
            polled = pollJpaRepository.existsByMemberAndQuestion(member, question) ? 'Y' : 'N'; // 투표(Y), 미투표(N)
        }

        /* 질문별 선택지 데이터 목록 */
        List<ChoiceDTO> choices = new ArrayList<>();
        for(PollChoice choice : question.getChoices()) {
            ChoiceDTO c = ChoiceDTO.builder()
                    .sequence(choice.getSequence())
                    .detail(choice.getDetail())
                    .count(pollJpaRepository.countByQuestionAndChoice(question, choice))
                    .build();
            choices.add(c);
        }

        /* 질문별 투표 데이터 목록 */
        List<Poll> polls = Optional.ofNullable(pollJpaRepository.findByQuestion(question))
                .orElseThrow(() -> new PollNotFoundException("투표 데이터가 존재하지 않습니다."));

        /* 투표별 솔루션 데이터 목록 */
        List<SolutionDTO> solutions = new ArrayList<>();

        int replyCnt = 0;
        for(Poll poll : polls) {
            /* 솔루션 존재여부 검증 */
            Solution solution = Optional.ofNullable(solutionJpaRepository.findByPoll(poll))
                    .orElseThrow(() -> new SolutionNotFoundException("존재하지 않는 솔루션입니다."));
            /* 솔루션별 답글 데이터 목록 */
            List<ReplyDTO> replies = new ArrayList<>();
            for(Reply reply : replyJpaRepository.findBySolution(solution)) {
                if(Optional.ofNullable(reply).isPresent()) {
                    ReplyDTO r = ReplyDTO.builder()
                            .replyId(reply.getReplyId())
                            .sequence(reply.getSolution().getPoll().getChoice().getSequence())
                            .comment(reply.getComment())
                            .nickname(reply.getMember().getNickname())
                            .createdAt(reply.getCreatedAt())
                            .build();
                    replies.add(r);
                    replyCnt++;
                }
            }
            SolutionDTO s = SolutionDTO.builder()
                    .solutionId(solution.getPoll().getPollId())
                    .sequence(poll.getChoice().getSequence())
                    .opinion(solution.getOpinion())
                    .createdAt(poll.getCreatedAt())
                    .memberId(poll.getMember().getMemberId())
                    .nickname(poll.getMember().getNickname())
                    .replies(replies)
                    .build();
            solutions.add(s);
        }

        /* 비회원과 미응답자는 솔루션 및 답글 정보 확인 불가 */
        int solutionCnt = solutions.size();
        if(polled == 'N') {
            solutions = null;
        }
        return QuestionDetailDTO.builder()
                .title(question.getTitle())
                .content(question.getContent())
                .category(question.getCategory().getName())
                .memberId(question.getMember().getMemberId())
                .nickname(question.getMember().getNickname())
                .polled(polled)
                .pollCnt(polls.size())
                .responseCnt(solutionCnt+replyCnt)
                .createdAt(question.getCreatedAt())
                .choices(choices)
                .solutions(solutions)
                .build();
    }

    public List<QuestionDTO> searchQuestionList(String searchKeyword) {
        List<Question> questions = Optional.ofNullable(questionJpaRepository.searchQuestionList(searchKeyword))
                .orElseThrow(() -> new QuestionLoadingFailedException("질문 목록을 불러오는 과정에서 오류가 발생했습니다."));
        return bringQuestionList(questions);
    }

    @Transactional
    public void addQuestion(String accessToken, QuestionRegistrationDTO dto) {
        /* Access Token으로부터 이메일 가져오기 */
        String email = tokenProvider.getTokenSubject(accessToken.substring(7));

        /* 회원 존재유무 확인 */
        Member member = Optional.ofNullable(memberJpaRepository.findByEmail(email))
                .orElseThrow(() -> new MemberNotFoundException("회원 정보가 존재하지 않습니다."));

        /* 카테고리 존재유무 확인 */
        if(dto.getCategoryId() < 0 || dto.getCategoryId() >= ArticleCategory.values().length) {
            throw new CategoryNotFoundException("잘못된 카테고리 유형입니다. 다시 확인해주세요.");
        }
        ArticleCategory category = enumManagement.getArticleCategoryMap().get(dto.getCategoryId());

        Question question = Question.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .member(member)
                .category(category)
                .build();
        questionRepository.save(question);

        for(ChoiceDTO item : dto.getChoices()) {
            PollChoice choice = PollChoice.builder()
                    .sequence(item.getSequence())
                    .detail(item.getDetail())
                    .question(question)
                    .build();
            choiceRepository.save(choice);
        }
    }

    @Transactional
    public void answerQuestion(String accessToken, QuestionResponseDTO dto) {
        /* Access Token으로부터 이메일 가져오기 */
        String email = tokenProvider.getTokenSubject(accessToken.substring(7));

        /* 회원 존재유무 확인 */
        Member member = Optional.ofNullable(memberJpaRepository.findByEmail(email))
                .orElseThrow(() -> new MemberNotFoundException("회원 정보가 존재하지 않습니다."));

        /* 질문 존재유무 확인 */
        Question question = Optional.ofNullable(questionRepository.findOne(dto.getQuestionId()))
                .orElseThrow(() -> new QuestionNotFoundException("질문 정보가 존재하지 않습니다."));

        /* 자문자답여부 확인 */
        if(email.equals(question.getMember().getEmail())) {
            throw new AnswerToOneselfException("본인이 질문한 글에는 의견을 제출할 수 없습니다.");
        }

        /* 선택지 존재유무 확인 */
        if(!choiceJpaRepository.existsByQuestionAndSequence(question, dto.getSequence())) {
            throw new PollChoiceNotFoundException("선택지 정보가 존재하지 않습니다.");
        }

        /* 답변 작성유무 확인 */
        if(pollJpaRepository.existsByMemberAndQuestion(member, question)) {
            throw new QuestionAlreadyAnsweredException("해당 질문에 대해 답변한 기록이 존재합니다.");
        }

        /* 질문 정보와 응답자가 선택한 시퀀스로 선택지 정보 가져오기 */
        PollChoice choice = Optional.ofNullable(choiceRepository.findByQuestionAndSequence(question, dto.getSequence()))
                .orElseThrow(() -> new PollChoiceNotFoundException("선택지 정보를 불러오는 과정에서 오류가 발생했습니다."));
        /* 투표 정보 생성 및 저장 */
        Poll poll = Poll.builder()
                .member(member)
                .question(question)
                .choice(choice)
                .build();
        pollRepository.save(poll);

        String opinion = dto.getOpinion();
        if(opinion != null) {
            /* FCM */
            try {
                /* 의견을 제출했다면 솔루션 정보 생성 및 저장 */
                solutionRepository.save(Solution.builder()
                        .opinion(opinion)
                        .poll(poll)
                        .build());

            } catch (Exception e) {
                log.error("FCM Service Error: {}", e.getMessage());
            }
        }
    }

    @Transactional
    public void addReply(String accessToken, ReplyRegistrationDTO dto) {
        /* Access Token으로부터 이메일 가져오기 */
        String email = tokenProvider.getTokenSubject(accessToken.substring(7));

        /* 회원 존재유무 확인 */
        Member member = Optional.ofNullable(memberJpaRepository.findByEmail(email))
                .orElseThrow(() -> new MemberNotFoundException("답글을 작성한 회원 정보가 존재하지 않습니다."));

        /* 투표 정보 확인 */
        Poll poll = Optional.ofNullable(pollJpaRepository.findByPollId(dto.getSolutionId()))
                .orElseThrow(() -> new PollNotFoundException("답글을 작성한 투표 정보가 존재하지 않습니다."));

        /* 솔루션 존재유무 확인 */
        Solution solution = Optional.ofNullable(solutionJpaRepository.findByPoll(poll))
                .orElseThrow(() -> new SolutionNotFoundException("답글을 작성한 솔루션 정보가 존재하지 않습니다."));

        /* 답글 정보 생성 및 저장 */
        Reply reply = Reply.builder()
                .comment(dto.getComment())
                .solution(solution)
                .member(member)
                .build();
        replyRepository.save(reply);
    }

    private List<QuestionDTO> bringQuestionList(List<Question> questions) {
        List<QuestionDTO> list = new ArrayList<>();
        for(Question question : questions) {
            /* 질문별 투표 데이터 목록 */
            List<Poll> polls = Optional.ofNullable(pollJpaRepository.findByQuestion(question))
                    .orElseThrow(() -> new PollNotFoundException("투표 데이터를 불러오는 과정에서 오류가 발생했습니다."));

            /* 질문별 솔루션 총합 & 솔루션별 답글 총합 */
            int replyCnt = 0;
            int solutionCnt = 0;
            for(Poll poll : polls) {
                if(solutionJpaRepository.existsByPoll(poll)) {
                    Solution solution = solutionJpaRepository.findByPoll(poll);
                    replyCnt += replyJpaRepository.countBySolution(solution);
                    solutionCnt++;
                }
            }

            QuestionDTO dto = QuestionDTO.builder()
                    .questionId(question.getQuestionId())
                    .title(question.getTitle())
                    .content(question.getContent())
                    .nickname(question.getMember().getNickname())
                    .createdAt(question.getCreatedAt())
                    .pollCnt(polls.size())
                    .responseCnt(solutionCnt+replyCnt)
                    .build();
            list.add(dto);
        }
        return list;
    }
}