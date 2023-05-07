package com.modagbul.BE.domain.vote.board.repository;

import com.modagbul.BE.domain.vote.board.dto.VoteDto.GetVoteDetailsResponse;
import com.modagbul.BE.domain.vote.board.entity.Vote;

import java.util.Optional;

public interface VoteRepositoryCustom {
    Optional<Vote> findNotClosedByVoteId(Long voteId);
    GetVoteDetailsResponse getVoteDetailByVoteId(Long voteId);
}
