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
import javafx.geometry.Insets;


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
        WalletController walletC = new WalletController();
        walletC.getLblCashDisplay().setStyle("-fx-text-fill: #007700; -fx-font-size: 300%;");
        NoteWindow noteCreationScene = new NoteWindow(stage, mainScene, walletC);
        CardWindow cardCreationScene = new CardWindow(stage, mainScene, walletC);
        PaymentPanel paymentPanel = new PaymentPanel(walletC, walletC.getLblCashDisplay());
        //gpWalletView.setHgap(10); 
        gpWalletView.setVgap(10);
        gpWalletView.setPadding(new Insets(10, 10, 10, 10));
        gpWalletView.getColumnConstraints().add(new ColumnConstraints(400));
        gpWalletView.getRowConstraints().addAll(new RowConstraints(350));

        //CARDS PANE
        Label lblCardsHeader = new Label("My Cards");
        lblCardsHeader.setStyle("-fx-font-size: 200%;");
        VBox vbCardsPanel = new VBox();
        //Label lblCardDescription = new Label("Creation Date: Lorem ipsum dolor sit amet,");
        Label lblCardDescription = new Label("");
        Button btnAddCard = new Button("Add new Card");
        //HBox hbCardDeletionPane = new HBox();
        //Button btnDeleteCard = new Button("Delete Card");
        //TextField tfCardIdInput = new TextField();
        //hbCardDeletionPane.getChildren().addAll(tfCardIdInput,btnDeleteCard);
        //tfCardIdInput.setPromptText("Enter Card ID in list");
//        btnDeleteCard.setOnAction(evt -> {
//            lblCardDescription.setText("");
//            walletC.handleCardDeletion(lblCardDescription.getText());
//        });
        btnAddCard.setOnAction(evt -> {
            stage.setScene(cardCreationScene.getScene());
        });

        walletC.getCbCards().setOnAction(evt ->{
                Pane cardView = new Pane();
                Button btndeleteCard = new Button("Delete this card");
                cardView.getChildren().add(walletC.handleViewCard(lblCardDescription));
                cardView.getChildren().add(btndeleteCard);
                Stage stage1 = new Stage();
                btndeleteCard.setOnAction(evt1 -> {
                    stage1.close();
                    lblCardDescription.setText("");
                    walletC.handleCardDeletion((String)walletC.getCbCards().getValue());
                });
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
        //Button btnDeleteNote = new Button("Delete Note");
        //TextField tfNoteIdInput = new TextField();
       // hbNoteDeletionPane.getChildren().addAll(tfNoteIdInput, btnDeleteNote);
        //tfNoteIdInput.setPromptText("Enter Note ID in list");
        Label lblNoteDescription = new Label("");
        
        //Label lblNoteDescription = new Label("Creation Date: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque id ex eget diam fermentum viverra at rutrum dolor. Aliquam mattis, ex eu congue fringilla");
        lblNoteDescription.setWrapText(true);
        Button btnAddNote = new Button("Add Note");
        btnAddNote.setOnAction(evt -> stage.setScene(noteCreationScene.getScene()));
//        btnDeleteNote.setOnAction(e -> {
//            lblNoteDescription.setText("");
//            walletC.handleNoteDeletion(tfNoteIdInput);
//        });
        walletC.getCbNotes().setOnAction(evt -> {
                Pane noteView = new Pane();
                Button btnDeleteNote1 = new Button("Delete this note");
                noteView.getChildren().add(walletC.handleViewNote(lblNoteDescription));
                noteView.getChildren().add(btnDeleteNote1);
                Stage stage1 = new Stage();
                btnDeleteNote1.setOnAction(evt1 -> {
                    stage1.close();
                    lblNoteDescription.setText("");
                    walletC.handleNoteDeletion((String)walletC.getCbNotes().getValue());
                });
                Scene scene1 = new Scene(noteView,300,150);
                stage1.setTitle("Show Note");
                stage1.setScene(scene1);
                stage1.show();
//                stage1.setOnCloseRequest(evt1 -> {
//                    walletC.updateNoteDropdownList();
//                });
                
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


        vbNotesPanel.getChildren().addAll(lblNoteHeader, walletC.getCbNotes(), lblNoteDescription, btnAddNote/*, hbNoteDeletionPane*/);
        //vbNotesPanel.getChildren().add(btnShowNotes);
        vbCardsPanel.getChildren().addAll(lblCardsHeader, walletC.getCbCards(), lblCardDescription, btnAddCard/*hbCardDeletionPane*/);
        gpWalletView.add(vbCardsPanel, 0, 1);
        gpWalletView.add(vbNotesPanel, 1, 1);
        gpWalletView.add(paymentPanel.getVbPayment(), 1, 0);
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