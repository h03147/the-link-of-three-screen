package com.cmkj.the_link_of_threescreen.service.Impl;

import com.cmkj.the_link_of_threescreen.dao.Thelinkofthreescreenuploadbtn1Dao;
import com.cmkj.the_link_of_threescreen.entity.Thelinkofthreescreenuploadbtn1;
import com.cmkj.the_link_of_threescreen.repository.Thelinkofthreescreenuploadbtn1Repository;
import com.cmkj.the_link_of_threescreen.service.Thelinkofthreescreenuploadbtn1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class Thelinkofthreescreenuploadbtn1ServiceImpl implements Thelinkofthreescreenuploadbtn1Service {

    @Autowired
    private Thelinkofthreescreenuploadbtn1Dao thelinkofthreescreenuploadbtn1Dao;

    @Autowired
    private Thelinkofthreescreenuploadbtn1Repository thelinkofthreescreenuploadbtn1Repository;

    // 插入数据库路径方法实现
    @Override
    public int saveFile(Thelinkofthreescreenuploadbtn1 thelinkofthreescreenuploadbtn1) {
        System.out.print("开始插入=getSecondmenu=="+thelinkofthreescreenuploadbtn1.getSecondmenu()+"\n");
        System.out.print("开始插入=getThirdmenu=="+thelinkofthreescreenuploadbtn1.getThirdmenu()+"\n");
        System.out.print("开始插入=getFourthmenu=="+thelinkofthreescreenuploadbtn1.getFourthmenu()+"\n");
        System.out.print("开始插入=getFifthmenu=="+thelinkofthreescreenuploadbtn1.getFifthmenu()+"\n");
        System.out.print("开始插入=getLeftscreencontext=="+thelinkofthreescreenuploadbtn1.getLeftscreencontext()+"\n");
        System.out.print("开始插入=getLeftscreenminiimg=="+thelinkofthreescreenuploadbtn1.getLeftscreenminiimg()+"\n");
        System.out.print("开始插入=getRightscreencontext=="+thelinkofthreescreenuploadbtn1.getRightscreencontext()+"\n");
        System.out.print("开始插入=getRightscreenimg=="+thelinkofthreescreenuploadbtn1.getRightscreenimg()+"\n");
        System.out.print("开始插入=getRightscreenvideo=="+thelinkofthreescreenuploadbtn1.getRightscreenvideo()+"\n");
        System.out.print("开始插入=getLujing=="+thelinkofthreescreenuploadbtn1.getLujing()+"\n");
        int insertResult = thelinkofthreescreenuploadbtn1Dao.saveFile(thelinkofthreescreenuploadbtn1);
        System.out.print("插入结果==="+insertResult+"\n");
        return insertResult;
    }

    // 动态多条件查询实现类
    @Override
    public List<Thelinkofthreescreenuploadbtn1> findByCondition(String secondmenu, String thirdmenu, String fourthmenu, String fifthmenu) {
        return thelinkofthreescreenuploadbtn1Repository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();
            System.out.println("impl内容"+secondmenu+thirdmenu+fourthmenu+thirdmenu);
            // 二级菜单条件是否存在
            if(!StringUtils.isEmpty(secondmenu)) {
                predicates.add(criteriaBuilder.equal(root.get("secondmenu"), secondmenu));
            }

            // 三级级菜单条件是否存在
            if(!StringUtils.isEmpty(thirdmenu)) {
                predicates.add(criteriaBuilder.equal(root.get("thirdmenu"), thirdmenu));
            }

            // 四级菜单条件是否存在
            if(!StringUtils.isEmpty(fourthmenu)) {
                predicates.add(criteriaBuilder.equal(root.get("fourthmenu"), fourthmenu));
            }

            // 五级菜单条件是否存在
            if(!StringUtils.isEmpty(fifthmenu)) {
                predicates.add(criteriaBuilder.equal(root.get("fifthmenu"), fifthmenu));
            }

            System.out.println("impl结果2"+criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction());
            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();

        });
    }
}
