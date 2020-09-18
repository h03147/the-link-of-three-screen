package com.cmkj.the_link_of_threescreen.controller;

import com.cmkj.the_link_of_threescreen.entity.Thelinkofthreescreenuploadbtn1;
import com.cmkj.the_link_of_threescreen.repository.Thelinkofthreescreenuploadbtn1Repository;
import com.cmkj.the_link_of_threescreen.service.Thelinkofthreescreenuploadbtn1Service;
import com.cmkj.the_link_of_threescreen.util.ResultUtil;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.lang.exception.ExceptionUtils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/btn1file")
public class Thelinkofthreescreenuploadbtn1Controller {

    @Autowired
    private Thelinkofthreescreenuploadbtn1Repository thelinkofthreescreenuploadbtn1Repository;

    @Autowired
    private Thelinkofthreescreenuploadbtn1Service thelinkofthreescreenuploadbtn1Service;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(Thelinkofthreescreenuploadbtn1Controller.class);
    //在文件操作中，不用/或者\最好，推荐使用File.separator
    private final static String  fileDir="files";
    private  final static String rootPath = System.getProperty("user.home")+ File.separator+fileDir+File.separator;
    private final static String filePath = "D:\\btn1fileUpload\\"+File.separator+fileDir+File.separator;

    private String btn1leftscreenminiimg; // 全局左屏缩略图url
    private String btn1rightscreenimg; // 全局右屏图url
    private String btn1rightscreenvideo; // 全局右屏视频url
    private String uploadlujing; // 全局上传路径字段

    // 上传文件接口
    @RequestMapping("/upload")
    public Object uploadFile(@RequestParam("file") MultipartFile[] multipartFiles, @RequestParam("UploadFileType") Integer UploadFileType, final HttpServletResponse response, final HttpServletRequest request){
        System.out.println("UploadFileType"+UploadFileType);
        File fileDir = new File(filePath);
        if (!fileDir.exists() && !fileDir.isDirectory()) {
            fileDir.mkdirs();
        }
        try {
            if (multipartFiles != null && multipartFiles.length > 0) {
                for(int i = 0;i<multipartFiles.length;i++){
                    try {
                        // 名字加时间戳
                        String fileName = multipartFiles[i].getOriginalFilename();
                        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
                        System.out.print("（加个时间戳，尽量避免文件名称重复）保存的文件名为: "+fileName+"\n");

                        // 获取扩展名.之后的内容（包括.）
                        String suffix = fileName.substring(fileName.lastIndexOf("."));

                        // 然后对文件名进行hash加密
                        String afterBCryptfileName = bCryptPasswordEncoder.encode(fileName).substring(10);
                        String imagehead = "AV";
                        String videohead = "BV";
                        if(UploadFileType == 0 || UploadFileType ==1)
                        {
                            afterBCryptfileName = imagehead + afterBCryptfileName + suffix;
                        }
                        else
                        {
                            afterBCryptfileName = videohead + afterBCryptfileName + suffix;
                        }
                        afterBCryptfileName = afterBCryptfileName.replace("\\", "ilovejava");
                        afterBCryptfileName = afterBCryptfileName.replace("/", "ilovejava");
                        System.out.println("BCrypt加密后的文件名：" + afterBCryptfileName);


                        //以原来的名称命名,覆盖掉旧的
                        String storagePath = filePath+afterBCryptfileName;
                        logger.info("上传的文件：" + multipartFiles[i].getName() + "," + multipartFiles[i].getContentType() + "," + afterBCryptfileName
                                +"，保存的路径为：" + storagePath);
                        Streams.copy(multipartFiles[i].getInputStream(), new FileOutputStream(storagePath), true);
                        //或者下面的
                        // Path path = Paths.get(storagePath);
                        //Files.write(path,multipartFiles[i].getBytes());



                        // 服务器上的全局路径赋值
                        uploadlujing = storagePath;

                        // 对上传的文件进行分类到数据库不同的字段
                        if(UploadFileType == 0)
                        {
                            btn1leftscreenminiimg = "http://218.61.208.69:9527/btn1files/"+afterBCryptfileName;
                        }else if(UploadFileType == 1)
                        {
                            btn1rightscreenimg = "http://218.61.208.69:9527/btn1files/"+afterBCryptfileName;
                        }else
                        {
                            btn1rightscreenvideo = "http://218.61.208.69:9527/btn1files/"+afterBCryptfileName;
                        }
                    } catch (IOException e) {
                        logger.error(ExceptionUtils.getFullStackTrace(e));
                    }
                }
            }

        } catch (Exception e) {
            return ResultUtil.error(e.getMessage());
        }
        return ResultUtil.success("上传成功!");
    }




