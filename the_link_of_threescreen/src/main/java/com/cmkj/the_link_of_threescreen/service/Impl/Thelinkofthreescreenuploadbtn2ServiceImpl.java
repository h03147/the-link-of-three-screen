package com.cmkj.the_link_of_threescreen.service.Impl;

import com.cmkj.the_link_of_threescreen.dao.Thelinkofthreescreenuploadbtn2Dao;
import com.cmkj.the_link_of_threescreen.entity.Thelinkofthreescreenuploadbtn2;
import com.cmkj.the_link_of_threescreen.service.Thelinkofthreescreenuploadbtn2Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Thelinkofthreescreenuploadbtn2ServiceImpl implements Thelinkofthreescreenuploadbtn2Service {

    private Thelinkofthreescreenuploadbtn2Dao thelinkofthreescreenuploadbtn2Dao;

    @Override
    public int saveFile(Thelinkofthreescreenuploadbtn2 thelinkofthreescreenuploadbtn2) {
        System.out.print("开始插入=getSecondmenu=="+thelinkofthreescreenuploadbtn2.getSecondmenu()+"\n");
        System.out.print("开始插入=getLeftscreenurl=="+thelinkofthreescreenuploadbtn2.getLeftscreenurl()+"\n");
        System.out.print("开始插入=getRightscreenimg=="+thelinkofthreescreenuploadbtn2.getRightscreenimg()+"\n");
        System.out.print("开始插入=getRightscreencontext=="+thelinkofthreescreenuploadbtn2.getRightscreencontext()+"\n");
        System.out.print("开始插入=getLujing=="+thelinkofthreescreenuploadbtn2.getLujing()+"\n");
        int insertResult = thelinkofthreescreenuploadbtn2Dao.saveFile(thelinkofthreescreenuploadbtn2);
        System.out.print("插入结果==="+insertResult+"\n");
        return insertResult;
    }
}
