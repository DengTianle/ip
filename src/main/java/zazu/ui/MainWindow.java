package zazu.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import zazu.Zazu;
import zazu.data.exception.ResponseException;
import zazu.parser.OutputFormatter;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Zazu zazu;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Bot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(OutputFormatter.printWelcome(), dukeImage, false));
    }

    /** Injects the Zazu instance */
    public void setZazu(Zazu z) {
        zazu = z;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        Boolean isError = false;

        try {
            response = zazu.getResponse(input);
        } catch(ResponseException e) {
            response = e.getMessage();
            isError = true;
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage, isError)
        );
        userInput.clear();
    }

    /**
     * Show a welcome message when the program starts.
     *
     * @param text message to display
     */
    @FXML
    public void showWelcomeMessage(String text) {
    }
}

