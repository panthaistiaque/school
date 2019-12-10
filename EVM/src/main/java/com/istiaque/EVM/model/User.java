package com.istiaque.EVM.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 02/Nov/2019.
 */
@Data
@Entity
@Table(name = "tbl_use")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    private String phone;
    private String dob;
    @NotNull
    @Column(unique = true)
    private String userName;
    private String password;
    @Column(columnDefinition = "DATE")
    private String passWordUpdateDate;
    private String token;
    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean isActive;
    @Column(columnDefinition = "DATE default '2001-01-01'")
    private String creationDate;
    private String bid;
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleModel> roleList;

}
