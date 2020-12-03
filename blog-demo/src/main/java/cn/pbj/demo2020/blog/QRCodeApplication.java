package cn.pbj.demo2020.blog;

import cn.pbj.demo2020.blog.util.QRCodeUtil;

/**
 * @pClassName: QRCodeApplication
 * @author: pengbingjiang
 * @create: 2020/12/3 19:28
 * @description: TODO 二维码生成类 [Java如何实现二维码](https://blog.csdn.net/qq_43647359/article/details/107119686)
 */
public class QRCodeApplication {
    public static void main(String[] args) throws Exception {
        // 存放在二维码中的内容
        // 二维码中的内容可以是文字，可以是链接等
        String text = "http://www.baidu.com";
        // 嵌入二维码的图片路径
        //String imgPath = "C:\\Users\\Administrator\\Pictures\\img\\dog.jpg";
        // 生成的二维码的路径及名称
        String destPath = "C:\\Users\\bai\\Pictures\\img\\code" + System.currentTimeMillis() + ".jpg";
        //生成二维码
        QRCodeUtil.encode(text, null, destPath, true);
        // 解析二维码
        String str = QRCodeUtil.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);
    }
}
