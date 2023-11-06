package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private Scanner scanner;
    private int contentId;
    private List<Quotation> Quotations;
    App(Scanner scanner){
        this.scanner = scanner;
        contentId = 0;
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
                contentId++;
                Quotation quotation = new Quotation(contentId,content,authorName);
                Quotations = new ArrayList<>();
                Quotations.add(quotation);
                System.out.println(quotation.getId()+"번 명언이 등록되었습니다.");
            }
            else if(cmd.equals("목록")){
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for(int i=Quotations.size()-1;i>=0;i--){
                    Quotation quotation = Quotations.get(i);
                    System.out.println(quotation.getId()+" / "+quotation.getAuthorName()+" / "+ quotation.getContent());
                }
            }
            else{
                break;
            }
        }

    }
}
