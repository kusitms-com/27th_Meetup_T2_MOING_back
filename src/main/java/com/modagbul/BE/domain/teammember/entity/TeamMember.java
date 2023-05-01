package com.modagbul.BE.domain.teammember.entity;

import com.modagbul.BE.domain.noticeread.entity.NoticeRead;
import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_member_id")
    private Long teamMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "teamMember")
    private List<NoticeRead> noticeReads = new ArrayList<>();

    public void setTeam(Team team){
        this.team=team;
        team.getTeamMembers().add(this);
    }

    public void setUser(User user){
        this.user=user;
    }

}
