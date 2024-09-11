package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.TableRegistrationComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput   = $("#lastName"),
            userEmailInput  = $("#userEmail"),
            genderWrapper   = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput   = $("#dateOfBirthInput"),
            subjectInput    = $("#subjectsInput"),
            hobbyInput      = $("#hobbiesWrapper"),
            pictureInput    = $("#uploadPicture"),
            addressInput    = $("#currentAddress"),
            stateInput      = $("#state"),
            stateSelector   = $("#stateCity-wrapper"),
            cityInput       = $("#city"),
            citySelector    = $("#stateCity-wrapper"),
            submitButton    = $("#submit");

    CalendarComponent calendarComponent = new CalendarComponent();
    TableRegistrationComponent tableRegistrationComponent = new TableRegistrationComponent();

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.scrollIntoView(true);
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.scrollIntoView(true);
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setEmail(String value) {
        userEmailInput.scrollIntoView(true);
        userEmailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        genderWrapper.scrollIntoView(true);
        genderWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumberInput.scrollIntoView(true);
        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInput.scrollIntoView(true);
        calendarInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public RegistrationPage setSubject(String value){
        subjectInput.scrollIntoView(true);
        subjectInput.setValue(value).pressEnter();

        return this;
    }

    public RegistrationPage setHobby(String value){
        hobbyInput.scrollIntoView(true);
        hobbyInput.$(byText(value)).click();

        return this;
    }

    public RegistrationPage uploadProfilePicture(String value){
        pictureInput.uploadFromClasspath("img/" + value);

        return this;
    }

    public RegistrationPage setAddress(String value){
        addressInput.scrollIntoView(true);
        addressInput.setValue(value);

        return this;
    }

    public RegistrationPage setState(String value){
        stateInput.scrollIntoView(true);
        stateInput.click();
        stateSelector.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setCity(String value){
        cityInput.scrollIntoView(true);
        cityInput.click();
        citySelector.$(byText(value)).click();
        return this;
    }

    public RegistrationPage submit(){
        submitButton.scrollIntoView(true);
        submitButton.click();

        return this;
    }

    public RegistrationPage checkResult(String key, String value) {
        tableRegistrationComponent.checkRow(key, value);

        return this;
    }

    public RegistrationPage checkForError() {
        tableRegistrationComponent.isHidden();

        return this;
    }

}