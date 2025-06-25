package app;

import java.util.ArrayList;
import java.util.List;

import BusinessCard.BusinessCardService;
import BusinessCard.BusinessCardTemplateVO;
import BusinessCard.CWBusinessCardService;
import cart.CartItemVO;
import cart.CartService;
import cart.CartServiceImpl;
import cart.HashMapCartDAO;
import file.ObjFileHashMapBusinessCardDAO;
import member.CWMemberService;
import member.MemberService;
import member.MemberVO;
import member.ObjFileHashMapMemberDAO;
import order.ObjFileHashMapOrderDAO;
import order.OrderItemVO;
import order.OrderService;
import order.OrderServiceImpl;
import order.OrderVO;

public class BusinessCardConsoleApp {

    String[] startMenuList = { "종료", "명함 템플릿 목록", "로그인", "회원 가입" };
    String[] adminMenuList = { "로그아웃", "명함 템플릿 목록", "명함 템플릿 등록", "명함 템플릿 수정", "명함 템플릿 삭제",
            "회원 목록", "주문 목록" };
    String[] memberMenuList = { "로그아웃", "명함 템플릿 목록", "명함 주문", "주문 목록", "장바구니 명함 담기", "장바구니 보기",
            "내 정보" };
    String[] cartMenuList = { "돌아가기", "명함 주문", "명함 삭제", "장바구니 비우기" };
    String[] myInfoMenuList = { "돌아가기", "비밀번호 변경", "회원 탈퇴" };

    final String ADMIN_ID = "admin";
    final String ADMIN_PWD = "1234";
    final String ADMIN_NAME = "관리자";

    final String CONFIRM = "yes";

    BusinessCardService bcs = new CWBusinessCardService(new ObjFileHashMapBusinessCardDAO());
    MemberService ms = new CWMemberService(new ObjFileHashMapMemberDAO());
    OrderService os = new OrderServiceImpl(new ObjFileHashMapOrderDAO(), bcs);
    CartService cs = new CartServiceImpl(new HashMapCartDAO());
    MemberVO loggedMember;

    MyAppReader input = new MyAppReader();

    public static void main(String[] args) {
        BusinessCardConsoleApp app = new BusinessCardConsoleApp();
        app.displayWelcome();
        app.controlStartMenu();
    }

    private void displayWelcome() {
        System.out.println("***********************************");
        System.out.println("*  Welcome to ChaeWoo BusinessCard Service  *");
        System.out.println("***********************************");
    }

    private void controlStartMenu() {
        int menu;
        do {
            menu = selectMenu(startMenuList);

            switch (menu) {
            case 1:
                menuTemplateList();
                break;
            case 2:
                menuLogin();
                break;
            case 3:
                menuSignUp();
                break;
            case 0:
                menuExit();
                break;
            default:
                menuWrongNumber();
            }

        } while (menu != 0);

    }

    private int selectMenu(String[] menuList) {
        System.out.println("-------------------------------");
        for (int i = 1; i < menuList.length; i++) {
            System.out.println(i + ". " + menuList[i]);
        }
        System.out.println("0. " + menuList[0]);
        System.out.println("-------------------------------");
        return input.readInt(">> 메뉴 선택 : ");
    }

    private void menuSignUp() {
        System.out.println("*** 회원 가입 ***");
        String id = input.readString(">> id : ");
        String password = input.readString(">> password : ");
        String username = input.readString(">> username : ");

        if (ms.registMember(new MemberVO(id, password, username))) {
            System.out.println("회원 가입이 완료되었습니다. 서비스 이용을 위한 로그인 해주세요.");
        } else {
            System.out.println("회원 가입에 실패하였습니다.");
        }
    }

    private void menuWrongNumber() {
        System.out.println("없는 메뉴입니다.");
    }

    private void menuExit() {
        System.out.println("ChaeWoo BusinessCard 서비스를 종료합니다.");
    }

    private void menuTemplateList() {
        System.out.println("*** 명함 템플릿 목록 ***");
        displayTemplateList();
    }

