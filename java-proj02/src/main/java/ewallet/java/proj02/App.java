package ewallet.java.proj02;

import ewallet.java.proj02.javafx.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
        WalletController walletC = new WalletController();
        NoteWindow noteCreationScene = new NoteWindow(stage, mainScene, walletC);
        CardWindow cardCreationScene = new CardWindow(stage, mainScene);

        gpWalletView.getColumnConstraints().add(new ColumnConstraints(500));
        gpWalletView.getColumnConstraints().add(new ColumnConstraints(300));
        gpWalletView.getRowConstraints().addAll(new RowConstraints(300), new RowConstraints(500), new RowConstraints(50));

        //CARDS PANE
        VBox vbCardsPanel = new VBox();
        ComboBox cbCardDropdown = new ComboBox();
        Label cardDescription = new Label("Creation Date: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque id ex eget diam fermentum viverra at rutrum dolor. Aliquam mattis, ex eu congue fringilla");

        Button btnAddCard = new Button("Add new Card");
        btnAddCard.setOnAction(e -> {
            stage.setScene(cardCreationScene.getScene());
        });


        //NOTES PANE
        VBox vbNotesPanel = new VBox();

        ComboBox cbNoteDropdown = new ComboBox();

        Label noteDescription = new Label("Creation Date: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque id ex eget diam fermentum viverra at rutrum dolor. Aliquam mattis, ex eu congue fringilla");
        noteDescription.setWrapText(true);
        Button btnAddNote = new Button("Add Note");
        btnAddNote.setOnAction(e -> {
            stage.setScene(noteCreationScene.getScene());
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
                System.out.println(walletPicture.getAbsolutePath());
                profilePictureView.setImage(new Image(walletPicture.getAbsolutePath()));
                profilePictureView.scaleXProperty();
                profilePictureView.scaleYProperty();
            }
        });


        vbNotesPanel.getChildren().addAll(cbNoteDropdown, noteDescription, btnAddNote);
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