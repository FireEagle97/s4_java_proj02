package ewallet.java.proj02.javafx;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CardWindow {

    private Scene scene;
    private Scene mainScene;
    private VBox vbCardPanel;


    public CardWindow(Stage mainStage, Scene mainScene) {
        mainStage.setTitle("Create a new Card");

        this.vbCardPanel = new VBox();
        Text tExpMonth = new Text("EXP Month");
        Text tExpDay = new Text("EXP Day");
        Text tExpYear = new Text("EXP Year");
        Text tLimit = new Text("Limit (credit) / Available Funds (debit)");
        TextField tfExpMonth = new TextField();
        TextField tfExpDay = new TextField();
        TextField tfExpYear = new TextField();
        TextField tfLimit = new TextField();
        Button btnAdd = new Button("Add Card");
        Button btnBack = new Button("Cancel");


        this.vbCardPanel.getChildren().addAll(tExpMonth, tfExpMonth, tExpDay, tfExpDay, tExpYear, tfExpYear, tLimit, tfLimit, btnAdd, btnBack);
        this.scene = new Scene(this.vbCardPanel, 400, 300);
        this.mainScene = mainScene;


        btnBack.setOnAction(evt -> {
            mainStage.setTitle("My E-wallet 2022");
            mainStage.setScene(this.mainScene);
        });

        //TODO get the controller to retrieve the input values and add functionality to tell model to create card.
        btnAdd.setOnAction(evt -> {
            mainStage.setScene(this.mainScene);
        });


    }

    public Scene getScene() {
        return this.scene;
    }


}

