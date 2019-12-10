package com.istiaque.EVM.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by User on 11/16/2019.
 */
@Data
@Entity
@Table(name = "tbl_sub_menu")
public class SubMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subMenuId;
    private String subMenuName;
    private String subMenuUrl;
    private String subMenuIcon;
    private String subMenuOnClick;
}
