package com.example.javacore_customers_demo.demo;

import com.example.javacore_customers_demo.entity.Customer;
import com.example.javacore_customers_demo.model.CustomerModel;
import com.example.javacore_customers_demo.model.InMemoryCustomerModel;
import com.example.javacore_customers_demo.model.MySqlCustomerModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {
    private static CustomerModel CustomerModel;

    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Please choose type of model: ");
            System.out.println("--------------------------");
            System.out.println("1. In memory model.");
            System.out.println("2. My SQL model.");
            System.out.println("--------------------------");
            System.out.println("Please enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    CustomerModel = new InMemoryCustomerModel();
                    break;
                case 2:
                    CustomerModel = new MySqlCustomerModel();
                    break;
            }
            generateMenu();
        }
    }

    private static void generateMenu() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("-------------Customers Manager--------------");
            System.out.println("1. Create new customer");
            System.out.println("2. Show all customer");
            System.out.println("3. Update customer");
            System.out.println("4. Delete customer");
            System.out.println("5. Exit");
            System.out.println("------------------------------------------");
            System.out.println("Please enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    createNewCustomer();
                    break;
                case 2:
                    showAllCustomer();
                    break;
                case 3:
                    updateCustomer();
                    break;
                case 4:
                    deleteCustomer();
                    break;
                case 5:
                    System.out.println("Cya!");
                    break;
            }
            if (choice == 5) {
                break;
            }
        }
    }

    private static void deleteCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter id to delete: ");
        String id = scanner.nextLine();
        Customer existingCustomer = CustomerModel.findByID(id);
        if (existingCustomer == null) {
            System.err.println("404, Customer not found!");
        } else {
            if (CustomerModel.delete(id)) {
                System.out.println("Action success!");
            } else {
                System.err.println("Action fails, please try again!");
            }
        }
    }

    private static void updateCustomer() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter id to update: ");
        String id = scanner.nextLine();
        Customer existingCustomer = CustomerModel.findByID(id);
        if (existingCustomer == null) {
            System.err.println("404, Customer not found!");
        } else {
            System.out.println("Please enter name: ");
            String name = scanner.nextLine();
            System.out.println("Please enter phone: ");
            String phone = scanner.nextLine();
            System.out.println("Please enter image: ");
            String image = scanner.nextLine();
            System.out.println("Please enter date of birth: ");
            LocalDateTime dob = LocalDateTime.of(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy/MMM/dd/h/s");
            dob.format(sdf);
            existingCustomer.setName(name);
            existingCustomer.setImage(image);
            existingCustomer.setPhone(phone);
            existingCustomer.setDOB(dob);

            if (CustomerModel.update(id, existingCustomer) != null) {
                System.out.println("Action success!");
            } else {
                System.err.println("Action fails, please try again!");
            }
        }
    }

    private static void showAllCustomer() {
        List<Customer> list = CustomerModel.findAll();
        for (Customer customer :
                list) {
            System.out.println(customer.toString());
        }
    }

    private static void createNewCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter id: ");
        String id = scanner.nextLine();
        System.out.println("Please enter name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter phone: ");
        String phone = scanner.nextLine();
        System.out.println("Please enter image: ");
        String image = scanner.nextLine();
        System.out.println("Please enter date of birth: ");
        LocalDateTime dob = LocalDateTime.of(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy/MMM/dd/h/s");
        dob.format(sdf);
        Customer customer = new Customer(id, name, phone, image, dob);
        if (CustomerModel.save(customer) != null) {
            System.out.println("Create customer success!");
        } else {
            System.err.println("Save customer fails, please try again later!");
        }
    }
}
