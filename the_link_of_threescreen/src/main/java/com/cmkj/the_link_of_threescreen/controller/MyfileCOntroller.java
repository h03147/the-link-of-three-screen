//package com.cmkj.the_link_of_threescreen.controller;
//
//import com.cmkj.the_link_of_threescreen.entity.Thelinkofthreescreenuploadbtn1;
//import com.cmkj.the_link_of_threescreen.entity.Upload;
//import com.cmkj.the_link_of_threescreen.service.Thelinkofthreescreenuploadbtn1Service;
//import com.cmkj.the_link_of_threescreen.service.UploadService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@RestController
//public class MyfileCOntroller {
//
//    @Autowired
//    private UploadService uploadService;
//
//    @Autowired
//    private Thelinkofthreescreenuploadbtn1Service thelinkofthreescreenuploadbtn1Service;
//
//    private String outurl;
//    private String outurl1;
//
//    @RequestMapping(value = "uploadFile", produces = "application/json;charset=UTF-8")
//    public String uploadthreelinkFile(@RequestParam("fileName") MultipartFile file) {
//
//        System.out.print("上传文件==="+"\n");
//        //判断文件是否为空
//        if (file.isEmpty()) {
//            return "上传文件不可为空";
//        }
//        // 获取文件名
//        String fileName = file.getOriginalFilename();
//        // System.out.print("上传的文件名为: "+fileName+"\n");
//
//        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
//        System.out.print("（加个时间戳，尽量避免文件名称重复）保存的文件名为: "+fileName+"\n");
//
//        //加个时间戳，尽量避免文件名称重复
//        String path = "F:/fileUpload/" +fileName;
//        //String path = "E:/fileUpload/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
//        //文件绝对路径
//        System.out.print("保存文件绝对路径"+path+"\n");
//
//        //创建文件路径
//        File dest = new File(path);
//
//        //判断文件是否已经存在
//        if (dest.exists()) {
//            return "文件已经存在";
//        }
//
//        //判断文件父目录是否存在
//        if (!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdir();
//        }
//
//        try {
//            //上传文件
//            file.transferTo(dest); //保存文件
//            System.out.print("保存文件路径"+path+"\n");
//            String url="http://192.168.31.100:8080/images/"+fileName;
//            Upload upload1 = new Upload();
//            upload1.setIndex("3");
//            upload1.setType("right");
//            upload1.setTitle("这是我和我的祖国大电影预告片的标题");
//            upload1.setContext("这是我和我的祖国大电影预告片的内容~");
//            upload1.setUrl(url);
//            upload1.setLujing(path);
//            upload1.setName(fileName);
//            int saveResult= uploadService.saveFile(upload1);
//            System.out.print("插入结果"+saveResult+"\n");
//            System.out.print("保存的完整url===="+url+"\n");
//            outurl = upload1.getUrl();
//
//        } catch (IOException e) {
//            return "上传失败";
//        }
//
//        return "上传成功,文件url=="+outurl;
//
//    }
//
//
//    // 接受前端学校党建里文件的上传请求
//    @RequestMapping(value = "btn1uploadFile", produces = "application/json;charset=UTF-8")
//    public String btn1uploadthreelinkFile(@RequestParam("fileName") MultipartFile file) {
//
//        System.out.print("上传文件==="+"\n");
//        //判断文件是否为空
//        if (file.isEmpty()) {
//            return "上传文件不可为空";
//        }
//        // 获取文件名
//        String fileName = file.getOriginalFilename();
//        // System.out.print("上传的文件名为: "+fileName+"\n");
//
//        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
//        System.out.print("（加个时间戳，尽量避免文件名称重复）保存的文件名为: "+fileName+"\n");
//
//        //加个时间戳，尽量避免文件名称重复
//        String path = "F:/btn1fileUpload/" +fileName;
//        //String path = "E:/fileUpload/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
//        //文件绝对路径
//        System.out.print("保存文件绝对路径"+path+"\n");
//
//        //创建文件路径
//        File dest = new File(path);
//
//        //判断文件是否已经存在
//        if (dest.exists()) {
//            return "文件已经存在";
//        }
//
//        //判断文件父目录是否存在
//        if (!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdir();
//        }
//
//        try {
//            //上传文件
//            file.transferTo(dest); //保存文件
//            System.out.print("保存文件路径"+path+"\n");
//            String url="http://192.168.31.100:8080/btn1images/"+fileName;
//            Thelinkofthreescreenuploadbtn1 thelinkofthreescreenuploadbtn1 = new Thelinkofthreescreenuploadbtn1();
//
//            thelinkofthreescreenuploadbtn1.setRightscreenimg(url);
//            thelinkofthreescreenuploadbtn1.setLujing(path);
//            thelinkofthreescreenuploadbtn1.setRightscreencontext(fileName);
//            int saveResult= thelinkofthreescreenuploadbtn1Service.saveFile(thelinkofthreescreenuploadbtn1);
//            System.out.print("插入结果"+saveResult+"\n");
//            System.out.print("保存的完整url===="+url+"\n");
//            outurl = thelinkofthreescreenuploadbtn1.getRightscreenimg();
//
//        } catch (IOException e) {
//            return "上传失败";
//        }
//
//        return "上传成功,文件url=="+outurl1;
//
//    }
//}
