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
        Quotations = new ArrayList<>();
        while(true){
            System.out.println("명령) ");
            String cmd = scanner.nextLine();
            Rq rq = new Rq(cmd);
            if(rq.getCmd().equals("등록")){
                System.out.println("명언 : ");
                String content = scanner.nextLine();
                System.out.println("작가 : ");
                String authorName = scanner.nextLine();
                contentId++;
                Quotation quotation = new Quotation(contentId,content,authorName);
                Quotations.add(quotation);
                System.out.println(quotation.getId()+"번 명언이 등록되었습니다.");
            }
            else if(rq.getCmd().equals("목록")){
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for(int i=Quotations.size()-1;i>=0;i--){
                    Quotation quotation = Quotations.get(i);
                    System.out.println(quotation.getId()+" / "+quotation.getAuthorName()+" / "+ quotation.getContent());
                }
            }
            else if(rq.getCmd().startsWith("삭제")){
                int id = rq.getParamAsInt("id",0);
                if(id == 0){
                    System.out.println("id를 입력해주세요.");
                }
                for(int i=0;i<Quotations.size();i++){
                    Quotation quotation = Quotations.get(i);
                    if(quotation.getId() == id){
                        Quotations.remove(id-1);
                        System.out.println(id +"번 명언이 삭제되었습니다.");
                    }
                }
            }
            else{
                break;
            }
        }

    }
}