    private void displayTemplateList() {
        List<BusinessCardTemplateVO> templateList = bcs.listTemplates();

        if (templateList.isEmpty()) {
            System.out.println("명함 템플릿을 소개합니다.");
            System.out.println("샘플 명함 템플릿 10가지를 추가합니다.");

            bcs.registTemplate(new BusinessCardTemplateVO("클래식 블랙", "DES001", "ChaeWoo", 1500, 30));
            bcs.registTemplate(new BusinessCardTemplateVO("모던 화이트", "DES002", "ChaeWoo", 1600, 25));
            bcs.registTemplate(new BusinessCardTemplateVO("심플 그레이", "DES003", "ChaeWoo", 1400, 20));
            bcs.registTemplate(new BusinessCardTemplateVO("비즈니스 블루", "DES004", "ChaeWoo", 1800, 15));
            bcs.registTemplate(new BusinessCardTemplateVO("럭셔리 골드", "DES005", "ChaeWoo", 2500, 10));
            bcs.registTemplate(new BusinessCardTemplateVO("크리에이티브 레드", "DES006", "ChaeWoo", 1700, 18));
            bcs.registTemplate(new BusinessCardTemplateVO("친환경 그린", "DES007", "ChaeWoo", 1600, 22));
            bcs.registTemplate(new BusinessCardTemplateVO("테크 블루", "DES008", "ChaeWoo", 1900, 12));
            bcs.registTemplate(new BusinessCardTemplateVO("프레쉬 오렌지", "DES009", "ChaeWoo", 1500, 20));
            bcs.registTemplate(new BusinessCardTemplateVO("에코 네이처", "DES010", "ChaeWoo", 1550, 17));

            templateList = bcs.listTemplates();
        }

        System.out.println("---------------------------------------");
        for (BusinessCardTemplateVO template : templateList) {
            System.out.printf("[%d] %s (%s) - 브랜드: %s, 가격: %,d원, 재고: %d\n",
                template.getTemplateNo(), template.getName(), template.getDesignCode(),
                template.getBrand(), template.getPrice(), template.getStock());
            System.out.println("이미지:");
            displayTemplateDesign(template);
            System.out.println("---------------------------------------");
        }
    }

    private void displayTemplateDesign(BusinessCardTemplateVO template) {
        String code = template.getDesignCode();
        int price = template.getPrice();

        switch (code) {
            case "DES001":
                System.out.println("██████████████████████████████");
                System.out.println("█    클래식 블랙 - 골드 텍스트    █");
                System.out.println("█      가격: " + price + "원           █");
                System.out.println("██████████████████████████████");
                break;
            case "DES002":
                System.out.println("██████████████████████████████");
                System.out.println("█      모던 화이트 심플 로고      █");
                System.out.println("█      가격: " + price + "원           █");
                System.out.println("██████████████████████████████");
                break;
            case "DES003":
                System.out.println("██████████████████████████████");
                System.out.println("█      심플 그레이 미니멀 디자인    █");
                System.out.println("█      가격: " + price + "원           █");
                System.out.println("██████████████████████████████");
                break;
            case "DES004":
                System.out.println("██████████████████████████████");
                System.out.println("█   비즈니스 블루 직선과 곡선    █");
                System.out.println("█      가격: " + price + "원           █");
                System.out.println("██████████████████████████████");
                break;
            case "DES005":
                System.out.println("██████████████████████████████");
                System.out.println("█    럭셔리 골드 고급스러운 느낌   █");
                System.out.println("█      가격: " + price + "원           █");
                System.out.println("██████████████████████████████");
                break;
            case "DES006":
                System.out.println("██████████████████████████████");
                System.out.println("█  크리에이티브 레드 역동적 레이아웃 █");
                System.out.println("█      가격: " + price + "원           █");
                System.out.println("██████████████████████████████");
                break;
            case "DES007":
                System.out.println("██████████████████████████████");
                System.out.println("█    친환경 그린 잎사귀 아이콘    █");
                System.out.println("█      가격: " + price + "원           █");
                System.out.println("██████████████████████████████");
                break;
            case "DES008":
                System.out.println("██████████████████████████████");
                System.out.println("█   테크 블루 디지털 느낌 그라데이션 █");
                System.out.println("█      가격: " + price + "원           █");
                System.out.println("██████████████████████████████");
                break;
            case "DES009":
                System.out.println("██████████████████████████████");
                System.out.println("█    프레쉬 오렌지 산뜻한 디자인   █");
                System.out.println("█      가격: " + price + "원           █");
                System.out.println("██████████████████████████████");
                break;
            case "DES010":
                System.out.println("██████████████████████████████");
                System.out.println("█     에코 네이처 자연 요소 일러스트  █");
                System.out.println("█      가격: " + price + "원           █");
                System.out.println("██████████████████████████████");
                break;
            default:
                System.out.println("디자인 정보를 찾을 수 없습니다.");
                break;
        }
    }


