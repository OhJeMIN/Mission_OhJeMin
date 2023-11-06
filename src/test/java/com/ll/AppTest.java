package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @Test
    @DisplayName("종료 입력 시 프로그램 종료.")
    void inputFinish(){
        Scanner scanner = TestUtil.genScanner("""
                종료
                """.stripIndent());
        new App(scanner).run();
        scanner.close();
    }

    @Test
    @DisplayName("등록 입력 시 명언 및 작가를 입력창이 뜬다.")
    void inputEnroll(){
        Scanner scanner = TestUtil.genScanner("""
                등록
                늦었다고 생각했을 때가 제일 늦었다.
                박명수
                종료
                """.stripIndent());
        new App(scanner).run();
        scanner.close();
    }

    @Test
    @DisplayName("등록 후 안내문 출력")
    void printOutNotice(){
        ByteArrayOutputStream byteArrayOutputStream = TestUtil.setOutToByteArray();
        Scanner scanner = TestUtil.genScanner("""
                등록
                늦었다고 생각했을 때가 제일 늦었다.
                박명수
                종료
                """.stripIndent());
        new App(scanner).run();
        scanner.close();

        String rs  = byteArrayOutputStream.toString();

        assertThat(rs).contains("1번 명언이 등록되었습니다.");
        TestUtil.clearSetOutToByteArray(byteArrayOutputStream);
    }

    @Test
    @DisplayName("등록 후 안내 번호 증가")
    void increaseOfIndex(){
        ByteArrayOutputStream byteArrayOutputStream = TestUtil.setOutToByteArray();
        Scanner scanner = TestUtil.genScanner("""
                등록
                늦었다고 생각했을 때가 제일 늦었다.
                박명수
                등록
                어려운 길은 길이 아니다.
                박명수
                종료
                """.stripIndent());
        new App(scanner).run();
        scanner.close();

        String rs  = byteArrayOutputStream.toString();

        assertThat(rs).contains("2번 명언이 등록되었습니다.");
        TestUtil.clearSetOutToByteArray(byteArrayOutputStream);
    }
}
