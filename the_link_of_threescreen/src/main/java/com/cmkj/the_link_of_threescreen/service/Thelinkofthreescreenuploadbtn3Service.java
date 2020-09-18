package com.cmkj.the_link_of_threescreen.service;

import com.cmkj.the_link_of_threescreen.entity.Thelinkofthreescreenuploadbtn3;

import java.util.List;

public interface Thelinkofthreescreenuploadbtn3Service {
    // 上传文件插入路径到数据库
    public int saveFile(Thelinkofthreescreenuploadbtn3 thelinkofthreescreenuploadbtn3);

    // 动态多条件查询
    public List<Thelinkofthreescreenuploadbtn3> findByCondition(String secondmenu);
}
