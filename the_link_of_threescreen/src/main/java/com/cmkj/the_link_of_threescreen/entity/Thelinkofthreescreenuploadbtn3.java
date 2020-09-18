package com.cmkj.the_link_of_threescreen.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Thelinkofthreescreenuploadbtn3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String secondmenu;
    private String leftscreenminiimg;
    private String leftscreenvideo;
    private String leftscreencontext;
    private String rightscreenurl;
    private String lujing;
}
