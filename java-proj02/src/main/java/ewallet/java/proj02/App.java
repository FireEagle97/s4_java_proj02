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
import javafx.scene.control.ListView;

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
        gpWalletView.setStyle("-fx-backgroundColor: #ff0000");
        Scene mainScene = new Scene(gpWalletView, 800, 650);
        //ComboBox cbCardDropdown = new ComboBox();
        //ComboBox cbNoteDropdown = new ComboBox();
        Label lblCashDisplay = new Label("$69.00");
        lblCashDisplay.setStyle("-fx-text-fill: #007700; -fx-font-size: 300%;");
        WalletController walletC = new WalletController(lblCashDisplay);
        NoteWindow noteCreationScene = new NoteWindow(stage, mainScene, walletC);
        CardWindow cardCreationScene = new CardWindow(stage, mainScene, walletC);
        PaymentPanel paymentPanel = new PaymentPanel(walletC, lblCashDisplay);

        gpWalletView.getColumnConstraints().add(new ColumnConstraints(500));
        gpWalletView.getColumnConstraints().add(new ColumnConstraints(300));
        gpWalletView.getRowConstraints().addAll(new RowConstraints(300), new RowConstraints(300), new RowConstraints(50));

        //CARDS PANE
        Label lblCardsHeader = new Label("My Cards");
        lblCardsHeader.setStyle("-fx-font-size: 200%;");
        VBox vbCardsPanel = new VBox();
        Label lblCardDescription = new Label("Creation Date: Lorem ipsum dolor sit amet,");
        Button btnAddCard = new Button("Add new Card");
        HBox hbCardDeletionPane = new HBox();
        Button btnDeleteCard = new Button("Delete Card");
        TextField tfCardIdInput = new TextField();
        hbCardDeletionPane.getChildren().addAll(tfCardIdInput, btnDeleteCard);
        tfCardIdInput.setPromptText("Enter Card ID in list");
        btnDeleteCard.setOnAction(evt -> {
            lblCardDescription.setText("");
            walletC.handleCardDeletion(tfCardIdInput);
        });
        btnAddCard.setOnAction(evt -> {
            stage.setScene(cardCreationScene.getScene());
        });

        walletC.getCbCards().setOnAction(evt ->{
                Pane cardView = new Pane();
                Button deleteNote = new Button("Delete this card");
                cardView.getChildren().add(walletC.handleViewCard(lblCardDescription));
                cardView.getChildren().add(deleteNote);
                Stage stage1 = new Stage();
                Scene scene1 = new Scene(cardView,300,150);
                stage1.setTitle("Show Card");
                stage1.setScene(scene1);
                stage1.show();
            
        });


        //NOTES PANE
        Label lblNoteHeader = new Label("My Notes");
        lblNoteHeader.setStyle("-fx-font-size: 200%; ");
        VBox vbNotesPanel = new VBox();
        vbNotesPanel.applyCss();
        HBox hbNoteDeletionPane = new HBox();
        Button btnDeleteNote = new Button("Delete Note");
        TextField tfNoteIdInput = new TextField();
        hbNoteDeletionPane.getChildren().addAll(tfNoteIdInput, btnDeleteNote);
        tfNoteIdInput.setPromptText("Enter Note ID in list");
        
        Label lblNoteDescription = new Label("Creation Date: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque id ex eget diam fermentum viverra at rutrum dolor. Aliquam mattis, ex eu congue fringilla");
        lblNoteDescription.setWrapText(true);
        Button btnAddNote = new Button("Add Note");
        btnAddNote.setOnAction(evt -> stage.setScene(noteCreationScene.getScene()));
        btnDeleteNote.setOnAction(e -> {
            lblNoteDescription.setText("");
            walletC.handleNoteDeletion(tfNoteIdInput);
        });
        walletC.getCbNotes().setOnAction(evt -> {
                Pane noteView = new Pane();
                Button deleteNote = new Button("Delete this note");
                noteView.getChildren().add(walletC.handleViewNote(lblNoteDescription));
                //walletC.updateNoteDropdownList();
                noteView.getChildren().add(deleteNote);
                Stage stage1 = new Stage();
                Scene scene1 = new Scene(noteView,300,150);
                stage1.setTitle("Show Note");
                stage1.setScene(scene1);
                stage1.show();
                stage1.setOnCloseRequest(evt1 -> {
                    walletC.updateNoteDropdownList();
                });
                
        });
        
        

        //PAYMENT WINDOW


        //PROFILE PICTURE HOLDER
        ImageView profilePictureView = new ImageView();
        profilePictureView.setFitHeight(250);
        profilePictureView.setFitWidth(250);
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

        //LOAD button from DB
        Button btnSaveWallet = new Button("Save Wallet");
        btnSaveWallet.setOnAction(evt -> walletC.handleSaveWallet());
        Button btnLoadWallet = new Button("Load Wallet");
        btnLoadWallet.setOnAction(evt -> walletC.handleLoadWallet());


        vbNotesPanel.getChildren().addAll(lblNoteHeader, walletC.getCbNotes(), lblNoteDescription, btnAddNote, hbNoteDeletionPane);
        //vbNotesPanel.getChildren().add(btnShowNotes);
        vbCardsPanel.getChildren().addAll(lblCardsHeader, walletC.getCbCards(), lblCardDescription, btnAddCard, hbCardDeletionPane);
        gpWalletView.add(vbCardsPanel, 1, 0);
        gpWalletView.add(vbNotesPanel, 1, 1);
        gpWalletView.add(paymentPanel.getVbPayment(), 0, 1);
        gpWalletView.add(new VBox(profilePictureView, btnChooseFile), 0 , 0);
        gpWalletView.add(btnLoadWallet, 0, 2);
        gpWalletView.add(btnSaveWallet, 1, 2);
        stage.setScene(mainScene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}