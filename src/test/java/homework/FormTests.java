package homework;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class FormTests {

    private String firstName = "Boris";
    private String lastName = "Britva";
    private String userEmail = "braiva.boris@ya.ru";
    private String gender = "Male";
    private String mobileNumber = "8976543217";
    private String dateOfBirth = "5 Sep 2050";
    private String subjects1 = "English";
    private String subjects2 = "Maths";
    private String Hobbies = "Sports";
    private String currentAddress = "moscow, red square, kremlin";
    private String state = "Haryana";
    private String city = "Karnal";


    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com/automation-practice-form";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillFormTest() {
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

 /*     Student Name dfdfd dfdfd
        table-responsive
        Label	Values
        Student Name	dfdfd dfdfd
        Student Email	d.ffd@fgf.rt
        Gender	Male
        Mobile	8976543217
        Date of Birth	10 September,2023
        Subjects	English, Maths
        Hobbies	Sports, Reading, Music
        Picture	368519730.jpg
        Address	moscow, red square, kremlin
        State and City	Haryana Karnal
 */
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