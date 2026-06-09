package ru.hypertheosx.qa.extensions;

import com.codeborne.selenide.Screenshots;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.FileInputStream;
import java.io.IOException;

public class ScreenshotOnFailureExtension implements TestWatcher {

    @Override
    public void testFailed(
            ExtensionContext context,
            Throwable throwable
    ) {
        try {
            var screenshot = Screenshots.takeScreenShotAsFile();

            if (screenshot != null) {
                Allure.addAttachment(
                        "Screenshot",
                        new FileInputStream(
                                screenshot
                        )
                );
            }
        } catch (IOException e) {
            Allure.step("Не удалось прикрепить скриншот: "
                    + e.getMessage());
        }
    }
}
