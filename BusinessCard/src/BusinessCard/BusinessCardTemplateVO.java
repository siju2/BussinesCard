package BusinessCard;

import java.io.Serializable;
import java.sql.Date;

public class BusinessCardTemplateVO implements Serializable {
    private int templateNo;
    private String name;
    private String designCode;
    private String brand;
    private int price;
    private int stock;

    private Date regdate;

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public BusinessCardTemplateVO() {}

    public BusinessCardTemplateVO(String name, String designCode, String brand, int price, int stock) {
        this.name = name;
        this.designCode = designCode;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
    }

    // getter/setter
    public int getTemplateNo() {
        return templateNo;
    }

    public void setTemplateNo(int templateNo) {
        this.templateNo = templateNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignCode() {
        return designCode;
    }

    public void setDesignCode(String designCode) {
        this.designCode = designCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s (%s) - 브랜드: %s, 가격: %,d원, 재고: %d",
            templateNo, name, designCode, brand, price, stock);
    }
}

