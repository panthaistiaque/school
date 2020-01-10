package com.istiaque.EVM.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by User on 12/27/2019.
 */
@Data
@Entity
@Table(name = "tbl_voter_monitoring")
public class VoterTurnout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String electionCode;
    private Long voterNo;
    private Integer userId;
    @Column(columnDefinition = "DATETIME")
    private String votingTime;

}
