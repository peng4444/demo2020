package cn.pbj.demo2020.book.springinaction4.chapter12;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * @pClassName: Order
 * @author: pengbingjiang
 * @create: 2020/6/29 11:27
 * @description: TODO
 */
@Document
public class Order {

    @Id
    private String id;

    @Field("customer")
    private String customer;

    private String type;

    private Collection<Item> items = new LinkedHashSet<Item>();

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }

    public String getId() {
        return id;
    }

}
