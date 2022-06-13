package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;

public class H1DivDifference {
    /**Есть ли разница между $("h1 div"); и $("h1").$("div"); - может ли привести к тому что, поиск найдёт разные элементы?
     *Я не эксперт в верстке, поэтому я не представляю, зачем в заголовок вставлять див.
     *Для проверки я придумал эквивалентную конструкцию, поменяв теги местами. Результат ниже.
     *Фокус в том, что первый вариант сразу проводит проверку по 2 параметрам, а второй вариант - поэтапно.
     *В итоге первый вариант выдаст результат, а второй - зафейлится.
     *
     *<!DOCTYPE html>
     *<html>
     *   <head>
     *       <title>Page Title</title>
     *   </head>
     *   <body>
     *       <div class="classname1">
     *           <p>My first paragraph.</p>
     *       </div>
     *       <div class="classname2">
     *           <h1>My First Heading</h1>
     *       </div>
     *   </body>
     *</html>
     *
     * Ниже представлен реальный пример.*/
    @DisplayName("Разница")
    @Test
    void h1DivDifference() {
        Selenide.open("https://github.com/");
        SelenideElement resultWithSpace = $("div.flex-items-center h1");
        System.out.println(resultWithSpace);
        System.out.println("-----------------------------------------------------------------------------------------");
        SelenideElement result2Searches = $("div.flex-items-center").$("h1");
        System.out.println(result2Searches);
        Assertions.assertTrue(resultWithSpace == result2Searches);
    }
}
