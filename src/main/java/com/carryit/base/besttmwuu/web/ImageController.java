package com.carryit.base.besttmwuu.web;


import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.base.ResultPojo;
import com.bean.req.BoardReq;
import com.carryit.base.besttmwuu.entity.*;
import com.carryit.base.besttmwuu.service.GlobounService;
import com.carryit.base.besttmwuu.service.MemberService;
import com.carryit.base.besttmwuu.service.SincerityService;
import com.mysql.jdbc.StringUtils;
import com.util.UploadHelper;
import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Decoder;

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
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            if(fileMap == null || fileMap.size() == 0){
                return faild("文件为空",false);
            }
            Collection<MultipartFile> files = fileMap.values();
            for(MultipartFile file:files){
                File tempFile = getTmpFile();
                        tempFile.createNewFile();
                        file.transferTo(tempFile); //到这里tempFile即是上传上来的文件。
                    }
        } catch (Exception e) {
            e.printStackTrace();
            return faild("上传失败",false);

        }
        return null;
    }

    public File getTmpFile() {
       String path ="/var/apache-tomcat-8.5.31/webapps/besttmwuu-0.0.1/WEB-INF/classes/static/index_img/";

        String filesName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
                        + (new Random().nextInt(9000) % (9000 - 1000 + 1) + 1000) + ".jpg";
        return new File(path, filesName);
    }

    @Override
    public JSONObject runTask(String json, int cmd) {
        return null;
    }
}

