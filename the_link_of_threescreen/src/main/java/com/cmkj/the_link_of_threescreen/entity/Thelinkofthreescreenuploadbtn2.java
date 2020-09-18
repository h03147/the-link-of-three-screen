package com.cmkj.the_link_of_threescreen.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Thelinkofthreescreenuploadbtn2 {

    @Id
    private Integer id;
    private String secondmenu;
    private String leftscreenurl;
    private String rightscreenimg;
    private String rightscreencontext;
    private String lujing;
}
