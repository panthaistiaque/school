package com.istiaque.EVM.model;

import lombok.Data;

import javax.persistence.Column;

/**
 * Created by User on 12/10/2019.
 */
@Data
public class ElectionSchedule {
    @Column(columnDefinition = "DATE", updatable = false)
    private String electionDate;
    @Column(columnDefinition = "DATE", updatable = false)
    private String nominationStartDate;
    @Column(columnDefinition = "DATE", updatable = false)
    private String nominationCloseDate;
    @Column(columnDefinition = "DATE", updatable = false)
    private String resultPublishDate;
}
