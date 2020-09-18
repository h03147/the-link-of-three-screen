package com.cmkj.the_link_of_threescreen.controller;

import com.cmkj.the_link_of_threescreen.entity.Thelinkofthreescreenuploadbtn2;
import com.cmkj.the_link_of_threescreen.repository.Thelinkofthreescreenuploadbtn2Repository;
import com.cmkj.the_link_of_threescreen.util.ResultUtil;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/btn2file")
public class Thelinkofthreescreenuploadbtn2Controller {

    @Autowired
    private Thelinkofthreescreenuploadbtn2Repository thelinkofthreescreenuploadbtn2Repository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(Thelinkofthreescreenuploadbtn1Controller.class);
    //在文件操作中，不用/或者\最好，推荐使用File.separator
    private final static String  fileDir="files";
    private  final static String rootPath = System.getProperty("user.home")+ File.separator+fileDir+File.separator;
    private final static String filePath = "D:\\btn1fileUpload\\"+File.separator+fileDir+File.separator;

    private String btn2rightscreenimg; // 全局右屏图url
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
                        if(UploadFileType ==1)
                        {
                            afterBCryptfileName = imagehead + afterBCryptfileName + suffix;
                        }
                        else
                        {
                            return false;
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

                        btn2rightscreenimg = "http://218.61.208.69:9527/btn1files/"+afterBCryptfileName;

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


    // 查询全部
    @GetMapping("/btn2findAll")
    public List<Thelinkofthreescreenuploadbtn2> findAll() { return thelinkofthreescreenuploadbtn2Repository.findAll(); }

    // 根据 id 查询单条信息
    @GetMapping("/btn2findById/{id}")
    public Thelinkofthreescreenuploadbtn2 filefindById(@PathVariable("id") Integer id) { return thelinkofthreescreenuploadbtn2Repository.findById(id).get(); }

    // 根据 id 修改当前行信息
    @PutMapping("/btn2updateById")
    public String updateById(@RequestBody Thelinkofthreescreenuploadbtn2 thelinkofthreescreenuploadbtn2) {
        System.out.println("前端传来要修改的信息：" + thelinkofthreescreenuploadbtn2);

        if(btn2rightscreenimg != null)
        {
            thelinkofthreescreenuploadbtn2.setRightscreenimg(btn2rightscreenimg);
            thelinkofthreescreenuploadbtn2.setLujing(uploadlujing);
            Thelinkofthreescreenuploadbtn2 btn2updateresult1 = thelinkofthreescreenuploadbtn2Repository.save(thelinkofthreescreenuploadbtn2);

            // 执行完后 清空新上传文件的路径缓存 方便下一次重新判断
            btn2rightscreenimg = null;
            uploadlujing = null;

            if(btn2updateresult1 != null)
            {
                return "success";
            }else
            {
                return "error";
            }
        }else
        {
            Thelinkofthreescreenuploadbtn2 btn2updateresult2 = thelinkofthreescreenuploadbtn2Repository.save(thelinkofthreescreenuploadbtn2);
            if(btn2updateresult2 != null)
            {
                return "success";
            }else
            {
                return "error";
            }
        }

    }

}
