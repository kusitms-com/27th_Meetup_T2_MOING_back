package com.modagbul.BE.domain.notice.read.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.modagbul.BE.domain.notice.read.entity.QNoticeRead.noticeRead;

public class NoticeReadRepositoryCustomImpl implements NoticeReadRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public NoticeReadRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<String> getNotReadUsersNickName(Long noticeId) {
        return queryFactory
                .select(noticeRead.user.nickName)
                .from(noticeRead)
                .where(noticeRead.notice.noticeId.eq(noticeId))
                .where(noticeRead.isRead.eq(false))
                .fetch();
    }
}
