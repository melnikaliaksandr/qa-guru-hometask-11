package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static helpers.DataHelper.*;
import static io.qameta.allure.Allure.step;

public class QaGuruTest extends TestBase {

    @BeforeEach
    public void openLoginPage() {
        step("Open login page", () -> {
            open("/cms/system/login");
        });
    }

    @Test
    @DisplayName("Successful login")
    public void successfulLogin() {
        step("Input login and password", () -> {
            $(byName("email")).val(LOGIN);
            $(byName("password")).val(PASSWORD).pressEnter();
        });
        step("Verify successful login", () -> {
            $(".login").click();
            $(".html-content").shouldHave(text("Вы авторизованы в этом аккаунте"));
        });
    }

    @Test
    @DisplayName("Login with wrong credentials")
    public void loginWithWrongCredentials() {
        step("Input login and password", () -> {
            $(byName("email")).val("wrongEmail");
            $(byName("password")).val("wrongPassword").pressEnter();
        });
        step("Verify wrong login", () -> {
            $(".btn-success").shouldHave(text("Неверный формат e-mail"));
        });
    }

    @Test
    @DisplayName("Login without email")
    public void loginWithoutEmail() {
        step("Input password and press Enter", () -> {
            $(byName("password")).val(PASSWORD).pressEnter();
        });
        step("Verify wrong login", () -> {
            $(".btn-success").shouldHave(text("Не заполнено поле E-Mail"));
        });
    }

    @Test
    @DisplayName("Login without password")
    public void loginWithoutPassword() {
        step("Input email and press Enter", () -> {
            $(byName("email")).val(LOGIN).pressEnter();
        });
        step("Verify wrong login", () -> {
            $(".btn-success").shouldHave(text("Не заполнено поле Пароль"));
        });
    }

    @Test
    @DisplayName("Successful registration")
    public void successfulRegistration() {
        String email = getRandomEmailAddress();
        String firstAndLastName = getRandomFirstAndLastName();

        step("Open registration page", () -> {
            $(".btn-register").click();
        });
        step("Input login and password", () -> {
            $(".active-state").$(byName("email")).val(email);
            $(".active-state").$(byName("full_name")).val(firstAndLastName).pressEnter();
        });
        step("Verify authorized zone", () -> {
            $(".login").click();
            $(".btn-logout").shouldBe(visible)
                    .shouldHave(text("Сменить пользователя"));
        });
    }

    @Test
    @DisplayName("Registration with existing user")
    public void registrationWithExistingUser() {
        String firstAndLastName = getRandomFirstAndLastName();

        step("Open registration page", () -> {
            $(".btn-register").click();
        });
        step("Input login and password", () -> {
            $(".active-state").$(byName("email")).val(LOGIN);
            $(".active-state").$(byName("full_name")).val(firstAndLastName).pressEnter();
        });
        step("Verify warning message about existing user", () -> {
            $(".has-registered-message").shouldBe(visible)
                    .shouldHave(text("уже был зарегистрирован ранее"));
        });
    }

}

