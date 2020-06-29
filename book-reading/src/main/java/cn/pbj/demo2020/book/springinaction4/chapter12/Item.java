package cn.pbj.demo2020.book.springinaction4.chapter12;

/**
 * @pClassName: Item
 * @author: pengbingjiang
 * @create: 2020/6/29 11:28
 * @description: TODO
 */
public class Item {

    private Long id;

    private Order order;

    private String product;

    private double price;

    private int quantity;

    public Order getOrder() {
        return order;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

}
