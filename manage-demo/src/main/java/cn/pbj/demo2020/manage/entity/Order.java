package cn.pbj.demo2020.manage.entity;

import java.util.Date;

/**
 * @pClassName: Order
 * @author: pengbingjiang
 * @create: 2020/7/24 15:08
 * @description: TODO
 */
public class Order {
    private Long id;
    private Long userId;
    private String orderName;
    private String orderPrice;
    private Integer orderSize;
    private Date orderTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getOrderSize() {
        return orderSize;
    }

    public void setOrderSize(Integer orderSize) {
        this.orderSize = orderSize;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
}
