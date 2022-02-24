package ru.yandex.mail.development.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitElement {

    public static WebElement waitElementVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(System.getProperty("explicit_wait"))))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitElementVisible(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(System.getProperty("explicit_wait"))))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitElementPresent(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(System.getProperty("explicit_wait"))))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static WebElement waitElementClickable(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(System.getProperty("explicit_wait"))))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean waitElementContainAttribute(WebDriver driver, By locator, String attribute, String value) {
        return new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(System.getProperty("explicit_wait"))))
                .until(ExpectedConditions.attributeContains(locator, attribute, value));
    }

    public static boolean waitElementContainAttribute(WebDriver driver, WebElement element, String attribute, String value) {
        return new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(System.getProperty("explicit_wait"))))
                .until(ExpectedConditions.attributeContains(element, attribute, value));
    }

}