    private void menuLogin() {
        System.out.println("*** 로그인 ***");
        String id = input.readString(">> id : ");
        String password = input.readString(">> password : ");

        // 관리자 -> 관리자 메뉴
        if (id.equals(ADMIN_ID) && password.equals(ADMIN_PWD)) {
            loggedMember = new MemberVO(ADMIN_ID, ADMIN_PWD, ADMIN_NAME);
            System.out.println("관리자 모드로 변경합니다.");
            controlAdminMenu();
        } else {
            // 회원 -> 회원 메뉴
            loggedMember = ms.login(id, password);

            if (loggedMember != null) {
                System.out.println("[로그인] " + loggedMember.getUsername() + "님 안녕하세요.");
                controlMemberMenu();
            } else {
                System.out.println("로그인을 하지 못했습니다.");
            }
        }

    }

    private void controlMemberMenu() {
        int menu;
        do {
            menu = selectMenu(memberMenuList);
            switch (menu) {
            case 1:
                menuTemplateList();
                break;
            case 2:
                menuCardOrder();
                break;
            case 3:
                menuOrderList();
                break;
            case 4:
                menuAddCard2Cart();
                break;
            case 5:
                menuCartView();
                break;
            case 6:
                menuMyInfo();
                break;
            case 0:
                menuLogout();
                break;
            default:
                menuWrongNumber();
            }
        } while (menu != 0);

    }

    private void menuCardOrder() {
        System.out.println("*** 명함 주문 ***");
        displayAvailableTemplateList();
        int templateNo = input.readInt(">> 명함 템플릿 번호 : ");
        BusinessCardTemplateVO template = bcs.detailTemplateInfo(templateNo);

        if (template == null) {
            System.out.println("없는 명함 템플릿입니다.");
            return;
        }

        int quantity = input.readInt(">> 주문량 : ");
        List<OrderItemVO> orderItemList = new ArrayList<>();
        int price = template.getPrice() * quantity;
        orderItemList.add(new OrderItemVO(templateNo, quantity, price));

        OrderVO order = new OrderVO(loggedMember.getId(), orderItemList, price);

        setDeliveryInfo();
        order.setMobile(loggedMember.getMobile());
        order.setAddress(loggedMember.getAddress());

        if (os.orderItems(order)) {
            System.out.println("주문이 완료되었습니다.");
            System.out.println("배송이 완료되었습니다.");
        } else {
            System.out.println("주문을 하지 못했습니다.");
        }
    }

    private void setDeliveryInfo() {
        if (loggedMember.getMobile() == null) {
            System.out.println("*** 배송 정보 입력 ***");

            String mobile = input.readString(">> 모바일 번호 : ");
            String email = input.readString(">> 이메일 주소 : ");
            String address = input.readString(">> 주소 : ");

            loggedMember.setMobile(mobile);
            loggedMember.setEmail(email);
            loggedMember.setAddress(address);

            ms.addMemberInfo(loggedMember.getId(), mobile, email, address);
        }
    }

    private void displayAvailableTemplateList() {
        List<BusinessCardTemplateVO> templateList = bcs.listTemplates();
        System.out.println("---------------------------------------");
        if (templateList.isEmpty()) {
            System.out.println("주문 가능한 명함 템플릿이 없습니다.");
        } else {
            int count = 0;
            for (BusinessCardTemplateVO template : templateList) {
                if (template.getStock() > 0) {
                    System.out.println(template);
                    count++;
                }
            }
            if (count == 0)
                System.out.println("주문 가능한 명함 템플릿이 없습니다.");
        }
        System.out.println("---------------------------------------");
    }

    private void menuAddCard2Cart() {
        System.out.println("*** 장바구니에 명함 담기 ***");

        displayAvailableTemplateList();
        int templateNo = input.readInt(">> 명함 템플릿 번호 : ");
        BusinessCardTemplateVO template = bcs.detailTemplateInfo(templateNo);

        if (template == null) {
            System.out.println("없는 명함 템플릿입니다.");
            return;
        }

        int quantity = input.readInt(">> 주문량 : ");
        if (quantity > template.getStock()) {
            System.out.println("주문량이 재고량보다 큽니다.");
            return;
        }

        if (cs.getCartItemInfo(templateNo) == null) {
            cs.addItem2Cart(new CartItemVO(templateNo, quantity));
            System.out.println("장바구니에 추가했습니다.");
        } else {
            System.out.println("이미 장바구니에 있는 명함입니다.");
        }
    }

    private void menuCartView() {
        System.out.println("*** 장바구니 보기 ***");
        displayCartItemList();

        if (!cs.isCartEmpty())
            controlCartMenu();

    }

