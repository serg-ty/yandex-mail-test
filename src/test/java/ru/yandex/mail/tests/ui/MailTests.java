package ru.yandex.mail.tests.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.mail.development.conf.Config;
import ru.yandex.mail.development.pages.LoginPage;
import ru.yandex.mail.development.pages.LoginWidgetPage;
import ru.yandex.mail.development.pages.MailPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MailTests extends ChromeTestsConfiguration {

    @Test
    @DisplayName("Вход в личный кабинет yandex.ru и отправка писем")
    void yandexLoginTest() {

        driver.get(System.getProperty("host_url"));

        LoginWidgetPage loginWidget = new LoginWidgetPage(driver);
        LoginPage loginPage = loginWidget.doLogin();
        loginPage.loginValidCredentials(Config.getProperty("userID"), Config.getProperty("userPassword"));

        assertEquals(Config.getProperty("userID"), loginWidget.getLoggedUserName(),
                "Значение в поле залогированного пользователя не совпадает с ID пользователя.");

        MailPage mailPage = loginWidget.checkMail();
        int currentMailCount = mailPage.getIncomingMailCount();
        mailPage.sendNewMail(Config.getProperty("userMail"), "Simbirsoft theme");
        assertEquals(currentMailCount + 1, mailPage.getIncomingMailCount(),
                "Количество входящих писем не соответствует ожидаемому.");
    }
}
