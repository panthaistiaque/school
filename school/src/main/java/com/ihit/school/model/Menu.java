package com.ihit.school.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 24/Sep/2019.
 */
@Data
@Entity
@Table(name = "config_menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String url;
    private String icon;
    private Integer menuOrder;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "menuId")
    private List<SubMenu> menuList;
}
