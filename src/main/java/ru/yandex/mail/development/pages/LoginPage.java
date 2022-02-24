package ru.yandex.mail.development.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "passp-field-login")
    WebElement idFieldLocator;
    @FindBy(id = "passp-field-passwd")
    WebElement pswFieldLocator;
    @FindBy(id = "passp:sign-in")
    WebElement signInBtnLocator;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @Step("Ввод ID '{id}'")
    public void enterID(String id) {
        idFieldLocator.sendKeys(id);
    }

    @Step("Ввод пароля '{psw}'")
    public void enterPassword(String psw) {
        pswFieldLocator.sendKeys(psw);
    }

    @Step("Вход в личный кабинет по id '{id}' и паролю '{psw}'")
    public void loginValidCredentials(String id, String psw) {
        enterID(id);
        clickSignInButton();
        enterPassword(psw);
        clickSignInButton();
    }

    @Step("Нажатие на кнопку 'Войти'")
    public void clickSignInButton() {
        signInBtnLocator.click();
    }
}