    // 给btn1添加一条新记录
    //添加新文件信息
    @PostMapping("/btn1addonefile")
    public String addsystemuser(@RequestBody Thelinkofthreescreenuploadbtn1 thelinkofthreescreenuploadbtn1) {
        System.out.println("前端传过来的东西在这呢！"+ thelinkofthreescreenuploadbtn1);
//        //新增前先查看账号是否已存在，如果已存在就驳回插入操作！
//        User findByusernumberResult = userDao.findByUsernumber(user.getUsernumber());
//        if(findByusernumberResult == null)
//        {
//            System.out.println("1----原始密码"+user.getPassword());
//            String abc = user.getPassword();
//            System.out.println("2----密码加密后"+bCryptPasswordEncoder.encode(abc));
//            user.setPassword(bCryptPasswordEncoder.encode(abc)); //密码加密后插入数据库
            thelinkofthreescreenuploadbtn1.setLeftscreenminiimg(btn1leftscreenminiimg);
            thelinkofthreescreenuploadbtn1.setRightscreenimg(btn1rightscreenimg);
            thelinkofthreescreenuploadbtn1.setRightscreenvideo(btn1rightscreenvideo);
            thelinkofthreescreenuploadbtn1.setLujing(uploadlujing);
        System.out.println("前端传过来的东西经过upload内容赋值后"+ thelinkofthreescreenuploadbtn1);
            Thelinkofthreescreenuploadbtn1 thelinkofthreescreenuploadbtn1Result = thelinkofthreescreenuploadbtn1Repository.save(thelinkofthreescreenuploadbtn1);
            // 插入完后清除使用过的路径
            btn1leftscreenminiimg = null;
            btn1rightscreenimg = null;
            btn1rightscreenvideo = null;
            uploadlujing = null;
            if(thelinkofthreescreenuploadbtn1Result != null)
            {
                return "添加文件信息成功";
            }else
            {
                return "非常抱歉的通知您，添加文件失败! 请重试";
            }
//        }else
//        {
//            return "怎么办~0q0~,该人员账号已经存在了,换一个试试~";
//        }


    }

    // 动态多条件查询
    @PostMapping("/findbycondition")
    public Map<String, Object> findByCondition(@RequestBody Thelinkofthreescreenuploadbtn1 thelinkofthreescreenuploadbtn1) {
        System.out.println("前端传来的多条件查询"+ thelinkofthreescreenuploadbtn1+"隔断"+thelinkofthreescreenuploadbtn1.getSecondmenu());
        Map<String, Object> map = new HashMap<>();
        if(thelinkofthreescreenuploadbtn1.getSecondmenu() == null)
        {
            map.put("ListData", "请先选择查询条件");
        }else
        {
            List<Thelinkofthreescreenuploadbtn1> listresult = thelinkofthreescreenuploadbtn1Service.findByCondition(thelinkofthreescreenuploadbtn1.getSecondmenu(),
                    thelinkofthreescreenuploadbtn1.getThirdmenu(),
                    thelinkofthreescreenuploadbtn1.getFourthmenu(),
                    thelinkofthreescreenuploadbtn1.getFifthmenu());

            System.out.println("返回的list结果"+listresult);
            map.put("ListData", listresult);
        }

        return map;
    }

    // 根据id查询单条记录
    @GetMapping("/filefindById/{id}")
    public Thelinkofthreescreenuploadbtn1 findById(@PathVariable("id") Integer id) { return thelinkofthreescreenuploadbtn1Repository.findById(id).get(); }

    // 根据id修改执行update操作
    @PutMapping("/updatefileById")
    public String updateById(@RequestBody Thelinkofthreescreenuploadbtn1 thelinkofthreescreenuploadbtn1) {
        System.out.println("前端传来的根据id修改数据请求" + thelinkofthreescreenuploadbtn1);

//        thelinkofthreescreenuploadbtn1.setLeftscreenminiimg(btn1leftscreenminiimg);
//        thelinkofthreescreenuploadbtn1.setRightscreenimg(btn1rightscreenimg);
//        thelinkofthreescreenuploadbtn1.setRightscreenvideo(btn1rightscreenvideo);
//        thelinkofthreescreenuploadbtn1.setLujing(uploadlujing);

        if(btn1leftscreenminiimg != null || btn1rightscreenimg != null || btn1rightscreenvideo != null)
        {
            thelinkofthreescreenuploadbtn1.setLeftscreenminiimg(btn1leftscreenminiimg);
            thelinkofthreescreenuploadbtn1.setRightscreenimg(btn1rightscreenimg);
            thelinkofthreescreenuploadbtn1.setRightscreenvideo(btn1rightscreenvideo);
            thelinkofthreescreenuploadbtn1.setLujing(uploadlujing);
            Thelinkofthreescreenuploadbtn1 updateresult1 = thelinkofthreescreenuploadbtn1Repository.save(thelinkofthreescreenuploadbtn1);

            // 执行完后 清空新上传文件的路径缓存 方便下一次重新判断
            btn1leftscreenminiimg = null;
            btn1rightscreenimg = null;
            btn1rightscreenvideo = null;
            uploadlujing = null;
            if(updateresult1 != null)
            {
                return "success";
            }else
            {
                return "error";
            }
        }else
        {
            Thelinkofthreescreenuploadbtn1 updateresult2 = thelinkofthreescreenuploadbtn1Repository.save(thelinkofthreescreenuploadbtn1);
            if(updateresult2 != null)
            {
                return "success";
            }else
            {
                return "error";
            }
        }

    }

    // 删除文件请求
    @DeleteMapping("/deleteByFileId/{id}")
    public void DeleteById(@PathVariable("id") Integer id) {
        System.out.println("前端传来了删除文件记录的请求id：" + id);
        thelinkofthreescreenuploadbtn1Repository.deleteById(id);
    }
}
