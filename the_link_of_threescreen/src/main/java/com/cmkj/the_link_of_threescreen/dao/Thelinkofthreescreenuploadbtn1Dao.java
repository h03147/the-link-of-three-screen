package com.cmkj.the_link_of_threescreen.dao;

import com.cmkj.the_link_of_threescreen.entity.Thelinkofthreescreenuploadbtn1;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface Thelinkofthreescreenuploadbtn1Dao {
    // 上传文件插入路径到数据库
    public int saveFile(Thelinkofthreescreenuploadbtn1 thelinkofthreescreenuploadbtn1);
}
