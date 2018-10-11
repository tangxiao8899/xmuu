package com.util;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

public class UploadHelper {

    /**
     * @descrption 根据HttpServletRequest对象获取MultipartFile集合

     * @param request
     * @param maxLength
     *            文件最大限制
     * @param allowExtName
     *            不允许上传的文件扩展名
     * @return MultipartFile集合
     */
    public static List<MultipartFile> getFileSet(HttpServletRequest request,
                                                 long maxLength, String[] allowExtName) {
        MultipartHttpServletRequest multipartRequest = null;
        try {
            multipartRequest = (MultipartHttpServletRequest) request;
        } catch (Exception e) {
            return new LinkedList<MultipartFile>();
        }

        List<MultipartFile> files = new LinkedList<MultipartFile>();
        Iterator<String> fileNames = multipartRequest.getFileNames();
        while(fileNames.hasNext()) {
            files = multipartRequest.getFiles(fileNames.next());
            for (int i = 0; i < files.size(); i++) {
                if (!validateFile(files.get(i), maxLength, allowExtName)) {
                    files.remove(files.get(i));
                    if (files.size() == 0) {
                        return files;
                    }
                }
            }
        }


        // 移除不符合条件的

        return files;
    }

    public static String uploadFile(MultipartFile file, String path)
            throws Exception {

        String filename = file.getOriginalFilename();
        String extName = filename.substring(filename.lastIndexOf("."))
                .toLowerCase();
        String lastFileName = UUID.randomUUID().toString() + extName;
        if (!path.endsWith(File.separator)) {
            path = path + File.separator;
        }
        File temp = new File(path);
        if (!temp.isDirectory()) {
            temp.mkdir();
        }
        // 图片存储的全路径
        String fileFullPath = path + lastFileName;
        FileCopyUtils.copy(file.getBytes(), new File(fileFullPath));
        return fileFullPath;
    }

    /**
     * @descrption 验证文件格式，这里主要验证后缀名

     * @param file
     *            MultipartFile对象
     * @param maxLength
     *            文件最大限制
     * @param allowExtName
     *            不允许上传的文件扩展名
     * @return 文件格式是否合法
     */
    private static boolean validateFile(MultipartFile file, long maxLength,
                                        String[] allowExtName) {
        if (file.getSize() < 0 || file.getSize() > maxLength)
            return false;
        String filename = file.getOriginalFilename();

        // 处理不选择文件点击上传时，也会有MultipartFile对象，在此进行过滤
        if (filename == "") {
            return false;
        }
        String extName = filename.substring(filename.lastIndexOf("."))
                .toLowerCase();
        if (allowExtName == null || allowExtName.length == 0
                || Arrays.binarySearch(allowExtName, extName) != -1) {
            return true;
        } else {
            return false;
        }
    }

}
