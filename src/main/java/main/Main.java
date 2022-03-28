package main;

import java.util.Scanner;

public class Main {
    static Scanner input=new Scanner(System.in);
    public static void main(String[] args)  {
        Boolean flag=true;
        while (flag){
            System.out.println("1-login"+"\n"+"2-singUp"+"\n"+"3-exit");
            Integer operator= input.nextInt();
        }
    }
}
