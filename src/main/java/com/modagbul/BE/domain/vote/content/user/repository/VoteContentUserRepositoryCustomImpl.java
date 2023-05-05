package com.modagbul.BE.domain.vote.content.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.modagbul.BE.domain.vote.content.user.enttiy.QVoteContentUser.voteContentUser;

public class VoteContentUserRepositoryCustomImpl implements VoteContentUserRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public VoteContentUserRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<String> getUsersNickNameByContent(String content) {
        return queryFactory.select(voteContentUser.nickName)
                .from(voteContentUser)
                .where(voteContentUser.content.eq(content))
                .fetch();
    }
}
