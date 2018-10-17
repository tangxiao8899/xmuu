package com.carryit.base.besttmwuu.web;


import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
            String path ="/var/apache-tomcat-8.5.31/webapps/besttmwuu-0.0.1/WEB-INF/classes/static/index_img/";
            ArrayList<String> urls = new ArrayList<>();
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

            List<MultipartFile> fileList = multipartRequest.getFiles("image");
            if(fileList == null || fileList.size() == 0){
                return faild("文件为空",false);
            }
            for(MultipartFile file:fileList){
                String filesName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
                        + (new Random().nextInt(9000) % (9000 - 1000 + 1) + 1000) + ".jpg";
                File tempFile=new File(path, filesName);
                tempFile.createNewFile();
                file.transferTo(tempFile); //到这里tempFile即是上传上来的文件。
                urls.add(domain_name+"/index_img/"+filesName);
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
}