    private void displayCartItemList() {
        if (cs.isCartEmpty()) {
            System.out.println("장바구니가 비어 있습니다.");
        } else {
            System.out.println("---------------------------------------");
            for (CartItemVO item : cs.listCartItems()) {
                System.out.println(item);
            }
            System.out.println("---------------------------------------");
        }
    }

    private void controlCartMenu() {
        int menu;
        do {
            menu = selectMenu(cartMenuList);
            switch (menu) {
            case 1:
                menuCartOrder();
                break;
            case 2:
                menuCartCardDelete();
                break;
            case 3:
                menuCartClear();
                break;
            case 0:
                break;
            default:
                menuWrongNumber();
            }
        } while (menu != 0 && !cs.isCartEmpty());
    }

    private void menuCartOrder() {
        System.out.println("*** 장바구니 명함 주문 ***");
        displayCartItemList();

        List<OrderItemVO> orderItemList = new ArrayList<>();
        int totalPrice = 0;
        for (CartItemVO item : cs.listCartItems()) {
            BusinessCardTemplateVO template = bcs.detailTemplateInfo(item.getTemplateNo());
            totalPrice += template.getPrice() * item.getQuantity();
            orderItemList.add(new OrderItemVO(item.getTemplateNo(), item.getQuantity(),
                    template.getPrice() * item.getQuantity()));
        }

        OrderVO order = new OrderVO(loggedMember.getId(), orderItemList, totalPrice);

        setDeliveryInfo();
        order.setMobile(loggedMember.getMobile());
        order.setAddress(loggedMember.getAddress());

        displayOrderInfo(order);

        String confirm = input.readString(">> 위와 같은 내용을 주문 및 결제를 진행하시겠습니까? ('"
                + CONFIRM + "'이면 주문 실행) : ");
        if (confirm.equals(CONFIRM)) {
            if (os.orderItems(order)) {
                cs.clearCart();
                System.out.println("주문이 완료되었습니다.");
                System.out.println("배송이 완료되었습니다.");
            } else {
                System.out.println("주문을 하지 못했습니다.");
            }
        } else {
            System.out.println("주문이 취소되었습니다.");
        }

    }

    private void displayOrderInfo(OrderVO order) {
        System.out.println(order);

    }

    private void menuCartCardDelete() {
        System.out.println("*** 장바구니 명함 삭제 ***");
        displayCartItemList();
        int templateNo = input.readInt(">> 명함 템플릿 번호 :");
        CartItemVO item = cs.getCartItemInfo(templateNo);
        if (item == null) {
            System.out.println("없는 명함입니다.");
        } else {
            cs.removeCartItem(templateNo);
            System.out.println("장바구니에서 명함을 삭제하였습니다.");
        }
        displayCartItemList();
    }

    private void menuCartClear() {
        System.out.println("*** 장바구니 비우기 ***");
        String confirm = input.readString(">> 장바구니의 모든 명함을 삭제하시겠습니까? ('" + CONFIRM + "'이면 삭제) : ");
        if (confirm.equals(CONFIRM)) {
            cs.clearCart();
            System.out.println("장바구니의 모든 명함을 삭제하였습니다.");
        } else {
            System.out.println("장바구니 비우기가 취소되었습니다.");
        }

    }

    private void menuMyInfo() {
        System.out.println("*** 내 정보 ***");
        System.out.println(loggedMember);

        controlMyInfoMenu();
    }

    private void controlMyInfoMenu() {
        int menu;
        do {
            menu = selectMenu(myInfoMenuList);
            switch (menu) {
            case 1:
                menuUpdatePassword();
                break;
            case 2:
                menuMemberExit();
                break;
            case 0:
                break;
            default:
                menuWrongNumber();
            }
        } while (menu != 0 && loggedMember != null);

    }

    private void menuUpdatePassword() {
        System.out.println("*** 비밀번호 수정 ***");
        String oldPassword = input.readString(">> 기존 비밀번호 : ");
        String newPassword = input.readString(">> 새 비밀번호 : ");
        if (ms.updatePassword(loggedMember.getId(), oldPassword, newPassword)) {
            System.out.println("[비밀번호 수정] 비밀번호를 수정했습니다.");
        } else {
            System.out.println("[비밀번호 수정 실패] 비밀번호 수정에 실패했습니다.");
        }
    }

    private void menuMemberExit() {
        System.out.println("*** 회원 탈퇴 ***");
        String password = input.readString(">> 비밀번호 : ");
        if (ms.removeMember(loggedMember.getId(), password)) {
            System.out.println("[회원 탈퇴] 회원정보, 주문정보를 삭제하였습니다. 그동안 서비스를 이용해 주셔서 감사합니다.");
            loggedMember = null;
        } else {
            System.out.println("[회원 탈퇴 실패] 회원 탈퇴 처리에 실패했습니다.");
        }
    }

