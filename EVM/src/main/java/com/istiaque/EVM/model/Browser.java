package com.istiaque.EVM.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 27/Oct/2019.
 */
@Data
@Entity
@Table(name = "browser")
public class Browser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String registrationNo;
    private String userAgent;
    private String clientIpAddress;
    private String clientOS;
    private String clientBrowser;
    @ElementCollection(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @CollectionTable(name = "browser_user")
    private List<String> userName;
}
