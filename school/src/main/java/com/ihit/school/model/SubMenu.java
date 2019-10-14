package com.ihit.school.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 24/Sep/2019.
 */
@Data
@Entity
@Table(name = "config_sub_menu")
public class SubMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String url;
    private String icon;
    @Column(name = "MENU_ID")
    private Integer menuId;
}
