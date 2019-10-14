package com.ihit.school.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 24/Sep/2019.
 */
@Data
@Entity
@Table(name = "tbl_user", indexes = {@Index(name = "tbl_usr_index", columnList = "id, email", unique = true)})
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(nullable = false, length = 60)
    private String firstName;

    @NotNull
    @Column(nullable = false, length = 60)
    private String lastName;

    @NotNull(message = "Give a valid E-mail Address")
    @Email
    @Column(nullable = false, unique = true, length = 100, updatable = false)
    private String email;

    @NotNull(message = "Date of Birth can not be Empty!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, length = 100, updatable = false)
    private Date dob;

    //@NotNull
    @Column(length = 200)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "map_user_role", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private List<Role> roleList;

    @Transient
    private List<Menu> menu;


}
