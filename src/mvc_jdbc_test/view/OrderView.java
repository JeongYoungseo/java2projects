package mvc_jdbc_test.view;

import mvc_jdbc_test.entity.Order;

import java.util.Date;

public class OrderView {
    public String title = "주문정보";
//    public void printOrder(Order order) {
//        System.out.printf("* 주문번호:%s\n",order.getOrderNumber());
//        System.out.printf("* 고객이름:%s\n",order.getCustomerName());
//        System.out.printf("* 고객아이디:%s\n",order.getCustomerId());
//        System.out.printf("* 배송지:%s\n",order.getShippingAddress());
//        System.out.printf("* 수량:%s\n",order.getQuantity());
//        System.out.printf("* 주문일자:%s\n",order.getOrderDate());
//        System.out.printf("* 제품명:%s\n",order.getProductName());
//    }

    public void printOrderHead() {
        System.out.println("----------------- " + title + " -------------------");
    }
    public void printOderFooter() {
        System.out.println("---------------- Print Done -----------------");
    }

    public static void printHead(){
        System.out.println("================== 주문화면 ====================");
        System.out.println("주문번호\t고객이름\t 고객아이디\t\t 배송지\t\t 수량\t\t 주문일자\t 제품명");
        System.out.println();
    }
    public static void printOrders(Order order) {
        Date orderDate = order.getOrderDate();
        String ordersDate = (orderDate.getYear())+"-"+(orderDate.getMonth())+"-"+(orderDate.getDay());
        System.out.printf("%s\t\t%s\t\t%s\t\t%s\t\t\t%s\t\t\t%s\t\t%s\n",order.getOrderNumber(),order.getCustomerName(),order.getCustomerId(), order.getShippingAddress(),order.getQuantity(),order.getOrderDate().toString(),order.getProductName());

    }
}

