package main;

import CustomExceptions.OutOfRangeInput;
import entity.Account;
import service.impl.AccountServiceImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static AccountServiceImpl accountService = new AccountServiceImpl();

    public static void main(String[] args) {
        Boolean flag = true;
        while (flag) {
            System.out.println("1-login" + "\n" + "2-singUp" + "\n" + "3-exit");
            try {
                Integer operator = input.nextInt();
                if (operator > 3 || operator < 1) {
                    throw new OutOfRangeInput("please enter something in range!");
                }
                switch (operator) {
                    case 1:
                        login();
                        break;
                    case 2:
                        singUp();
                        break;
                    case 3:
                        flag = false;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("please enter a number!");
            } catch (OutOfRangeInput e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public static void login() {
        System.out.println("please enter your username:");
        String username = input.next();
        System.out.println("please enter your password:");
        String password = input.next();
        Account account = new Account();
        account.setUserName(username);
        account.setPassword(password);
        Integer id = accountService.login(account);
        if (id <= 0) {
            System.out.println("there is no acc with this username and password!");
            return;
        }
    }

    public static void singUp() {
        System.out.println("please enter your firstname:");
        String firstname = input.next();
        System.out.println("please enter your lastname:");
        String lastname = input.next();
        System.out.println("please enter your username:");
        String username = input.next();
        System.out.println("please enter your password:");
        String password = input.next();
        Account account=new Account(firstname,lastname,username,password,null,null);
        if(accountService.create(account)>0){
            System.out.println("done!");
            return;
        }
        System.out.println("something went wrong! try again!");
    }
}
