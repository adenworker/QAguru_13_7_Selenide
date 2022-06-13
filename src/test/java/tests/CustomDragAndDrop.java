package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class CustomDragAndDrop {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    @DisplayName("Перемещение квадратов")
    void customDragAndDrop(){
        open("https://the-internet.herokuapp.com/drag_and_drop");

        /**Первый вариант решения я взял почти целиком из сниппетов Алексея Виноградова:
        actions().clickAndHold($("#column-a")).moveToElement($("#column-b")).release().perform();
        Оно работает до clickAndHold включительно, а дальше висит очень долго и ничего не происходит до движения
        юзером мышкой. Что странно, я нашел похожий кейс на YouTube и там оно работает.*/

        /**Во втором варианте я предположил, что дело может быть в методе moveToElement (к тому же в сниппете Алексея
        использовался другой метод). Чтобы получить координаты, пришлось кое-что дописать:
        String strLocation = ($("#column-b").getLocation().toString());
        String[] strLocationsList = strLocation.split(",");
        List<Integer> intLocations = new ArrayList<>();
        for(String strLoc : strLocationsList) {
            intLocations.add(Integer.parseInt(strLoc.replaceAll("\\D", "")));
        }
        actions().clickAndHold($("#column-a"))
                .moveByOffset(intLocations.get(0), intLocations.get(1)).release().perform();
        Впрочем, не помогло, ошибка та же, что и в первом варианте.*/

        /**Сработал только метод из коробки. Если я зарылся в селенид в правильном направлении, то в dragAndDropTo
         *используется селениумное перемещение мыши. Возможно, селенидовое на этом сайте не работает.*/
        $("#column-a").dragAndDropTo("#column-b");
    }
}
