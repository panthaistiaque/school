package com.istiaque.EVM.model;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by User on 11/20/2019.
 */
@Data
@Entity
@Table(name = "tbl_association")
public class Association implements Comparable<Association> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer associationId;
    private String associationName;
    private String associationAddress;
    @Column(columnDefinition = "LONGTEXT")
    private String associationLogo;
    @Column(columnDefinition = "DATETIME")
    private String creationDate;
    private Integer createdBy;
    @Column(columnDefinition = "DATETIME")
    private String updateDate;
    private Integer updateBy;

//@OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(referencedColumnName = "associationId", name = "assoId")
//    private List<Election> list;

    @Override
    public int compareTo(Association o) {
        return getAssociationId().compareTo(o.getAssociationId());
    }
}
