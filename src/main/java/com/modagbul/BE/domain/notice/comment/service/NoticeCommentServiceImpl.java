package com.modagbul.BE.domain.notice.comment.service;

import com.modagbul.BE.domain.notice.board.entity.Notice;
import com.modagbul.BE.domain.notice.board.service.validate.NoticeValidationService;
import com.modagbul.BE.domain.notice.comment.dto.NoticeCommentDto.CreateNoticeCommentRequest;
import com.modagbul.BE.domain.notice.comment.dto.NoticeCommentDto.CreateNoticeCommentResponse;
import com.modagbul.BE.domain.notice.comment.dto.NoticeCommentDto.GetNoticeCommentResponse;
import com.modagbul.BE.domain.notice.comment.dto.NoticeCommentMapper;
import com.modagbul.BE.domain.notice.comment.entity.NoticeComment;
import com.modagbul.BE.domain.notice.comment.exception.NotFoundNoticeCommentIdException;
import com.modagbul.BE.domain.notice.comment.exception.NotNoticeCommentWriterException;
import com.modagbul.BE.domain.notice.comment.repsitory.NoticeCommentRepository;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class NoticeCommentServiceImpl implements NoticeCommentService{

    private final NoticeCommentRepository noticeCommentRepository;
    private final NoticeCommentMapper noticeCommentMapper;
    private final NoticeValidationService noticeValidationService;

    @Override
    public CreateNoticeCommentResponse createNoticeComment(Long teamId, Long noticeId, CreateNoticeCommentRequest createNoticeCommentRequest) {
        NoticeComment noticeComment=noticeCommentMapper.toEntity(teamId, noticeId, createNoticeCommentRequest);
        noticeCommentRepository.save(noticeComment);
        return new CreateNoticeCommentResponse(noticeComment.getNoticeCommentId());
    }

    @Override
    public void deleteNoticeComment(Long teamId, Long noticeId, Long noticeCommentId) {
        NoticeComment noticeComment=validateNoticeComment(teamId, noticeId, noticeCommentId);
        validateUser(SecurityUtils.getLoggedInUser(),noticeComment);
        noticeComment.deleteNoticeComment();
    }

    @Override
    public List<GetNoticeCommentResponse> getAllNoticeCommentByNoticeId(Long teamId, Long noticeId) {
        Notice notice=noticeValidationService.validateNotice(teamId, noticeId);
        List<NoticeComment> noticeComments=noticeCommentRepository.findAllCommentsByNoticeId(noticeId);
        List<GetNoticeCommentResponse> result=new ArrayList<>();
        Map<Long, GetNoticeCommentResponse> map=new HashMap<>();
        noticeComments.stream().forEach(c->{
            GetNoticeCommentResponse getNoticeCommentResponse=noticeCommentMapper.toDto(c);
            map.put(getNoticeCommentResponse.getNoticeCommentId(), getNoticeCommentResponse);
            result.add(getNoticeCommentResponse);
        });
        return result;
    }

    /**
     * NoticeComment 유효성 체크 메서드
     * @param noticeCommentId
     * @return
     */

    @Override
    public NoticeComment validateNoticeComment(Long teamId, Long noticeId, Long noticeCommentId) {
        noticeValidationService.validateNotice(teamId, noticeId);
        return this.noticeCommentRepository.findNotDeletedByCommentId(noticeCommentId).orElseThrow(()->new NotFoundNoticeCommentIdException());
    }


    /**
     * 댓글을 작성한 유저인지 확인하는 메서드
     * @param user
     * @param noticeComment
     */
    private void validateUser(User user, NoticeComment noticeComment){
        if(noticeComment.getUser().getUserId()!=user.getUserId())
            throw new NotNoticeCommentWriterException();
    }


}
