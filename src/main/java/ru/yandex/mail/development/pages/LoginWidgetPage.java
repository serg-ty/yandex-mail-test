package ru.yandex.mail.development.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.mail.development.utility.WaitElement;

import java.util.stream.Collectors;

public class LoginWidgetPage {

    private final WebDriver driver;
    private final WebElement loginBtn;
    private final String windowHandle;
    private final By loginLocator = By.xpath("//div[text()='Войти']");
    private final By incomingMailFolderLocator = By.xpath("//span[text()='Входящие']/parent::div");
    private final By loggedUserLocator = By.xpath("//span[starts-with(@class,'username ')]");
    private final By mailLocator = By.xpath("//div[text()='Почта']");

    public LoginWidgetPage(WebDriver driver) {
        this.driver = driver;
        windowHandle = driver.getWindowHandle();
        loginBtn = WaitElement.waitElementVisible(driver, loginLocator);
    }

    @Step("Вход в личный кабинет")
    public void doLogin() {
        loginBtn.click();
    }

    @Step("Проверка почты")
    public String checkMail() {
        WaitElement.waitElementVisible(driver, mailLocator).click();
        String newWndHandle;
        newWndHandle = driver.getWindowHandles().stream().filter(h -> !h.equals(windowHandle)).
                collect(Collectors.toList()).get(0);
        driver.switchTo().window(newWndHandle);
        WaitElement.waitElementVisible(driver, incomingMailFolderLocator);

        return newWndHandle;
    }

    public String getLoggedUserName() {
        return WaitElement.waitElementVisible(driver, loggedUserLocator).getText();
    }
}
