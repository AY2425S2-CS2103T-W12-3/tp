package seedu.address.ui;

/**
 * Factory for creating ConfirmationWindow instances. With inspiration from Copilot.
 */
public class ConfirmationWindowFactory {
    private static ConfirmationWindowProvider provider = new DefaultConfirmationWindowProvider();

    /**
     * Sets a custom provider for testing
     */
    public static void setProvider(ConfirmationWindowProvider testProvider) {
        assert testProvider != null;
        provider = testProvider;
    }

    /**
     * Gets a confirmation window instance
     */
    public static ConfirmationWindow getConfirmationWindow() {
        assert provider != null;
        return provider.getConfirmationWindow();
    }

    /**
     * Resets to the default provider
     */
    public static void resetProvider() {
        provider = new DefaultConfirmationWindowProvider();
    }

    /**
     * Interface for confirmation window providers
     */
    public interface ConfirmationWindowProvider {
        ConfirmationWindow getConfirmationWindow();
    }

    /**
     * Default provider that returns the real confirmation window
     */
    private static class DefaultConfirmationWindowProvider implements ConfirmationWindowProvider {
        @Override
        public ConfirmationWindow getConfirmationWindow() {
            return ClearConfirmationWindow.getInstance();
        }
    }
}
