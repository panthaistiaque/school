package com.istiaque.EVM.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by User on 11/16/2019.
 */
@Data
@Entity
@Table(name = "tbl_menu")
public class Menu implements Comparable<Menu> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuId;
    private String menuName;
    private String menuUrl;
    private String menuIcon;
    private String menuOnClick;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "menuId", name ="menuId" )
    private List<SubMenu> subMenus;

    @Override
    public int compareTo(Menu o) {
        return getMenuId().compareTo(o.getMenuId());
    }
}
