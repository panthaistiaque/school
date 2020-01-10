package com.istiaque.EVM.model;

import com.istiaque.EVM.model.enam.Status;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

/**
 * Created by User on 12/26/2019.
 */
@Data
@Entity
@Table(name = "tbl_ballot")
public class Ballot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "candidateId", name = "candidateId")
    private Candidate candidate;
    private Integer counter=0;
    @Enumerated(EnumType.STRING)
    private Status status = Status.OPEN;
    @Enumerated(EnumType.STRING)
    private Status isResultPublish = Status.NO;
}
