package com.istiaque.EVM.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by User on 11/16/2019.
 */
@Data
@Entity
@Table(name = "tbl_role")
public class RoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
    private String roleName;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_menu", joinColumns = @JoinColumn(name = "roleId"), inverseJoinColumns = @JoinColumn(name = "menuId"))
    private List<Menu> menuList;
}
