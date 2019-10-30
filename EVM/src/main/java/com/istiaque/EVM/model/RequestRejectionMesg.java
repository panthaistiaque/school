package com.istiaque.EVM.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 30/Oct/2019.
 */
@Data
@Entity
@Table(name = "user_request_message")
public class RequestRejectionMesg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer requestId;
    private String comment;
}
