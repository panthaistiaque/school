package com.istiaque.EVM.model;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by User on 12/8/2019.
 */
@Data
@Entity
@Table(name = "tbl_voter_list")
public class VoterList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Voter ID is not empty")
    @Column(unique = true)
    private Long voterNo;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "electionId", name = "electionCode")
    private Election election;
    @NotNull(message = "Do logout and Login aging")
    private Integer userId;
    @Column(columnDefinition = "DATETIME")
    private String createOn;
}