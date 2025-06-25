package order;

import java.util.List;

public interface OrderDAO {
    boolean insertOrder(OrderVO order);
    OrderVO selectOrder(int orderNo);
    List<OrderVO> selectOrdersOfMember(String memberId);
    List<OrderVO> selectAllOrders();
}
