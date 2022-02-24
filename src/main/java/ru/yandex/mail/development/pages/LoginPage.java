package ru.yandex.mail.development.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.mail.development.utility.WaitElement;

public class LoginPage {

    private final WebDriver driver;
    @FindBy(id = "passp-field-login")
    WebElement idFieldLocator;
    @FindBy(id = "passp-field-passwd")
    WebElement pswFieldLocator;
    @FindBy(id = "passp:sign-in")
    WebElement signInBtnLocator;
    /*private final By idFieldLocator = By.id("passp-field-login");
    private final By pswFieldLocator = By.id("passp-field-passwd");
    private final By signInBtnLocator = By.id("passp:sign-in");*/

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
//        WaitElement.waitElementVisible(driver, By.xpath("//label[text()='Введите ваш ID']"));
    }

    @Step("Ввод ID '{id}'")
    public void enterID(String id) {
//        driver.findElement(idFieldLocator).sendKeys(id);
        idFieldLocator.sendKeys(id);
    }

    @Step("Ввод пароля '{psw}'")
    public void enterPassword(String psw) {
//        WaitElement.waitElementVisible(driver, pswFieldLocator).sendKeys(psw);
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
//        driver.findElement(signInBtnLocator).click();
        signInBtnLocator.click();
    }
}
