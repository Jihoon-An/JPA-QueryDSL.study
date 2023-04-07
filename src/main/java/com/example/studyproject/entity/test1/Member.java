package com.example.studyproject.entity.test1;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Member {
    @Id
    private Long id;
    private Long age;
    private String username;
    @Column(name = "team_id")
    private Long teamId;
}
