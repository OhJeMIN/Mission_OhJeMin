package com.ll;

import java.util.Scanner;

public class App {
    private Scanner scanner;
    App(Scanner scanner){
        this.scanner = scanner;
    }
    void run(){
        System.out.println("== 명언 앱 ==");

        while(true){
            System.out.println("명령) ");
            String cmd = scanner.nextLine();
            if(cmd.equals("등록")){
                System.out.println("명언 : ");
                String content = scanner.nextLine();
                System.out.println("작가 : ");
                String authorName = scanner.nextLine();
            }
            else{
                break;
            }
        }

    }
}
