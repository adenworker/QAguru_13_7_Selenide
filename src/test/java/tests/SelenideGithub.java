package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SelenideGithub {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    @DisplayName("Поиск примера кода JUnit 5")
    void jUnitCodeExampleSearch(){
        open("https://github.com/");
        $("[data-test-selector='nav-search-input']").setValue("Selenide").pressEnter();

        //Тут я кликаю на 1 результат, это само по себе проверка, что поиск прошел правильно и дальше все будет окей
        $$("ul.repo-list li").first().$("[class='f4 text-normal']").click();
        $("ul.UnderlineNav-body [data-content='Wiki']").parent().click();

        //Тут можно или развернуть список кликом на нижнюю строку или воспользоваться фильтром
        $("#wiki-pages-filter").sendKeys("soft");
        $("ul[data-filterable-for='wiki-pages-filter']")
                .shouldHave(text("SoftAssertions")).shouldBe(visible).click();

        //Ищем на странице строку с уникальным для JUnit5 синтаксисом
        $(".Layout-main").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})"));
    }
}
