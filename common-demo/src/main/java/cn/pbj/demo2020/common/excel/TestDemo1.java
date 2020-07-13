package cn.pbj.demo2020.common.excel;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.pbj.demo2020.common.entity.Teacher;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @pClassName: TestDemo1
 * @author: pengbingjiang
 * @create: 2020/7/2 09:04
 * @description: TODO Excel导入导出
 */
public class TestDemo1 {
    /**
     * easypoi导出
     */
    @Test
    public void test1() throws IOException {
//        模拟数据
        List<Teacher> list = new ArrayList<>();
        list.add(new Teacher(1,"李老师","hhh.jpg",1));
        list.add(new Teacher(2,"李老师","hhh.jpg",1));
        list.add(new Teacher(3,"李老师","hhh.jpg",1));
        list.add(new Teacher(4,"李老师","hhh.jpg",1));
        list.add(new Teacher(5,"李老师","hhh.jpg",1));
        list.add(new Teacher(6,"李老师","hhh.jpg",1));
        /**
         * 导出参数对象
         * 参数1 标题
         * 参数2 表的名字
         */
        ExportParams exportParams = new ExportParams("所有老师数据","teacher");
        /**
         * exportExcel 导出Excel文件
         * 参数1 导出参数对象
         * 参数2 要导出的实体类的类对象
         * 参数3 要导出的数据 需要一个集合  数据库查询出来的老师对象的集合
         * 返回值就是封装好的文件对象
         */
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, Teacher.class, list);

        workbook.write(new FileOutputStream("C:\\Users\\pengbingjiang\\Desktop\\teachers.xls"));
    }

    /**
     * easypoi导入
     */
    @Test
    public void test5() throws Exception {
        File file = new File("C:/Users/pengbingjiang/Desktop/");
        if (file.exists()) {
            file.mkdirs();
        }
        FileInputStream inputStream = new FileInputStream("C:/Users/pengbingjiang/Desktop/teachers.xls");
        /**
         * ImportParams 导入参数对象
         * 定义标题栏和表头数据
         */
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(1);
        /**
         * importExcel 导入方法
         * 参数1 流 读取要导入的文件
         * 参数2 要导入的实体类的类对象  上师对象的类对象
         * 参数3 导入参数对象
         * 返回值 导入数据 直接封装为集合对象
         */
        try {
            List<Teacher> teachers = ExcelImportUtil.importExcel(inputStream, Teacher.class, importParams);
            for (Teacher teacher : teachers) {
                System.out.println(teacher.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}