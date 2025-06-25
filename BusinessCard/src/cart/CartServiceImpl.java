package cart;

import java.util.List;

public class CartServiceImpl implements CartService {

    private CartDAO cartDAO;

    public CartServiceImpl(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    @Override
    public boolean addItem2Cart(CartItemVO item) {
        return cartDAO.insertCartItem(item);
    }

    @Override
    public CartItemVO getCartItemInfo(int templateNo) {
        return cartDAO.selectCartItem(templateNo);
    }

    @Override
    public List<CartItemVO> listCartItems() {
        return cartDAO.selectAllCartItems();
    }

    @Override
    public boolean isCartEmpty() {
        return cartDAO.selectAllCartItems().isEmpty();
    }

    @Override
    public boolean removeCartItem(int templateNo) {
        return cartDAO.deleteCartItem(templateNo);
    }

    @Override
    public boolean clearCart() {
        return cartDAO.clear();
    }
}
