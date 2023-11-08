package com.ll;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        readFile();
        Quotation quotation = Quotations.get(num);
        System.out.println("명언(기존) : " + quotation.getContent());
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.println("작가(기존) : " + quotation.getAuthorName());
        System.out.print("작가 : ");
        String authorName = scanner.nextLine();

        quotation.setContent(content);
        quotation.setAuthorName(authorName);
        writeFileAll();
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
        readFile();
        Quotations.remove(num);
        writeFileAll();
        System.out.println(id + "번 명언이 삭제되었습니다.");
    }

    private void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        readFile();
        Quotations.reversed().forEach(quotation -> System.out.println(quotation.getId() + " / " + quotation.getAuthorName() + " / " + quotation.getContent()));
        System.out.println("output.txt에서 가져왔습니다.");
    }

    private void actionRegister() {
        System.out.println("명언 : ");
        String content = scanner.nextLine();
        System.out.println("작가 : ");
        String authorName = scanner.nextLine();
        contentId++;
        Quotation quotation = new Quotation(contentId, content, authorName);
        //To verify writeFile -- 2023-11-08
        //Quotations.add(quotation);
        writeFile(quotation);
        System.out.println(quotation.getId() + "번 명언이 등록되었습니다.");
    }

    int verifyId(int id, int defaultValue) {
        readFile();
        for (int i = 0; i < Quotations.size(); i++) {
            Quotation quotation = Quotations.get(i);
            if (quotation.getId() == id) {
                Quotations.clear();
                return i;
            }
        }
        Quotations.clear();
        return defaultValue;
    }
    void writeFileAll() {
        try {
            //파일 초기화
            FileWriter fileWriter = new FileWriter("output.txt");
            fileWriter.write("");
            fileWriter.close();
            FileWriter writer = new FileWriter("output.txt", true);
            Quotations.forEach(
                    quotation -> {
                        try {
                            writer.write(quotation.getId() + " / " + quotation.getAuthorName() + " / " + quotation.getContent() + '\n');
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    void writeFile(Quotation quotation) {
        try {
            FileWriter writer = new FileWriter("output.txt", true);
            writer.write(quotation.getId() + " / " + quotation.getAuthorName() + " / " + quotation.getContent() + '\n');
            writer.close();
            System.out.println("output.txt에 저장했습니다.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    void readFile() {
        Path file = Paths.get("output.txt");
        try {
            List<String> lines = Files.readAllLines(file);

            for (String line : lines) {
                String[] lineBits = line.split(" / ",3);
                Quotation quotation = new Quotation(Integer.parseInt(lineBits[0]), lineBits[2], lineBits[1]);
                Quotations.add(quotation);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}
