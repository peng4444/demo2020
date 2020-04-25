package cn.pbj.demo2020.fastdfs;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @ClassName: TestFastDFS
 * @Author: pbj
 * @Date: 2020/4/24 21:42
 * @Description: TODO 通过fastDFS的client代码访问tracker和storage
 * 通过client的API代码方便访问tracker和storage，中间走socket协议
 */
public class TestFastDFS {

    //测试文件上传
    @Test
    public void testUpload() {
        //通过fastDFS的client代码访问tracker和storage
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
            metaList[0] = new NameValuePair("fileName", "1.jpg");
            // 执行上传
            String fileId = client.upload_file1("C:\\Users\\74354\\Desktop\\2.jpg", "jpg", metaList);
            System.out.println("upload success. file id is: " + fileId);
            //关闭trackerServer服务的连接
            trackerServer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 查询文件是否存在
    @Test
    public void testQuery() {
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
            // 查询方法 1.组名+远程文件名  2.文件id
            FileInfo fileInfo = client.query_file_info("group1", "M00/00/00/wKhIgF6i8eqADLwBABmB0njghJI356.jpg");
            System.out.println(fileInfo);
            //方法2 查询整个文件id
            FileInfo fileInfo1 = client.query_file_info1("group1/M00/00/00/wKhIgF6i8eqADLwBABmB0njghJI356.jpg");
            System.out.println(fileInfo1);
            // 查询文件上传时的元信息
            NameValuePair[] metadata1 = client.get_metadata1("group1/M00/00/00/wKhIgF6i8eqADLwBABmB0njghJI356.jpg");
            System.out.println(metadata1);
            //关闭trackerServer服务的连接
            trackerServer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 测试fastDFS文件下载
    @Test
    public void testDownLoad() {
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
            //执行下载
            byte[] bytes = client.download_file1("group1/M00/00/00/wKhIgF6i8eqADLwBABmB0njghJI356.jpg");
            //执行文件输出流
            File file = new File("e:/a.jpg");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.close();
            //关闭trackerServer服务的连接
            trackerServer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
