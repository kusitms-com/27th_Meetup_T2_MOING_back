package com.modagbul.BE.domain.notice.board.controller;

import com.modagbul.BE.domain.notice.board.constant.NoticeConstant;
import com.modagbul.BE.domain.notice.board.dto.NoticeDto;
import com.modagbul.BE.domain.notice.board.service.NoticeService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Api(tags = "Notice API")
@RequestMapping("/api/v1/{teamId}/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @ApiOperation(value = "공지 생성", notes = "공지를 생성합니다.")
    @PostMapping
    public ResponseEntity<ResponseDto<NoticeDto.CreateNoticeResponse>> createNotice(@PathVariable Long teamId, @Valid @RequestBody NoticeDto.CreateNoticeRequest createNoticeRequest) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), NoticeConstant.ENoticeResponseMessage.CREATE_NOTICE_SUCCESS.getMessage(), noticeService.createNotice(teamId, createNoticeRequest)));
    }

    @ApiOperation(value = "공지 삭제", notes = "공지를 삭제합니다.")
    @DeleteMapping("/{noticeId}")
    public ResponseEntity<ResponseDto> deleteNotice(@PathVariable Long teamId, @PathVariable Long noticeId) {
        this.noticeService.deleteNotice(noticeId);
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), NoticeConstant.ENoticeResponseMessage.DELETE_NOTICE_SUCCESS.getMessage()));
    }

    @ApiOperation(value = "공지 상세 조회", notes = "공지를 상세 조회합니다")
    @GetMapping("/{noticeId}")
    public ResponseEntity<ResponseDto<NoticeDto.GetNoticeDetailsResponse>> getNoticeDetail(@PathVariable Long teamId, @PathVariable Long noticeId) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), NoticeConstant.ENoticeResponseMessage.GET_NOTICE_DETAIL_SUCCESS.getMessage(), this.noticeService.getNoticeDetails(noticeId)));
    }

    //공지 전체 조회

    //공지 수정
}