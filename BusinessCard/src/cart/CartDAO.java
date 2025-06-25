package cart;

import java.util.List;

public interface CartDAO {
    boolean insertCartItem(CartItemVO item);
    CartItemVO selectCartItem(int templateNo);
    List<CartItemVO> selectAllCartItems();
    boolean deleteCartItem(int templateNo);
    boolean clear();
}
