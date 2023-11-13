package com.ll.base;

import com.ll.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

public class AppTest {

    public static String run(String cmd) {
        ByteArrayOutputStream byteArrayOutputStream = TestUtil.setOutToByteArray();
        Scanner scanner = TestUtil.genScanner(cmd.stripIndent());
        new App(scanner).run();
        scanner.close();

        String rs = byteArrayOutputStream.toString();
        TestUtil.clearSetOutToByteArray(byteArrayOutputStream);

        return rs.trim();
    }

    @Test
    @DisplayName("종료 입력 시 프로그램 종료.")
    void inputFinish() {
        Scanner scanner = TestUtil.genScanner("""
                종료
                """.stripIndent());
        new App(scanner).run();
        scanner.close();
    }

    @Test
    @DisplayName("등록 입력 시 명언 및 작가를 입력창이 뜬다.")
    void inputEnroll() {
        Scanner scanner = TestUtil.genScanner("""
                등록
                늦었다고 생각했을 때가 제일 늦었다.
                박명수
                종료
                """.stripIndent());

        new App(scanner).run();
        scanner.close();
    }

}
