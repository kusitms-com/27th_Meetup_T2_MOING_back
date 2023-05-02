package com.modagbul.BE.domain.mission.dto;

import com.modagbul.BE.domain.usermission.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MissionListDto {
    private Long missionId;
    private String title;
    private String dueTo;
    private Status status;
}