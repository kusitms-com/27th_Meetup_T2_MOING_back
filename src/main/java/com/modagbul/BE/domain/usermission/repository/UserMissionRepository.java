package com.modagbul.BE.domain.usermission.repository;

import com.modagbul.BE.domain.usermission.constant.Status;
import com.modagbul.BE.domain.usermission.dto.UserMissionDetailDto;
import com.modagbul.BE.domain.usermission.dto.UserMissionListDto;
import com.modagbul.BE.domain.usermission.entity.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    @Query(value = "select new com.modagbul.BE.domain.usermission.dto.UserMissionListDto(um.mission.missionId,um.mission.title,um.mission.dueTo,um.status)" +
            "from UserMission um " +
            "where um.team.teamId = :teamId and um.user.userId = :userId")
    Optional<List<UserMissionListDto>> findUserMissionListById(@Param("teamId") Long teamId, @Param("userId") Long userId);

    @Query(value = " select new com.modagbul.BE.domain.usermission.dto.UserMissionDetailDto(um.mission.title,um.mission.dueTo,um.mission.content,um.mission.rule, um.status)" +
            "from UserMission um " +
            "where um.team.teamId = :teamId and um.user.userId = :userId and um.mission.missionId = :missionId")
    Optional<UserMissionDetailDto> findUserMissionDetailById(@Param("teamId") Long teamId, @Param("userId") Long userId, @Param("missionId") Long missionId);


    @Query("select um.status from UserMission um where um.user.userId = :userId and um.team.teamId = :teamId and um.mission.missionId = :missionId")
    Optional<Status> findUserMissionStatusById(@Param("userId") Long userId,@Param("teamId") Long teamId,@Param("missionId") Long missionId);
}