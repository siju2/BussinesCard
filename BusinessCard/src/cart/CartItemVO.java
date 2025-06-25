package cart;

public class CartItemVO {

    private int templateNo;
    private int quantity;

    public CartItemVO(int templateNo, int quantity) {
        this.templateNo = templateNo;
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return "[" + templateNo + "번 명함, " + quantity + "장]";
    }
}
