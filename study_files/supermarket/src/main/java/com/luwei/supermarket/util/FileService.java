package com.luwei.supermarket.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.UUID;

@Component
public class FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Value("uploadDir")
    private String uploadDir;

    /**
     * <p>@description : 文件上传  </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/5/23 16:00 </p>
     *
     * @param file
     * @return 路径
     **/
    public Notify<String> uploadImage(MultipartFile file) throws RuntimeException {
        if (file.isEmpty()) {
            return new Notify<>(Notify.Code.ERROR, "文件不能为空");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = uploadDir;
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            logger.info("上传成功后的文件路径未：" + filePath + fileName);
            return new Notify<>(Notify.Code.SUCCESS, fileName);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return new Notify<>(Notify.Code.ERROR, "文件不能为空");
    }

    /**
     * <p>@description : 文件下载相关代码  </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/5/23 16:01 </p>
     *
     * @param imageName
     * @param request
     * @param response
     **/
    public Notify<String> downloadImage(String imageName, HttpServletRequest request, HttpServletResponse response) {
        //String fileName = "123.JPG";
        logger.debug("the imageName is : " + imageName);
        String fileUrl = uploadDir + imageName;
        if (StringUtils.isNotBlank(fileUrl)) {
            //当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
           /* String realPath = request.getServletContext().getRealPath(
                    "//WEB-INF//");*/
            /*File file = new File(realPath, fileName);*/
            File file = new File(fileUrl);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + imageName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return new Notify<>(Notify.Code.SUCCESS, "SUCCESS");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return new Notify<>(Notify.Code.ERROR, "ERROR");
    }

    /**
     * <p>@description : 多文件上传 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/5/23 16:07 </p>
     *
     * @param files
     * @return
     **/
    public Notify<String> uploadFiles(MultipartFile[] files) {
        StringBuilder result = new StringBuilder();
        try {
            for (MultipartFile file : files) {
                if (Objects.nonNull(file)) {
                    //调用上传方法
                    String fileName = executeUpload(file);
                    result.append(fileName).append(";");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Notify<>(Notify.Code.ERROR, "文件上传失败");
        }
        return new Notify<>(Notify.Code.SUCCESS, result.toString());
    }

    /**
     * <p>@description : 提取上传方法为公共方法 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/5/23 16:07 </p>
     *
     * @param file
     * @return
     **/
    private String executeUpload(MultipartFile file) throws Exception {
        //文件后缀名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //上传文件名
        String fileName = UUID.randomUUID() + suffix;
        //服务端保存的文件对象
        File serverFile = new File(uploadDir + fileName);
        // 检测是否存在目录
        if (!serverFile.getParentFile().exists()) {
            serverFile.getParentFile().mkdirs();
        }
        //将上传的文件写入到服务器端文件内
        file.transferTo(serverFile);
        return fileName;
    }

}
