package cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapCartDAO implements CartDAO {

    Map<Integer, CartItemVO> cartDB = new HashMap<>();

    @Override
    public boolean insertCartItem(CartItemVO item) {
        cartDB.put(item.getTemplateNo(), item);
        return true;
    }

    @Override
    public CartItemVO selectCartItem(int templateNo) {
        return cartDB.get(templateNo);
    }

    @Override
    public List<CartItemVO> selectAllCartItems() {
        return new ArrayList<>(cartDB.values());
    }

    @Override
    public boolean deleteCartItem(int templateNo) {
        return cartDB.remove(templateNo) != null;
    }

    @Override
    public boolean clear() {
        cartDB.clear();
        return true;
    }
}
