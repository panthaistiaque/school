package com.istiaque.EVM.model;

import lombok.Data;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 28/Oct/2019.
 */
@Data
public class LogModel {
    private Integer id;
    private String app;
    private String logger;
    private String ts;
    private String level;
    private String method;
    private String file;
    private String line;
    private String thread;
    private String msg;
    private String stack;
    private String clas;
}
