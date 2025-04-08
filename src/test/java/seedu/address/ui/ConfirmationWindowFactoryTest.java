package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.ui.ConfirmationWindowFactory.ConfirmationWindowProvider;

/**
 * Unit tests for ConfirmationWindowFactory, with reference from Copilot
 */
public class ConfirmationWindowFactoryTest {

    private MockConfirmationWindowProvider mockProvider;
    private StubConfirmationWindow stubWindow;

    @BeforeEach
    public void setUp() {
        // Save the factory's initial state
        ConfirmationWindowFactory.resetProvider();

        // Create test doubles
        stubWindow = new StubConfirmationWindow();
        mockProvider = new MockConfirmationWindowProvider(stubWindow);
    }

    @AfterEach
    public void tearDown() {
        // Reset factory to default state after each test
        ConfirmationWindowFactory.resetProvider();
    }

    @Test
    public void getConfirmationWindow_withCustomProvider_returnsCustomWindow() {
        // Set our custom provider
        ConfirmationWindowFactory.setProvider(mockProvider);

        // Get a window from the factory
        ConfirmationWindow window = ConfirmationWindowFactory.getConfirmationWindow();

        // Should be our stub window
        assertSame(stubWindow, window);
        assertEquals(1, mockProvider.getCallCount(), "Provider should be called exactly once");
    }

    @Test
    public void getConfirmationWindow_calledMultipleTimes_delegatesToProvider() {
        // Set our custom provider
        ConfirmationWindowFactory.setProvider(mockProvider);

        // Call getConfirmationWindow multiple times
        ConfirmationWindow window1 = ConfirmationWindowFactory.getConfirmationWindow();
        ConfirmationWindow window2 = ConfirmationWindowFactory.getConfirmationWindow();
        ConfirmationWindow window3 = ConfirmationWindowFactory.getConfirmationWindow();

        // Verify the provider was called each time
        assertEquals(3, mockProvider.getCallCount());

        // And returned our stub each time
        assertSame(stubWindow, window1);
        assertSame(stubWindow, window2);
        assertSame(stubWindow, window3);
    }

    /**
     * A stub implementation of ConfirmationWindow for testing
     */
    private static class StubConfirmationWindow implements ConfirmationWindow {
        private boolean showing = false;
        private String message;

        @Override
        public boolean showAndWait() {
            showing = true;
            return true;
        }

        @Override
        public boolean isShowing() {
            return showing;
        }

        @Override
        public void focus() {
            // Do nothing for the stub
        }

        @Override
        public void setMessage(String message) {
            this.message = message;
        }


    }

    /**
     * A mock ConfirmationWindowProvider that counts how many times it's called
     */
    private static class MockConfirmationWindowProvider implements ConfirmationWindowProvider {
        private final ConfirmationWindow windowToReturn;
        private int callCount = 0;

        public MockConfirmationWindowProvider(ConfirmationWindow windowToReturn) {
            this.windowToReturn = windowToReturn;
        }

        @Override
        public ConfirmationWindow getConfirmationWindow() {
            callCount++;
            return windowToReturn;
        }

        public int getCallCount() {
            return callCount;
        }
    }
}
