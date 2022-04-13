package ewallet.java.proj02;

import ewallet.java.proj02.javafx.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();


        GridPane gpWalletView = new GridPane();
        Scene mainScene = new Scene(gpWalletView, 800, 600);
        ComboBox cbCardDropdown = new ComboBox();
        ComboBox cbNoteDropdown = new ComboBox();

        WalletController walletC = new WalletController(cbCardDropdown, cbNoteDropdown);
        NoteWindow noteCreationScene = new NoteWindow(stage, mainScene, walletC);
        CardWindow cardCreationScene = new CardWindow(stage, mainScene, walletC);

        gpWalletView.getColumnConstraints().add(new ColumnConstraints(500));
        gpWalletView.getColumnConstraints().add(new ColumnConstraints(300));
        gpWalletView.getRowConstraints().addAll(new RowConstraints(300), new RowConstraints(500), new RowConstraints(50));

        //CARDS PANE
        VBox vbCardsPanel = new VBox();
        Label cardDescription = new Label("Creation Date: Lorem ipsum dolor sit amet,");
        Button btnAddCard = new Button("Add new Card");
        btnAddCard.setOnAction(e -> {
            stage.setScene(cardCreationScene.getScene());
        });


        //NOTES PANE
        VBox vbNotesPanel = new VBox();

        HBox hbNoteDeletionPane = new HBox();
        Button btnDeleteNote = new Button("Delete Note");
        TextField tfNoteIdInput = new TextField();
        hbNoteDeletionPane.getChildren().addAll(tfNoteIdInput, btnDeleteNote);
        tfNoteIdInput.setPromptText("Enter Note ID in list");
        btnDeleteNote.setOnAction(e -> walletC.handleNoteDeletion(tfNoteIdInput));

        Label lblNoteDescription = new Label("Creation Date: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque id ex eget diam fermentum viverra at rutrum dolor. Aliquam mattis, ex eu congue fringilla");
        lblNoteDescription.setWrapText(true);
        Button btnAddNote = new Button("Add Note");
        btnAddNote.setOnAction(evt -> stage.setScene(noteCreationScene.getScene()));

        cbNoteDropdown.setOnAction(evt -> {
            walletC.handleViewNote(lblNoteDescription);
        });

        //PROFILE PICTURE HOLDER
        ImageView profilePictureView = new ImageView();
        profilePictureView.setFitHeight(300);
        profilePictureView.setFitWidth(300);
        FileChooser picFileChooser = new FileChooser();
        picFileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.bmp"));
        picFileChooser.setTitle("Choose Akshan Picture:");
        Button btnChooseFile = new Button("chooseFile");
        btnChooseFile.setOnAction(e -> {
            File walletPicture = picFileChooser.showOpenDialog(stage);
            if (walletPicture != null) {
                profilePictureView.setImage(new Image("File:///" + walletPicture.getAbsolutePath()));
                profilePictureView.scaleXProperty();
                profilePictureView.scaleYProperty();
            }
        });


        vbNotesPanel.getChildren().addAll(cbNoteDropdown, lblNoteDescription, btnAddNote, hbNoteDeletionPane);
        vbCardsPanel.getChildren().addAll(cbCardDropdown, cardDescription, btnAddCard);
        gpWalletView.add(vbCardsPanel, 1, 0);
        gpWalletView.add(vbNotesPanel, 1, 1);
        gpWalletView.add(new VBox(profilePictureView, btnChooseFile), 0 , 0);
        stage.setScene(mainScene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }

}