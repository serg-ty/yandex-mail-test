package ru.yandex.mail.development.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.mail.development.utility.WaitElement;

public class MailPage {

    private final WebDriver driver;
    @FindBy(xpath = "//span[text()='Написать']/parent::a")
    WebElement newMailBtnLocator;
    @FindBy(xpath = "//span[text()='Входящие']/parent::div")
    WebElement incomingMailFolderLocator;
    @FindBy(xpath = "//span[contains(@class,'LeftColumn-Counters')]")
    WebElement mailCountLocator;
    @FindBy(xpath = "//button[contains(@class,'SyncButton')]")
    WebElement refreshBtnLocator;
    @FindBy(xpath = "//div[@class='ComposeRecipients-TopRow']//div[@contenteditable='true']")
    WebElement recipientFieldLocator;
    @FindBy(xpath = "//input[@name='subject']")
    WebElement subjectFieldLocator;
    @FindBy(xpath = "//div[@role='textbox']")
    WebElement mailContentLocator;
    @FindBy(xpath = "//span[text()='Отправить']/ancestor::button")
    WebElement sendBtnLocator;


    public MailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Отправка нового письма")
    public void sendNewMail(String toRecipient, String subject) {
        int mailCount = getIncomingMailCount();
        String mailContent = getMailTextToSend(mailCount);

        newMailBtnLocator.click();
        recipientFieldLocator.sendKeys(toRecipient);
        subjectFieldLocator.sendKeys(subject);
        mailContentLocator.sendKeys(mailContent);
        sendBtnLocator.click();
        WaitElement.waitElementVisible(driver, By.partialLinkText("Вернуться во")).click();
    }

    @Step("Получение количества входящих писем")
    public int getIncomingMailCount() {
        driver.navigate().refresh();
        String incomingMailCountTxt = mailCountLocator.getText();

        return incomingMailCountTxt.equals("") ? 0 :
                Integer.parseInt(incomingMailCountTxt.substring(0, incomingMailCountTxt.indexOf('/')));
    }

    private String getMailTextToSend(int mailCount) {
        String message = String.format("Найдено %s ", mailCount);

        switch (mailCount) {
            case 1:
                message += "письмо";
                break;
            case 2:
            case 3:
            case 4:
                message += "письма";
                break;
            default:
                message += "писем";
        }

        return message;
    }
}
