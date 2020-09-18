package com.cmkj.the_link_of_threescreen.service.Impl;

import com.cmkj.the_link_of_threescreen.dao.Thelinkofthreescreenuploadbtn3Dao;
import com.cmkj.the_link_of_threescreen.entity.Thelinkofthreescreenuploadbtn3;
import com.cmkj.the_link_of_threescreen.repository.Thelinkofthreescreenuploadbtn3Repository;
import com.cmkj.the_link_of_threescreen.service.Thelinkofthreescreenuploadbtn3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class Thelinkofthreescreenuploadbtn3ServiceImpl implements Thelinkofthreescreenuploadbtn3Service {

    @Autowired
    private Thelinkofthreescreenuploadbtn3Dao thelinkofthreescreenuploadbtn3Dao;

    @Autowired
    private Thelinkofthreescreenuploadbtn3Repository thelinkofthreescreenuploadbtn3Repository;

    // 插入数据库路径方法实现
    @Override
    public int saveFile(Thelinkofthreescreenuploadbtn3 thelinkofthreescreenuploadbtn3) {
        System.out.print("开始插入=getSecondmenu=="+thelinkofthreescreenuploadbtn3.getSecondmenu()+"\n");
        System.out.print("开始插入=getLeftscreenminiimg=="+thelinkofthreescreenuploadbtn3.getLeftscreenminiimg()+"\n");
        System.out.print("开始插入=getLeftscreenvideo=="+thelinkofthreescreenuploadbtn3.getLeftscreenvideo()+"\n");
        System.out.print("开始插入=getLeftscreencontext=="+thelinkofthreescreenuploadbtn3.getLeftscreencontext()+"\n");
        System.out.print("开始插入=getRightscreenurl=="+thelinkofthreescreenuploadbtn3.getRightscreenurl()+"\n");
        System.out.print("开始插入=getLujing=="+thelinkofthreescreenuploadbtn3.getLujing()+"\n");
        int insertResult = thelinkofthreescreenuploadbtn3Dao.saveFile(thelinkofthreescreenuploadbtn3);
        System.out.print("插入结果==="+insertResult+"\n");
        return insertResult;
    }

    // 动态多条件查询实现类
    @Override
    public List<Thelinkofthreescreenuploadbtn3> findByCondition(String secondmenu) {
        return thelinkofthreescreenuploadbtn3Repository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();
            System.out.println("impl内容"+secondmenu);
            // 二级菜单条件是否存在
            if(!StringUtils.isEmpty(secondmenu)) {
                predicates.add(criteriaBuilder.equal(root.get("secondmenu"), secondmenu));
            }

            System.out.println("impl结果2"+criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction());
            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();

        });
    }
}
