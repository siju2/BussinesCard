package order;

import java.util.Date;
import java.util.List;

import BusinessCard.BusinessCardService;

public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;
    private BusinessCardService businessCardService;

    private final int COMPLETE = 10;

    public OrderServiceImpl(OrderDAO orderDAO, BusinessCardService businessCardService) {
        this.orderDAO = orderDAO;
        this.businessCardService = businessCardService;
    }

    @Override
    public boolean orderItems(OrderVO order) {

        // 1. 주문 정보 추가 (주문일, 배송상태, 배송완료일)
        order.setOrderDate(new Date());
        order.setStatus(COMPLETE);
        order.setDeliverDate(new Date());

        // 2. 명함 템플릿 재고량 update
        for (OrderItemVO item : order.getOrderItemList()) {
            int templateNo = item.getTemplateNo();
            int newStock = businessCardService.detailTemplateInfo(templateNo).getStock() - item.getQuantity();
            if (newStock >= 0) {
                businessCardService.updateTemplateStock(templateNo, newStock);
            } else {
                return false;
            }
        }

        // 3. 주문 정보 DB에 추가
        orderDAO.insertOrder(order);
        return true;
    }

    @Override
    public List<OrderVO> listMyOrders(String memberId) {
        return orderDAO.selectOrdersOfMember(memberId);
    }

    @Override
    public List<OrderVO> listAllOrders() {
        return orderDAO.selectAllOrders();
    }
}
