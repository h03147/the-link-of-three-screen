package com.cmkj.the_link_of_threescreen.dao;

import com.cmkj.the_link_of_threescreen.entity.Thelinkofthreescreenuploadbtn2;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface Thelinkofthreescreenuploadbtn2Dao {
    // 上传文件插入路径到数据库
    public int saveFile(Thelinkofthreescreenuploadbtn2 thelinkofthreescreenuploadbtn2);
}
