package com.ll.base;

import com.ll.domain.quotation.quotation.controller.QuotationController;

import java.util.Scanner;

public class App {
    public Scanner scanner;
    App(Scanner scanner) {
        this.scanner = scanner;
    }
    void run() {
        System.out.println("== 명언 앱 ==");
        QuotationController quotationController = new QuotationController(scanner);
        while (true) {
            System.out.println("명령) ");
            String cmd = scanner.nextLine();
            final Rq rq = new Rq(cmd);
            switch (rq.getCmd()) {
                case "등록" ,"목록" ,"삭제" ,"수정" -> quotationController.dispatch(rq);
                case "종료" -> {
                    return;
                }
            }
        }
    }
}
