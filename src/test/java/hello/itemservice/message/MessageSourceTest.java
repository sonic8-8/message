package hello.itemservice.message;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @DisplayName("locale 정보가 없으면 기본 메세지 파일을 조회한다")
    @Test
    void helloMessage() {
        // given // when
        String result = ms.getMessage("hello", null, null);

        // then
        assertThat(result).isEqualTo("안녕");
    }

    @DisplayName("메세지가 없는 경우 NoSuchMessageException이 발생한다.")
    @Test
    void notFoundMessageCode() {
        // given // when // then
        assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @DisplayName("메세지가 없는 경우 대체 메세지를 가져온다.")
    @Test
    void notFoundMessageCodeDefaultMessage() {
        // given // when // then
        String result = ms.getMessage("no_code", null, "기본 메세지", null);
        assertThat(result).isEqualTo("기본 메세지");
    }

    @DisplayName("{0} 부분은 매개변수를 전달해서 치환할 수 있다.")
    @Test
    void test() {
        // given

        String result = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(result).isEqualTo("안녕 Spring");
        // when

        // then

    }
}
