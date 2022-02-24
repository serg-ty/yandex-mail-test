package ru.yandex.mail.tests.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ChromeTestsConfiguration {

    protected WebDriver driver;

    @BeforeAll
    static void initDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    @Step("Инициализация WebDriver")
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterEach
    @Step("Закрытие окна браузера")
    void tearDown() {
        driver.quit();
    }
}
