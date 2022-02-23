package ru.yandex.mail.development.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.mail.development.utility.WaitElement;

public class MailPage {

    private final WebDriver driver;
    private final By newMailBtnLocator = By.xpath("//span[text()='Написать']/parent::a");
    private final By incomingMailFolderLocator = By.xpath("//span[text()='Входящие']/parent::div");
    private final By mailCountLocator = By.xpath("//span[contains(@class,'LeftColumn-Counters')]");
    private final By refreshBtnLocator = By.xpath("//button[contains(@class,'SyncButton')]");
    private final By recipientFieldLocator = By.xpath("//div[@class='ComposeRecipients-TopRow']//div[@contenteditable='true']");
    private final By subjectFieldLocator = By.xpath("//input[@name='subject']");
    private final By mailContentLocator = By.xpath("//div[@role='textbox']");
    private final By sendBtnLocator = By.xpath("//span[text()='Отправить']/ancestor::button");

    public MailPage(WebDriver driver) {
        this.driver = driver;
        WaitElement.waitElementVisible(driver, newMailBtnLocator);
    }

    @Step("Отправка нового письма")
    public void sendNewMail(String toRecipient, String subject) {
        int mailCount = getIncomingMailCount();
        String mailContent = getMailTextToSend(mailCount);

        driver.findElement(newMailBtnLocator).click();
        WaitElement.waitElementVisible(driver, recipientFieldLocator).sendKeys(toRecipient);
        driver.findElement(subjectFieldLocator).sendKeys(subject);
        driver.findElement(mailContentLocator).sendKeys(mailContent);
        driver.findElement(sendBtnLocator).click();
        WaitElement.waitElementVisible(driver, By.partialLinkText("Вернуться во")).click();
    }

    @Step("Получение количества входящих писем")
    public int getIncomingMailCount() {
//        driver.findElement(refreshBtnLocator).click();
        driver.navigate().refresh();
        WebElement incomingFolderMailCount = WaitElement.waitElementVisible(driver, incomingMailFolderLocator)
                .findElement(mailCountLocator);

        String incomingMailCountTxt = incomingFolderMailCount.getText();

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
