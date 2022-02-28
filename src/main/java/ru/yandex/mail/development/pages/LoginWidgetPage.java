package ru.yandex.mail.development.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.mail.development.utility.WaitElement;

import java.util.stream.Collectors;

public class LoginWidgetPage {

    private final WebDriver driver;
    private final String windowHandle;
    @FindBy(xpath = "//div[text()='Войти']")
    WebElement loginBtn;
    @FindBy(xpath = "//span[text()='Входящие']/parent::div")
    WebElement incomingMailFolderLocator;
    @FindBy(xpath = "//span[starts-with(@class,'username ')]")
    WebElement loggedUserLocator;
    @FindBy(xpath = "//div[text()='Почта']")
    WebElement mailLocator;

    public LoginWidgetPage(WebDriver driver) {
        this.driver = driver;
        windowHandle = driver.getWindowHandle();
        PageFactory.initElements(driver, this);
    }

    @Step("Вход в личный кабинет")
    public LoginPage doLogin() {
        loginBtn.click();
        return new LoginPage(driver);
    }

    @Step("Проверка почты")
    public MailPage checkMail() {
        mailLocator.click();
        String newWndHandle;
        newWndHandle = driver.getWindowHandles().stream().filter(h -> !h.equals(windowHandle)).
                collect(Collectors.toList()).get(0);
        driver.switchTo().window(newWndHandle);
        WaitElement.waitElementVisible(driver, incomingMailFolderLocator);

        return new MailPage(driver);
    }

    public String getLoggedUserName() {
        return loggedUserLocator.getText();
    }
}
