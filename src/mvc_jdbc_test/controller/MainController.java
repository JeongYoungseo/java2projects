package mvc_jdbc_test.controller;

import jdbcTest.JDBCConnector;
import mvc_jdbc_test.entity.Customer;
import mvc_jdbc_test.entity.Order;
import mvc_jdbc_test.view.CustomerView;
import mvc_jdbc_test.view.InputCustomerInfoView;
import mvc_jdbc_test.view.OrderView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainController {
    public static void main(String[] args) {
        Connection con = JDBCConnector.getConnection();
        //customerListAndView(con);
        //orderListAndView(con);
        InputAndView(con);
    }
    public static void orderListAndView(Connection con) {
       ArrayList<Order> orderList = new ArrayList<Order>();
        String sql = "select 주문번호, 고객이름, 고객아이디, 배송지, 수량, 주문일자, 제품명 from 주문, 고객,제품 where 주문.주문고객=고객.고객아이디 and 주문.주문제품=제품.제품번호";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Order order = null;
            while (rs.next()) {
                order = new Order();
                order.setOrderNumber(rs.getString("주문번호"));
                order.setCustomerName(rs.getString("고객이름"));
                order.setCustomerId(rs.getString("고객아이디"));
                order.setShippingAddress(rs.getString("배송지"));
                order.setQuantity(rs.getInt("수량"));
                order.setOrderDate(rs.getDate("주문일자"));
                order.setProductName(rs.getString("제품명"));
                orderList.add(order);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Statement Error");
        }
//        OrderView orderView = new OrderView();
//        orderView.printOrderHead();
        OrderView.printHead(); // ->static이면 생성안하고 바로 사용 가능
        for (Order order: orderList) {
            OrderView.printOrders(order);
            System.out.println();
        }
//       orderView.printOderFooter();

    }

    public static void customerListAndView(Connection con){
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        try {
            String sql = "select * from 고객";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Customer customer = null;

            while(rs.next()) {
                customer = new Customer();
                customer.setCustomerid(rs.getString("고객아이디"));
                customer.setCustomername(rs.getString("고객이름"));
                customer.setAge(rs.getInt("나이"));
                customer.setLevel(rs.getString("등급"));
                customer.setJob(rs.getString("직업"));
                customer.setReward(rs.getInt("적립금"));
                customerList.add(customer);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Statement Error");
        }

        // CustomerView를 사용해서 customerList에 저장된 Customer Entity의 정보들 출력
        CustomerView customerView = new CustomerView();
        customerView.printHead();
        for (Customer customer: customerList) {
            customerView.printCustomer(customer);
            System.out.println();
        }
        customerView.printFooter();

    }

// 고객정보 입력 및 입력 내용 출력
// 고객정보 db의 고객테이블에 고객 Entity 추가

    public static void InputAndView(Connection con){
        Scanner s2 = new Scanner(System.in);
        InputCustomerInfoView inputCustomerInfoView = new InputCustomerInfoView();
        while (true){
            Customer customer = inputCustomerInfoView .inputCustomerInfo();
            CustomerView customerView = new CustomerView();
            customerView.printHead();
            customerView.printCustomer(customer);
            customerView.printFooter();

            String sql = "insert into 고객 values(?,?,?,?,?,?)";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, customer.getCustomerid());
                ps.setString(2, customer.getCustomername());
                ps.setInt(3, customer.getAge());
                ps.setString(4, customer.getLevel());
                ps.setString(5, customer.getJob());
                ps.setInt(6, customer.getReward());
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("고객정보를 추가하기겠습까?(N입력시 종료): ");

            String input = s2.nextLine();

            if(input.equals("N")){
               break;
           }
            System.out.println("프로그램 종료");
        }


    }


}


