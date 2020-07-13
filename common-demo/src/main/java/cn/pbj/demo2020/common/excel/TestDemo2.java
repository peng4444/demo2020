package cn.pbj.demo2020.common.excel;

import cn.pbj.demo2020.common.entity.DemoData;
import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @pClassName: TestDemo2
 * @author: pengbingjiang
 * @create: 2020/7/13 14:51
 * @description: TODO easyexcel测试
 */
public class TestDemo2 {
    String PATH = "C:\\Users\\pengbingjiang\\Desktop\\";

    private List<DemoData> data() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }

    /**
     * 最简单的写
     * 1. 创建excel对应的实体对象 参照{@link DemoData}
     * 2. 直接写即可
     */
    @Test
    public void simpleWrite() {
        // 写法1
        String fileName = PATH + "EasyTest.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());

    }
}
