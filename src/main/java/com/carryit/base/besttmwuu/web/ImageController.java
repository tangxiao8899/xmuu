package com.carryit.base.besttmwuu.web;


import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/image")
public class ImageController extends BaseController {

    @Value(value = "${domain_name}")
    private String domain_name;
//
//    //上传图片,先临时保存在/static/index_img下,做测试.后面保存到图片服务器

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject uploadFile(HttpServletRequest request,
                             HttpServletResponse response) {
        try {
            //服务器临时路径
            String path ="/opt/soft/apache-tomcat-8.5.31/webapps/besttmwuu-0.0.1/WEB-INF/classes/static/index_img/";
            String realPath ="/opt/image/";
            //本地路径临时路径
//            String path = "E:\\";
//            String realPath ="D:\\";
            ArrayList<String> urls = new ArrayList<>();
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

            List<MultipartFile> fileList = multipartRequest.getFiles("image");
            if(fileList == null || fileList.size() == 0){
                return faild("文件为空",false);
            }
            for(MultipartFile file:fileList){
                if(file.getSize()>4*1024*1024){
                    return faild("单张图片不能大于4M",false);
                }
            }
            for(MultipartFile file:fileList){

                //临时文件
                String filesName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
                        + (new Random().nextInt(9000) % (9000 - 1000 + 1) + 1000) + ".jpg";
                File tempFile=new File(path, filesName);


                tempFile.createNewFile();
                file.transferTo(tempFile); //到这里tempFile即是上传上来的文件。
                //图片压缩,小余50kb不压缩
                if(file.getSize()<60*1024){
                    File newtempFile=new File(realPath, filesName);
                    Thumbnails.of(tempFile).scale(1f).toFile(newtempFile);
                    urls.add(domain_name+"/images/"+filesName);
                    tempFile.delete();
                }else if(file.getSize()<500*1024) {
                    File newtempFile=new File(realPath, filesName);
                    Thumbnails.of(tempFile).scale(0.7f).toFile(newtempFile);
                    urls.add(domain_name+"/images/"+filesName);
                    tempFile.delete();
                }else if(file.getSize()<1.5*1024*1024) {
                    File newtempFile=new File(realPath, filesName);
                    Thumbnails.of(tempFile).scale(0.43f).toFile(newtempFile);
                    urls.add(domain_name+"/images/"+filesName);
                    tempFile.delete();
                }else {
                    File newtempFile=new File(realPath, filesName);
                    Thumbnails.of(tempFile).scale(0.35f).toFile(newtempFile);
                    urls.add(domain_name+"/images/"+filesName);
                    tempFile.delete();
                }

            }
                return doObjResp(urls);
        } catch (Exception e) {
            e.printStackTrace();
            return faild("上传失败",false);

        }
    }

    @Override
    public JSONObject runTask(String json, int cmd) {
        return null;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize("20480KB");
        /// 总上传数据大小
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }

}