    private void controlAdminMenu() {
        int menu;
        do {
            menu = selectMenu(adminMenuList);
            switch (menu) {
            case 1:
                menuTemplateList();
                break;
            case 2:
                menuTemplateRegist();
                break;
            case 3:
                menuTemplateUpdate();
                break;
            case 4:
                menuTemplateRemove();
                break;
            case 5:
                menuMemberList();
                break;
            case 6:
                menuOrderList();
                break;
            case 0:
                menuLogout();
                break;
            default:
                menuWrongNumber();
            }

        } while (menu != 0 && loggedMember != null);

    }

    private void menuTemplateRegist() {

        System.out.println("*** 명함 템플릿 등록 ***");
        String name = input.readString(">> 명함 이름 : ");
        String designCode = input.readString(">> 디자인 코드 : ");
        String brand = input.readString(">> 브랜드 : ");
        int price = input.readInt(">> 가격 : ");
        int stock = input.readInt(">> 재고량 : ");

        if (bcs.registTemplate(new BusinessCardTemplateVO(name, designCode, brand, price, stock))) {
            System.out.println("명함 템플릿을 등록했습니다.");
            displayTemplateList();
        } else {
            System.out.println("명함 템플릿 등록에 실패했습니다.");
        }

    }

    private void menuTemplateUpdate() {
        System.out.println("*** 명함 템플릿 정보 수정 ***");
        displayTemplateList();
        int templateNo = input.readInt(">> 명함 템플릿 번호 :");

        int select = input.readInt(">> 수정할 정보 선택 (1. 가격, 2. 재고량) : ");
        if (select == 1) { // 가격
            int price = input.readInt(">> 새 가격 : ");
            if (bcs.updateTemplatePrice(templateNo, price)) {
                System.out.println("[명함 템플릿 정보 수정] 가격을 수정하였습니다.");
            } else {
                System.out.println("[명함 템플릿 정보 수정 오류] 없는 명함 템플릿입니다.");
            }

        } else if (select == 2) {// 재고량
            int stock = input.readInt(">> 새 재고량 :");
            if (bcs.updateTemplateStock(templateNo, stock)) {
                System.out.println("[명함 템플릿 정보 수정] 재고량을 수정하였습니다.");
            } else {
                System.out.println("[명함 템플릿 정보 수정 오류] 없는 명함 템플릿입니다.");
            }
        } else {
            System.out.println("[명함 템플릿 정보 수정 취소] 지원하지 않는 기능입니다.");
        }

    }

    private void menuTemplateRemove() {
        System.out.println("*** 명함 템플릿 삭제 ***");
        displayTemplateList();
        int templateNo = input.readInt(">> 명함 템플릿 번호 :");
        String confirm = input.readString("선택한 명함 템플릿을 삭제하시겠습니까? ('" + CONFIRM + "'를 입력하면 실행) : ");
        if (confirm.equals(CONFIRM)) {
            if (bcs.removeTemplate(templateNo)) {
                System.out.println("[명함 템플릿 삭제] 명함 템플릿을 삭제했습니다.");
            } else {
                System.out.println("[명함 템플릿 삭제 오류] 없는 명함 템플릿입니다.");
            }
        } else {
            System.out.println("[명함 템플릿 삭제 취소] 명함 템플릿 삭제를 취소했습니다.");
        }
    }

    private void menuMemberList() {
        System.out.println("*** 회원 목록 ***");
        System.out.println("---------------------------------------");
        List<MemberVO> memberList = ms.listMembers();
        if (memberList.isEmpty()) {
            System.out.println("회원이 없습니다.");
        } else {
            for (MemberVO member : memberList) {
                System.out.println(member);
            }
        }
        System.out.println("---------------------------------------");
    }

    private void menuOrderList() {
        if (loggedMember != null && loggedMember.getId().equals(ADMIN_ID)) {
            System.out.println(os.listAllOrders());
        } else if (loggedMember != null) {
            System.out.println(os.listMyOrders(loggedMember.getId()));
        } else {
            System.out.println("로그인이 필요합니다.");
        }
    }

    private void menuLogout() {
        if (loggedMember != null) {
            System.out.println("[로그아웃] " + loggedMember.getUsername() + "님, 안녕히 가십시오.");
            loggedMember = null;
        } else {
            System.out.println("이미 로그아웃 상태입니다.");
        }
    }
}
