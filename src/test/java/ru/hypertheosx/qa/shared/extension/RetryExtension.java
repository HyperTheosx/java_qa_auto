package ru.hypertheosx.qa.shared.extension;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RetryExtension implements TestTemplateInvocationContextProvider {

    private static final Logger log = LoggerFactory.getLogger(RetryExtension.class);

    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
        return context.getTestMethod()
                .map(method -> method.isAnnotationPresent(RetryTest.class))
                .orElse(false);
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
        int maxAttempts = context.getTestMethod()
                .map(method -> method.getAnnotation(RetryTest.class).value())
                .orElse(1);

        return IntStream.rangeClosed(1, maxAttempts)
                .mapToObj(index -> new RetryInvocationContext(index, maxAttempts));
    }

    private static class RetryInvocationContext implements TestTemplateInvocationContext {
        private final int attempt;
        private final int maxAttempts;

        public RetryInvocationContext(int attempt, int maxAttempts) {
            this.attempt = attempt;
            this.maxAttempts = maxAttempts;
        }

        @Override
        public String getDisplayName(int invocationIndex) {
            return "Attempt " + attempt + " of " + maxAttempts;
        }

        @Override
        public List<Extension> getAdditionalExtensions() {
            return Collections.singletonList((TestExecutionExceptionHandler) (context, throwable) -> {
                log.warn("Attempt {} of {} failed for test: {}", attempt, maxAttempts, context.getDisplayName());
                if (attempt < maxAttempts) {
                    return;
                }
                throw throwable;
            });
        }
    }
}
