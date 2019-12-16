package com.istiaque.EVM.model;

import com.istiaque.EVM.model.enam.Status;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by User on 12/11/2019.
 */
@Data
@Entity
@Table(name = "tbl_candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer candidateId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "voterNo")
    private VoterList voterList;
    private String name;
    @Column(columnDefinition = "LONGTEXT")
    private String image;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Long supporterId;
    @Column(columnDefinition = "DATETIME")
    private String supporterApproveDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "electionId", name = "election_id")
    private Election election;
    @Column(columnDefinition = "DATETIME")
    private String createOn;
}
