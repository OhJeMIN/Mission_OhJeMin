package com.ll.domain.quotation.quotation.controller;

import com.ll.base.AppTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuotationControllerTest {


    @Test
    @DisplayName("등록 후 안내문 출력")
    void printOutNotice() {
        String out = AppTest.run("""
                등록
                늦었다고 생각했을 때가 제일 늦었다.
                박명수
                종료
                """);
        assertThat(out).contains("1번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("등록 후 안내 번호 증가")
    void increaseOfIndex() {
        String out = AppTest.run("""
                등록
                늦었다고 생각했을 때가 제일 늦었다.
                박명수
                등록
                어려운 길은 길이 아니다.
                박명수
                종료
                """);
        assertThat(out).contains("번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("목록 명령어 입력 시 저장된 명언 목록 출력")
    void printOutList() {
        String out = AppTest.run("""
                목록
                종료
                """);
        assertThat(out).contains("1 / 박명수 / 늦었다고 생각했을 때가 제일 늦었다.");
        System.out.println(out);
    }

    @Test
    @DisplayName("삭제 명령어를 통해 명언 삭제")
    void removeContent() {
        String out = AppTest.run("""
                목록
                삭제?id=1&author=박명수
                목록
                종료
                """);
        assertThat(out).contains("1번 명언이 삭제되었습니다.");
        System.out.println(out);
    }

    @Test
    @DisplayName("param이 없는 명령문 예외처리")
    void paramExceptionHandling() {
        String out = AppTest.run("""
                목록
                삭제?
                목록
                종료
                """);
        assertThat(out).contains("id를 입력해주세요.");
        System.out.println(out);
    }

    @Test
    @DisplayName("존재하지 않는 명언삭제에 대한 예외처리")
    void notExistQuotation() {
        String out = AppTest.run("""
                목록
                삭제?id=2
                삭제?id=2
                종료
                """);
        assertThat(out).contains("2번 명언은 존재하지 않습니다.");
        System.out.println(out);
    }

    @Test
    @DisplayName("수정 명령문 시 기존 명언, 작가 출력")
    void modifyQuotation() {
        String out = AppTest.run("""
                목록
                삭제?id=2
                목록
                수정?id=1
                꿈은 없고요. 그냥 놀고 싶습니다
                박명수333
                종료
                """);
        System.out.println(out);
        assertThat(out).contains("명언(기존) : 늦었다고 생각했을 때가 제일 늦었다.")
                .contains("작가(기존) : 박명수")
                .contains("4 / 박명수333 / 꿈은 없고요. 그냥 놀고 싶습니다");
        System.out.println(out);
    }

    @Test
    @DisplayName("등록 후 목록 명령어 시 output.txt에서 저장된 파일 나오게 하기")
    void writeFile() {
        String out = AppTest.run("""
                등록
                꿈은 없고요. 그냥 놀고 싶습니다
                박명수333
                등록
                늦었다고 생각했을 때가 제일 늦었다.
                박명수
                종료
                """);
        assertThat(out).contains("output.txt에 저장했습니다.");
        System.out.println(out);

    }

    @Test
    @DisplayName("등록 후 목록 명령어 시 output.txt에서 저장된 파일 나오게 하기")
    void ReadFile() {
        String out = AppTest.run("""
                등록
                꿈은 없고요. 그냥 놀고 싶습니다
                박명수333
                등록
                늦었다고 생각했을 때가 제일 늦었다.
                박명수
                목록
                종료
                """);
        System.out.println(out);
        assertThat(out).contains("5 / 박명수 / 늦었다고 생각했을 때가 제일 늦었다.")
                .contains("4 / 박명수333 / 꿈은 없고요. 그냥 놀고 싶습니다")
                .contains("output.txt에서 가져왔습니다.");

    }

    @Test
    @DisplayName("등록 없이 목록 명령어 시 목록 나오게 하기")
    void justReadFile() {
        String out = AppTest.run("""
                목록
                종료
                """);
        System.out.println(out);
        assertThat(out).contains("6 / 박명수 / 늦었다고 생각했을 때가 제일 늦었다.")
                .contains("4 / 박명수333 / 꿈은 없고요. 그냥 놀고 싶습니다")
                .contains("output.txt에서 가져왔습니다.");

    }
}
