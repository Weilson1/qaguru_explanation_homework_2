import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;

public class explanation_homework_2 {
    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }
    @Test
    void fillFormTest() {
        open ("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        $("#firstName").setValue("John");
        $("#lastName").setValue("Shepard");
        $("#userEmail").setValue("jshepard@email.net");
        $("genderWrapper").$(byText("Other")).click(); // Самый полный и корректный вариант выбора radiobutton

// Другие варианты выбора radiobutton
        $("gender-radio-3").parent().click();    // Обращение к родительской части
        $(byText("Other")).click();             // Ищет слово Other и кликает на первое попавшееся
        $("[for=gender-radio-3]").click();
        $("[for=' gender-radio-3']").click();    // Одинарные кавычки ставятся если есть пробел
        $x("//label[contains(.,'Male']").click();
        $("[for='gender-radio-1']").click();
// Конец других вариантов выбора radiobutton

        $(".react-datepicker__year-select").selectOption("1990");
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__day--030:not(.react-datapicker__day--outside-month").click();

// Другие варианты выбора дня из календаря
        $$(".react-datepicker__day--030")
                .filter(not(cssClass(".react-datapicker__day--outside-month")))
                .first() // or .get(0)
                .click();
        $("[aria-label=Choose Wednesday, July 30th, 2008]").click(); // NOT WORKING
        $("[aria-label='Choose Wednesday, July 30th, 2008']").click();
        $("[aria-label=\"Choose Wednesday, July 30th, 2008\"]").click(); // ' = \"
        $x("//*[contains(@aria-label, \"July 30th, 2008\"]").click(); // Ищем только часть
        $("[aria-label$='July 30th, 2008']").click();                     // Самый лаконичный и эффективный
        $("[aria-label*='July 30th']");                                   // * - просто содержит в себе July 30th
// Конец других вариантов выбора дня из календаря

        $("#subjectsInput").setValue("Maths").pressEnter();

// Другие варианты выбора предметов
        $("#subjectsInput").setValue("Maths");
        $("#subjectsWrapper").$(byText("Math")).click();
// Конец других вариантов выбора предметов

        $("#hobbiesWrapper").$(byText("Sports")).click();

        $("#uploadPicture").uploadFromClasspath("img/1.png");

// Другие варианты загрузки файлов

        $("#uploadPicture").uploadFile(new File("src/test/resources/img/1.png"));

        File someFile = new File("src/test/resources/img/1.png");
        $("#uploadPicture").uploadFile(someFile);
// Конец других вариантов загрузки файлов

        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delphi")).click();
        $("#submit").click();

    }


}
