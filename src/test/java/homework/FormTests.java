package homework;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class FormTests {


    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com/automation-practice-form";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillFormTest() {

        String firstName = "Boris";
        String lastName = "Britva";
        String userEmail = "braiva.boris@ya.ru";
        String gender = "Male";
        String mobileNumber = "8976543217";
        String dateOfBirth = "5 Sep 2050";
        String subjects1 = "English";
        String subjects2 = "Maths";
        String Hobbies = "Sports";
        String currentAddress = "moscow, red square, kremlin";
        String state = "Haryana";
        String city = "Karnal";

        open("/");

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#userNumber").setValue(mobileNumber);
        $(".custom-control-label").click();
        $(By.id("subjectsInput")).sendKeys("e");
        $(By.id("subjectsInput")).pressEnter();
        $(By.id("subjectsInput")).sendKeys("m");
        $(By.id("subjectsInput")).pressEnter();
        $("label[for='hobbies-checkbox-1']").click();
        $("label[for='hobbies-checkbox-2']").click();
        $("label[for='hobbies-checkbox-3']").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/368519730.jpg"));
        $("#currentAddress").setValue(currentAddress);
        $("#state").click();
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#city").click();
        $("#react-select-4-input").setValue(city).pressEnter();

        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text(firstName + " " + lastName),
                text(userEmail),
                text("Male"),
                text(mobileNumber),
                text(subjects1 + ", " + subjects2),
                text("368519730.jpg"),
                text("Address " + currentAddress),
                text(state + " " + city));

    }
}