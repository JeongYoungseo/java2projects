package mvc_jdbc_test.controller;

import jdbcTest.JDBCConnector;
import mvc_jdbc_test.dataAccessObject.CustomerDao;
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
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=======고객 정보 프로그램 ========\n");
            System.out.println("1. 고객정보조회");
            System.out.println("2. 고객정보입력");
            System.out.println("3. 고객정보수정");
            System.out.println("4. 고객정보삭제");
            System.out.println("0. 프로그램 종료");
            System.out.println("선택: ");

            int select = sc.nextInt();
            sc.nextLine();

            switch (select) {
                case 1:
                    customerListAndView(con);
                    break;
                case 2:
                    InputAndView(con);
                    break;
                case 3:
                    UpdateCustomer(con);
                    break;
                case 4:
                    DeleteCustomer(con);
                    break;
                case 0:
                    System.out.println("프로그램이 종료됩니다.");
                    sc.close();
                {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return;
                }
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택하세요");

            }
        }
        //customerListAndView(con);
        //orderListAndView(con);
        //InputAndView(con);
       // UpdateCustomer(con);
       // DeleteCustomer(con);
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
            s2.close();
        }
    }

//  ============================== 중간고사 과제 부분 =================================
// ** CustomerDao 클래스를 이용해 수정 및 삭제 **
// Customer table Update(수정)
    public static void UpdateCustomer(Connection con){
        Scanner s3 = new Scanner(System.in);
        CustomerDao customerDao = new CustomerDao();
        System.out.print("수정할 고객 아이디: ");
        String customerid = s3.nextLine();

        Customer existing = customerDao.getCustomerId(con, customerid);
        if(existing == null){
            System.out.println("해당 고객이 존재하지 않습니다.");
            return;
        }
        System.out.println("현재 이름: " + existing.getCustomername());
        System.out.println("현재 나이: " + existing.getAge());
        System.out.println("현재 등급: " + existing.getLevel());
        System.out.println("현재 직업: " + existing.getJob());
        System.out.println("현재 적립금: " + existing.getReward());


        System.out.println("새 이름: ");  
        String newName = s3.nextLine();
        System.out.println("새 나이: ");
        int newAge = Integer.parseInt(s3.nextLine());
        System.out.println("새 등급: ");
        String newLevel = s3.nextLine();
        System.out.println("새 직업: ");
        String newJob = s3.nextLine();
        System.out.println("새 적립금: ");
        int newReward = Integer.parseInt(s3.nextLine());

        Customer update = new Customer(customerid, newName,newAge,newLevel,newJob,newReward);
        customerDao.updateCustomer(con, update);

        System.out.println("수정완료");
    }

// Customer table Delete(삭제)
    public static void DeleteCustomer(Connection con){
        Scanner s4 = new Scanner(System.in);
        CustomerDao customerDao = new CustomerDao();
        System.out.println("삭제할 고객 아이디: ");
        String id  = s4.nextLine();

        Customer target = customerDao.getCustomerId(con, id);
        if(target == null){
            System.out.println("해당 고객이 존재하지 않습니다.");
            return;
        }

        System.out.println("삭제할 고객 이름: " + target.getCustomerid());
        System.out.println("정말 삭제하시겠습니까? [Y/N]");
        String answer = s4.nextLine();

        if(answer.equalsIgnoreCase("Y")) {
            customerDao.deleteCustomer(con, id);
            System.out.println("삭제완료");
        }else {
            System.out.println("삭제취소");
        }
    }
}


