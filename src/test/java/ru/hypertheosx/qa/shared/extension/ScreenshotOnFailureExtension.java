package ru.hypertheosx.qa.shared.extension;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.logging.LogType;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.Selenide;

import io.qameta.allure.Allure;

public class ScreenshotOnFailureExtension implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable throwable) {
        attachScreenshot();
        attachBrowserConsoleLogs();
    }

    private void attachScreenshot() {
        try {
            var screenshot = Screenshots.takeScreenShotAsFile();
            if (screenshot != null) {
                Allure.addAttachment("Screenshot on failure", new FileInputStream(screenshot));
            }
        } catch (IOException e) {
            Allure.step("Failed to attach screenshot: " + e.getMessage());
        }
    }

    private void attachBrowserConsoleLogs() {
        try {
            List<String> logEntries = Selenide.getWebDriverLogs(LogType.BROWSER, Level.ALL);
            if (!logEntries.isEmpty()) {
                String consoleLogs = String.join("\n", logEntries);
                Allure.addAttachment("Browser Console Logs", "text/plain", consoleLogs);
            }
        } catch (Exception e) {
            // Browser logs might not be supported or webdriver not initialized
        }
    }
}
