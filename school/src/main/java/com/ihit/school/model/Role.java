package com.ihit.school.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 24/Sep/2019.
 */
@Data
@Entity
@Table(name = "tbl_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String roleName;

//    @ManyToMany( fetch = FetchType.LAZY, mappedBy = "roleList")
//    private List<User> userList;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "map_role_menu", joinColumns = @JoinColumn(name = "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "MENU_ID"))
    private List<Menu> menuList;
}
