package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class AppTest {
    @Test
    @DisplayName("종료 입력 시 프로그램 종료.")
    void inputFinish(){
        Scanner scanner = TestUtil.genScanner("""
                종료
                """.stripIndent());
        new App().run();
        scanner.close();
    }
}
