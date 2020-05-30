package cn.pbj.demo2020.springcloud.rpc.httpclient;

/**
 * @ClassName: Order
 * @Author: pbj
 * @Date: 2020/5/30 10:05
 * @Description: TODO 订单类
 */
public class Order {
    private String id;
    private Double total;
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", total=" + total +
                ", date='" + date + '\'' +
                '}';
    }
}
