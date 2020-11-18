package cn.pbj.demo2020.rocketmq.consumer;

/**
 * @pClassName: Order
 * @author: pengbingjiang
 * @create: 2020/11/18 10:44
 * @description: TODO
 */
public class Order {
    private String orderId;

    private String address;

    public Order(String orderId, String address) {
        this.orderId = orderId;
        this.address = address;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
