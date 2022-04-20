package ewallet.java.proj02;

import ewallet.java.proj02.javafx.*;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.util.Duration;


import java.io.File;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        GridPane gpWalletView = new GridPane();
        gpWalletView.setStyle("-fx-background-color: #d7ffd7");
        Scene mainScene = new Scene(gpWalletView, 650, 450);
        WalletController walletC = new WalletController();
        walletC.getLblCashDisplay().setStyle("-fx-text-fill: #007700; -fx-font-size: 300%;");
        NoteWindow noteCreationScene = new NoteWindow(stage, mainScene, walletC);
        CardWindow cardCreationScene = new CardWindow(stage, mainScene, walletC);
        PaymentPanel paymentPanel = new PaymentPanel(walletC, walletC.getLblCashDisplay());
        //gpWalletView.setHgap(10); 
        gpWalletView.setVgap(10);
        gpWalletView.setPadding(new Insets(10, 10, 10, 10));
        gpWalletView.getColumnConstraints().add(new ColumnConstraints(300));
        gpWalletView.getRowConstraints().addAll(new RowConstraints(300));

        //CARDS PANE
        Label lblCardsHeader = new Label("My Cards");
        lblCardsHeader.setStyle("-fx-font-size: 200%;");
        VBox vbCardsPanel = new VBox();
        vbCardsPanel.setSpacing(10);
        HBox hbCN = new HBox();
        hbCN.setSpacing(20);
        HBox hbLS = new HBox();
        hbLS.setSpacing(20);
        VBox vbLeftSection = new VBox();
        vbLeftSection.setSpacing(10);
        VBox vbRightSection = new VBox();
        Label lblCardDescription = new Label("");
        Button btnAddCard = new Button("Add new Card");
  
        btnAddCard.setOnAction(evt -> {
            stage.setScene(cardCreationScene.getScene());
        });

        walletC.getCbCards().setOnAction(evt ->{
                StackPane cardView = new StackPane();
                cardView.setPadding(new Insets(10, 10, 10, 10));
                Button btndeleteCard = new Button("Delete this card");
                VBox vbCardInfo = new VBox();
                vbCardInfo.setSpacing(10);
                vbCardInfo.getChildren().addAll(walletC.handleViewCard(lblCardDescription),btndeleteCard);
                cardView.getChildren().add(vbCardInfo);
                cardView.setAlignment(vbCardInfo,Pos.CENTER);
                Stage stage1 = new Stage();
                btndeleteCard.setOnAction(evt1 -> {
                    stage1.close();
                    lblCardDescription.setText("");
                    walletC.handleCardDeletion((String)walletC.getCbCards().getValue());
                });
                Scene scene1 = new Scene(cardView,180,200);
                stage1.setTitle("Show Card");
                stage1.setScene(scene1);
                stage1.show();
                
            
        });



        //NOTES PANE
        Label lblNoteHeader = new Label("My Notes");
        lblNoteHeader.setStyle("-fx-font-size: 200%; ");
        VBox vbNotesPanel = new VBox();
        vbNotesPanel.setSpacing(10);
        vbNotesPanel.applyCss();
        Button btnListNotes = new Button("Print notes to console (uses another thread)");
        Label lblNoteDescription = new Label("Note Description...");
        lblNoteDescription.setWrapText(true);
        Button btnAddNote = new Button("Add Note");
        
        
        btnAddNote.setOnAction(evt -> stage.setScene(noteCreationScene.getScene()));
        btnAddNote.setOnAction(evt -> stage.setScene(noteCreationScene.getScene()));
        btnListNotes.setOnAction(evt -> walletC.handlePrintAllNotes());

        walletC.getCbNotes().setOnAction(evt -> {
                StackPane noteView = new StackPane();
                noteView.setPadding(new Insets(10, 10, 10, 10));
                VBox vbNoteInfo = new VBox();
                vbNoteInfo.setSpacing(10);
                Button btnDeleteNote1 = new Button("Delete this note");
                vbNoteInfo.getChildren().addAll(walletC.handleViewNote(lblNoteDescription), btnDeleteNote1);
                noteView.getChildren().add(vbNoteInfo);
                noteView.setAlignment(vbNoteInfo,Pos.CENTER);
                Stage stage1 = new Stage();
                btnDeleteNote1.setOnAction(evt1 -> {
                    stage1.close();
                    lblNoteDescription.setText("");
                    walletC.handleNoteDeletion((String)walletC.getCbNotes().getValue());
                });
                Scene scene1 = new Scene(noteView,250,150);
                stage1.setTitle("Show Note");
                stage1.setScene(scene1);
                stage1.show();

                
        });
        
        

        //PAYMENT WINDOW


        //PROFILE PICTURE HOLDER
        ImageView profilePictureView = new ImageView();
        profilePictureView.setFitHeight(250);
        profilePictureView.setFitWidth(250);
        FileChooser picFileChooser = new FileChooser();
        picFileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.bmp"));
        picFileChooser.setTitle("Choose Akshan Picture:");
        Button btnChooseFile = new Button("Choose a Wallet Picture");
        btnChooseFile.setOnAction(e -> {
            File walletPicture = picFileChooser.showOpenDialog(stage);
            if (walletPicture != null) {
                profilePictureView.setImage(new Image("File:///" + walletPicture.getAbsolutePath()));
                profilePictureView.scaleXProperty();
                profilePictureView.scaleYProperty();
                PathTransition pt = new PathTransition(Duration.millis(500), new Line(100, -100, 100, 100), profilePictureView);
                PathTransition pt2 = new PathTransition(Duration.millis(200), new Line(100, 100, 100, 70), profilePictureView);
                PathTransition pt3 = new PathTransition(Duration.millis(200), new Line(100, 70, 100, 100), profilePictureView);
                SequentialTransition bouncingAnimation = new SequentialTransition(pt, pt2, pt3);
                bouncingAnimation.play();
            }
        });

        //LOAD button from DB
        Button btnSaveWallet = new Button("Save Wallet");
        btnSaveWallet.setOnAction(evt -> walletC.handleSaveWallet());
        Button btnLoadWallet = new Button("Load Wallet");
        btnLoadWallet.setOnAction(evt -> walletC.handleLoadWallet());
        
        
        vbNotesPanel.getChildren().addAll(lblNoteHeader, btnAddNote,walletC.getCbNotes(), lblNoteDescription /*, hbNoteDeletionPane*/);
        vbCardsPanel.getChildren().addAll(lblCardsHeader, btnAddCard,walletC.getCbCards(), lblCardDescription /*hbCardDeletionPane*/);
        hbCN.getChildren().addAll(vbNotesPanel,vbCardsPanel);
        hbLS.getChildren().addAll(btnLoadWallet,btnSaveWallet);
        vbLeftSection.getChildren().addAll(profilePictureView, btnChooseFile, hbLS, btnListNotes);
        gpWalletView.add(vbRightSection,1,0);
        gpWalletView.add(hbCN,1,1);
        gpWalletView.add(hbLS, 0, 1);
        gpWalletView.add(paymentPanel.getVbPayment(), 1, 0);
        gpWalletView.add(vbLeftSection, 0 , 0);
        stage.setScene(mainScene);
        stage.setTitle("My eWallet");
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}