package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private Scanner scanner;
    private int contentId;
    private List<Quotation> Quotations;

    App(Scanner scanner) {
        this.scanner = scanner;
        contentId = 0;
    }

    void run() {
        System.out.println("== 명언 앱 ==");
        Quotations = new ArrayList<>();
        while (true) {
            System.out.println("명령) ");
            String cmd = scanner.nextLine();
            Rq rq = new Rq(cmd);
            switch (rq.getCmd()) {
                case "종료":
                    return;
                case "등록":
                    actionRegister();
                    break;
                case "목록":
                    actionList();
                    break;
                case "삭제":
                    actionRemove(rq);
                    break;
                case "수정":
                    actionModify(rq);
                    break;
            }
        }
    }

    private void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", 0);
        if (id == 0) {
            System.out.println("id를 입력해주세요.");
            return;
        }
        int num = verifyId(id, -1);

        if (num == -1) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }
        Quotation quotation = Quotations.get(num);
        System.out.println("명언(기존) : " + quotation.getContent());
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.println("작가(기존) : " + quotation.getAuthorName());
        System.out.print("작가 : ");
        String authorName = scanner.nextLine();

        quotation.setContent(content);
        quotation.setAuthorName(authorName);
    }

    private void actionRemove(Rq rq) {
        int id = rq.getParamAsInt("id", 0);
        if (id == 0) {
            System.out.println("id를 입력해주세요.");
        }
        int num = verifyId(id, -1);
        if (num == -1) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }
        Quotations.remove(num);
        System.out.println(id + "번 명언이 삭제되었습니다.");
    }

    private void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        Quotations.reversed().forEach(quotation -> System.out.println(quotation.getId() + " / " + quotation.getAuthorName() + " / " + quotation.getContent()));
    }

    private void actionRegister() {
        System.out.println("명언 : ");
        String content = scanner.nextLine();
        System.out.println("작가 : ");
        String authorName = scanner.nextLine();
        contentId++;
        Quotation quotation = new Quotation(contentId, content, authorName);
        Quotations.add(quotation);
        System.out.println(quotation.getId() + "번 명언이 등록되었습니다.");
    }

    int verifyId(int id, int defaultValue) {
        for (int i = 0; i < Quotations.size(); i++) {
            Quotation quotation = Quotations.get(i);
            if (quotation.getId() == id) {
                return i;
            }
        }
        return defaultValue;
    }
}
