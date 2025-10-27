package mvc_jdbc_test.view;

import mvc_jdbc_test.entity.Customer;

import java.util.Scanner;

public class InputCustomerInfoView {
    public Customer inputCustomerInfo() {
        Customer customer = new Customer();
        Scanner s = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        System.out.println("====== 고객정보입력 ======\n");
        System.out.println("고객아이디입력: ");
        String customerId = s.nextLine();
        System.out.println("고객이름입력: ");
        String customerName = s.nextLine();
        System.out.println("나이입력: ");
        int age = sc.nextInt();
        System.out.println("등급입력: ");
        String level = s.nextLine();
        System.out.println("직업입력: ");
        String job = s.nextLine();
        System.out.println("적립금입력: ");
        int reward = sc.nextInt();

        customer.setCustomerid(customerId);
        customer.setCustomername(customerName);
        customer.setAge(age);
        customer.setLevel(level);
        customer.setJob(job);
        customer.setReward(reward);

        return customer;
    }
}
