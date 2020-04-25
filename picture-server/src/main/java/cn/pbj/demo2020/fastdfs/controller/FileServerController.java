package cn.pbj.demo2020.fastdfs.controller;

import cn.pbj.demo2020.fastdfs.entity.FileSystem;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @ClassName: FileServerController
 * @Author: pbj
 * @Date: 2020/4/25 15:10
 * @Description: TODO 文件服务器控制层
 */
@RestController
@RequestMapping("/filesystem")
public class FileServerController {
    //从配置文件获取上传文件的文件夹
    @Value("${fastdfs-file.upload_location}")
    private String upload_location;

    @PostMapping("/upload")
    public FileSystem FileUPLoad(@RequestParam("file") MultipartFile file) throws IOException {
        // 将文件先存储到web服务器上(本机)，在调用fastDFS的client将文件上传到fastDFS服务器
        FileSystem fileSystem = new FileSystem();
        // 获取上传文件的原始名称
        String originalFilename = file.getOriginalFilename();
        // 获取文件扩展名
        String extendName = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 定义文件在web服务器上新的名字
        String fileNewName = UUID.randomUUID() + extendName;
        // 定义file1,使用file1存储上传的文件
        File file1 = new File(upload_location + fileNewName);
        file.transferTo(file1);
        // 获取上传文件的物理路径
        String newFilePath = file1.getAbsolutePath();
        // 通过fastDFS的client代码访问tracker和storage
        try {
            //加载fastDFS客户端的配置文件
            ClientGlobal.initByProperties("config/fastdfs-client.properties");
            System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
            System.out.println("charset=" + ClientGlobal.g_charset);
            // 创建tracker客户端
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            // 定义storage的客户端
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);
            // 定义元信息
            NameValuePair[] metaList = new NameValuePair[1];
            // 定义上传文件名称 可以有多个
            metaList[0] = new NameValuePair("fileName", originalFilename);
            // 执行上传
            String fileId = client.upload_file1(newFilePath, null, metaList);
            System.out.println("upload success. file id is: " + fileId);
            fileSystem.setFileId(fileId);
            fileSystem.setFilePath(fileId);
            fileSystem.setFileName(originalFilename);
            // 通过调用service及dao将文件的路径存储到数据库
            // ...

            //关闭trackerServer服务的连接
            trackerServer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return fileSystem;
    }
}
