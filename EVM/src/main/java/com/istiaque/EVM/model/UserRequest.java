package com.istiaque.EVM.model;

import com.istiaque.EVM.model.enam.Status;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 27/Oct/2019.
 */
@Data
@Entity
@Table(name = "user_request")
public class UserRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;
    private String fullName;
    @NotNull
    private String email;
    private String phone;
    private String dob;
    @NotNull
    private String bid;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(columnDefinition = "DATE default '2001-01-01'")
    private String requstDate;
    @Column(columnDefinition = "DATE")
    private String actionDate;
}
