package ewallet.java.proj02;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
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
        gpWalletView.getColumnConstraints().add(new ColumnConstraints(500));
        gpWalletView.getColumnConstraints().add(new ColumnConstraints(300));
        gpWalletView.getRowConstraints().addAll(new RowConstraints(300), new RowConstraints(500), new RowConstraints(50));
        //CARDS PANE
        VBox vbCardsPanel = new VBox();
        Text tExpMonth = new Text("EXP Month");
        Text tExpDay = new Text("EXP Day");
        Text tExpYear = new Text("EXP Year");
        Text tLimit = new Text("Limit (credit) / Available Funds (debit)");
        TextField tfExpMonth = new TextField();
        TextField tfExpDay = new TextField();
        TextField tfExpYear = new TextField();
        TextField tfLimit = new TextField();
        ComboBox cbCardDropdown = new ComboBox();
        Label cardDescription = new Label("Creation Date: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque id ex eget diam fermentum viverra at rutrum dolor. Aliquam mattis, ex eu congue fringilla");

        //NOTES PANE
        VBox vbNotesPanel = new VBox();
        Text tMonthInput = new Text("Month");
        Text tDayInput = new Text("Day");
        Text tYearInput = new Text("Year");
        TextField tfMonth = new TextField();
        TextField tfDay = new TextField();
        TextField tfYear = new TextField();
        ComboBox cbNoteDropdown = new ComboBox();
        Label noteDescription = new Label("Creation Date: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque id ex eget diam fermentum viverra at rutrum dolor. Aliquam mattis, ex eu congue fringilla");

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
                profilePictureView.setImage(new Image("file:///" +walletPicture.getAbsolutePath()));
                profilePictureView.scaleXProperty();
                profilePictureView.scaleYProperty();
            }
        });



        noteDescription.setWrapText(true);
        vbNotesPanel.getChildren().addAll(new Text("My Notes"), cbNoteDropdown, noteDescription, tMonthInput, tfMonth,
                tDayInput, tfDay, tYearInput, tfYear);
        vbCardsPanel.getChildren().addAll(new Text("My Cards"), cbCardDropdown, cardDescription, tExpMonth, tfExpMonth,
                tExpDay, tfExpDay, tExpYear, tfExpYear, tLimit, tfLimit);
        gpWalletView.add(vbCardsPanel, 1, 0);
        gpWalletView.add(vbNotesPanel, 1, 1);
        gpWalletView.add(new VBox(profilePictureView, btnChooseFile), 0 , 0);
//        tfDay.applyCss("fx-backgroundColor");
        var scene = new Scene(gpWalletView, 800, 600);


        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }

}