package com.markloy.code_community.provider;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class OSSProvider {
    @Value("${aliyun.endpoint}")
    private String  endpoint;
    @Value("${aliyun.accessKeyId}")
    private String accessKeyId ;
    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.bucketName}")
    private String bucketName;
    @Value("${aliyun.filePatten}")
    private String filePatten;
    @Value("${aliyun.expiration}")
    private Long expires;

    /**
     * 文件上传
     * @param file 文件
     * @return OSS外网路径
     */
    public URL fileUpload(MultipartFile file) throws IOException {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //对文件名进行处理  \\代表转义
        String[] strings = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String suffix = strings[strings.length - 1];
        String fileName = UUID.randomUUID() + "." + suffix;
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, filePatten + fileName, file.getInputStream());
        ossClient.putObject(putObjectRequest);
        //过期时间
        Date expiration = new Date(System.currentTimeMillis() + expires);
        //获取返回的文件外网路径
        URL url = ossClient.generatePresignedUrl(bucketName, filePatten + fileName, expiration);
        ossClient.shutdown();
        return url;
    }


}
