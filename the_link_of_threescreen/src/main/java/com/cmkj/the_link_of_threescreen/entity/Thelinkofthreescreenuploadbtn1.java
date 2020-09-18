package com.cmkj.the_link_of_threescreen.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Thelinkofthreescreenuploadbtn1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String secondmenu;
    private String thirdmenu;
    private String fourthmenu;
    private String fifthmenu;
    private String leftscreenminiimg;
    private String leftscreencontext;
    private String rightscreenimg;
    private String rightscreenvideo;
    private String rightscreencontext;
    private String lujing;
}
