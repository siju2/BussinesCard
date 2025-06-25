package order;

import java.io.Serializable;

public class OrderItemVO implements Serializable {
    private int templateNo;
    private int quantity;
    private int price;

    public OrderItemVO(int templateNo, int quantity, int price) {
        this.templateNo = templateNo;
        this.quantity = quantity;
        this.price = price;
    }

    public int getTemplateNo() {
        return templateNo;
    }

    public void setTemplateNo(int templateNo) {
        this.templateNo = templateNo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "\t***" + templateNo + ", " + quantity + "장, " + price + "원";
    }
}
