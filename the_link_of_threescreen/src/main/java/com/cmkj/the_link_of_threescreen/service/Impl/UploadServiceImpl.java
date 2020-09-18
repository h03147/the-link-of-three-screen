//package com.cmkj.the_link_of_threescreen.service.Impl;
//
//import com.cmkj.the_link_of_threescreen.dao.UploadDao;
//import com.cmkj.the_link_of_threescreen.entity.Upload;
//import com.cmkj.the_link_of_threescreen.service.UploadService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Transactional
//public class UploadServiceImpl implements UploadService {
//
//    @Autowired
//    private UploadDao uploadDao;
//
//    // 插入数据库路径方法
//    @Override
//    public int saveFile(Upload upload) {
//        System.out.print("开始插入=name=="+upload.getName()+"\n");
//        System.out.print("开始插入=lujing=="+upload.getLujing()+"\n");
//        System.out.print("开始插入=url=="+upload.getUrl()+"\n");
//        System.out.print("开始插入=url=="+upload.getIndex()+"\n");
//        System.out.print("开始插入=url=="+upload.getType()+"\n");
//        System.out.print("开始插入=url=="+upload.getTitle()+"\n");
//        System.out.print("开始插入=url=="+upload.getContext()+"\n");
//        int insertResult = uploadDao.saveFile(upload);
//        System.out.print("插入结果==="+insertResult+"\n");
//        return insertResult;
//    }
//}
