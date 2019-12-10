package com.istiaque.EVM.model;

import com.istiaque.EVM.model.enam.Status;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by User on 11/20/2019.
 */
@Data
@Entity
@Table(name = "tbl_election")
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer electionId;
    private String electionCode;
    private String electionName;
    @Column(columnDefinition = "DATE")
    private String validDate;
    @Column(columnDefinition = "DATETIME")
    private String creationDate;
    private Integer createdBy;
    @Column(columnDefinition = "DATETIME")
    private String updateDate;
    private Integer updateBy;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "associationId", name = "assoId")
    private Association association;
    @Enumerated(EnumType.ORDINAL)
    private Status status;
}
