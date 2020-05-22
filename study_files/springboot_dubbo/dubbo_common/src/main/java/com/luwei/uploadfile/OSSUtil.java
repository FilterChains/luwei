package com.luwei.uploadfile;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.internal.OSSHeaders;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.StorageClass;

import java.io.ByteArrayInputStream;

/**
 * <p>@description : 文件上传服务 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/5/14 8:50 </p>
 */
public class OSSUtil {

    public static void main(String[] args) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        final String endpoint = "oss-cn-chengdu.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        final String accessKeyId = "";
        final String accessKeySecret = "";
        final String bucketName = "";
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 创建PutObjectRequest对象。
        String content = "Hello OSS";
        // 包含文件在内的完整路径
        String path = "files/hello.txt";
        // <yourObjectName>表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, path, new ByteArrayInputStream(content.getBytes()));
        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        metadata.setObjectAcl(CannedAccessControlList.PublicRead);
        putObjectRequest.setMetadata(metadata);
        // 上传字符串。
        ossClient.putObject(putObjectRequest);
        // 关闭OSSClient。
        ossClient.shutdown();
        /*能不能展示图片:<img src="http://867285912.oss-cn-chengdu.aliyuncs.com/images/Capture001.png">*/
    }
}
