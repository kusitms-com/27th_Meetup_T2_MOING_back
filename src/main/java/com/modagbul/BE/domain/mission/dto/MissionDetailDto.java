package com.modagbul.BE.domain.mission.dto;

import com.modagbul.BE.domain.usermission.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MissionDetailDto {
    private String title;
    private String dueTo;
    private String content;
    private String rule;
    private Status status;
}
