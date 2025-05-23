package seedu.address.ui;


import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import javafx.util.Duration;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.HelpCommand;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2425s2-cs2103t-w12-3.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";
    private static final String LIGHT_THEME = "/view/LightTheme.css";
    private static final String DARK_THEME = "/view/DarkTheme.css";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private TextArea commandUsageArea;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        String commandUsages = HelpCommand.HELP_COMMANDS.stream()
                .collect(Collectors.joining("\n\n")); // Join commands with a double newline for separation
        commandUsageArea.setText(commandUsages);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }


    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);


        copyButton.setText("Copied!");


        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(event -> copyButton.setText("Copy URL"));
        pause.play();
    }

    public void setTheme(String currentTheme) {
        Scene scene = getRoot().getScene();
        scene.getRoot().getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource("/view/HelpWindow.css").toExternalForm());

        scene.getRoot().getStyleClass().removeAll("light", "dark");

        // Conditionally add the theme class and corresponding CSS
        if (currentTheme.equals(LIGHT_THEME)) {
            scene.getRoot().getStyleClass().add("light");
        } else if (currentTheme.equals(DARK_THEME)) {
            scene.getRoot().getStyleClass().add("dark");
        }

    }

}
