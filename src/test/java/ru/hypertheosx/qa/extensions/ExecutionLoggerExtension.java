package ru.hypertheosx.qa.extensions;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ExecutionLoggerExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final String START_TIME = "startTime";

    @Override
    public void beforeTestExecution(
            ExtensionContext context
    ) {
        context
                .getStore(ExtensionContext.Namespace.GLOBAL)
                .put(START_TIME, System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        Long startTime = context.getStore(ExtensionContext.Namespace.GLOBAL)
                .get(START_TIME, Long.class);

    long duration = System.currentTimeMillis() - startTime;

        System.out.println(context.getDisplayName() + " took " + duration + " ms");
    }
}