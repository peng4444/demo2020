package cn.pbj.demo2020.common.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @pClassName: Teacher
 * @author: pengbingjiang
 * @create: 2020/7/2 09:02
 * @description: TODO
 */
public class Teacher {
    /**
     * 老师的主键
     */
    private Integer teacherId;
    /**
     * 名字
     */
    @Excel(name="老师名字",height = 20,width = 30,isImportField = "true_st")
    private String teacherName;
    /**
     * 头像图片地址
     */
    @Excel(name = "老师图片")
    private String teacherImage;
    /**
     * 老师的状态 0代表正常 1代表删除
     * replace 值的替换
     */
    @Excel(name = "老师状态",replace = {"正常_0","冻结_1"})//
    private Integer teacherStatus;

    public Teacher() {
    }

    public Teacher(Integer teacherId, String teacherName, String teacherImage, Integer teacherStatus) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherImage = teacherImage;
        this.teacherStatus = teacherStatus;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherImage() {
        return teacherImage;
    }

    public void setTeacherImage(String teacherImage) {
        this.teacherImage = teacherImage;
    }

    public Integer getTeacherStatus() {
        return teacherStatus;
    }

    public void setTeacherStatus(Integer teacherStatus) {
        this.teacherStatus = teacherStatus;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", teacherImage='" + teacherImage + '\'' +
                ", teacherStatus=" + teacherStatus +
                '}';
    }
}
