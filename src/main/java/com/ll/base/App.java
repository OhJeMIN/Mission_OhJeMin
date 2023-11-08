package com.ll.base;

import com.ll.domain.quotation.QuotationController;

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
            Rq rq = new Rq(cmd);
            switch (rq.getCmd()) {
                case "종료":
                    return;
                case "등록":
                    quotationController.actionRegister();
                    break;
                case "목록":
                    quotationController.actionList();
                    break;
                case "삭제":
                    quotationController.actionRemove(rq);
                    break;
                case "수정":
                    quotationController.actionModify(rq);
                    break;
            }
        }
    }



}
