package com.cmkj.the_link_of_threescreen.service;

import com.cmkj.the_link_of_threescreen.entity.Thelinkofthreescreenuploadbtn1;

import java.util.List;

public interface Thelinkofthreescreenuploadbtn1Service {
    // 上传文件插入路径到数据库
    public int saveFile(Thelinkofthreescreenuploadbtn1 thelinkofthreescreenuploadbtn1);

    // 动态多条件查询
    public List<Thelinkofthreescreenuploadbtn1> findByCondition(String secondmenu, String thirdmenu, String fourthmenu, String fifthmenu);
}
