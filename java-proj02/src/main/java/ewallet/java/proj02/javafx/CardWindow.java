package ewallet.java.proj02.javafx;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
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
        Text tExpYear = new Text("EXP Year");
        Text tLimit = new Text("Limit (credit) / Available Funds (debit)");
        Text tCardDescription = new Text("description eg. Driver's Licence, Health card ...");


        TextField tfExpMonth = new TextField();
        TextField tfExpYear = new TextField();
        TextField tfLimit = new TextField();
        TextField tfCardDescription = new TextField();

        Button btnAdd = new Button("Add Card");
        Button btnBack = new Button("Cancel");

        RadioButton rbDebit = new RadioButton("Debit Card");
        RadioButton rbCredit = new RadioButton("Credit Card");
        RadioButton rbPersonal = new RadioButton("Personal Card");
        ToggleGroup radioButtons = new ToggleGroup();
        HBox hbButtonsPane = new HBox();
        hbButtonsPane.setAlignment(Pos.CENTER);
        hbButtonsPane.setSpacing(5.0);
        rbDebit.setToggleGroup(radioButtons);
        rbCredit.setToggleGroup(radioButtons);
        rbPersonal.setToggleGroup(radioButtons);
        rbDebit.setSelected(true);
        hbButtonsPane.getChildren().addAll(rbDebit, rbCredit, rbPersonal);


        this.vbCardPanel.getChildren().addAll(hbButtonsPane, tExpMonth, tfExpMonth, tExpYear, tfExpYear,
                tCardDescription, tfCardDescription,  tLimit, tfLimit, btnAdd, btnBack);
        this.scene = new Scene(this.vbCardPanel, 400, 300);
        this.mainScene = mainScene;

        rbPersonal.setOnAction(evt -> {
            tCardDescription.setVisible(rbPersonal.isSelected());
            tfCardDescription.setVisible(rbPersonal.isSelected());
            tLimit.setVisible(!rbPersonal.isSelected());
            tfLimit.setVisible(!rbPersonal.isSelected());

        });
        rbDebit.setOnAction(evt -> {
            tCardDescription.setVisible(rbPersonal.isSelected());
            tfCardDescription.setVisible(rbPersonal.isSelected());
            tLimit.setVisible(!rbPersonal.isSelected());
            tfLimit.setVisible(!rbPersonal.isSelected());
        });
        rbCredit.setOnAction(evt -> {
            tCardDescription.setVisible(rbPersonal.isSelected());
            tfCardDescription.setVisible(rbPersonal.isSelected());
            tLimit.setVisible(!rbPersonal.isSelected());
            tfLimit.setVisible(!rbPersonal.isSelected());
        });

        btnBack.setOnAction(evt -> {
            mainStage.setTitle("My E-wallet 2022");
            mainStage.setScene(this.mainScene);
        });

        //TODO get the controller to retrieve the input values and add functionality to tell model to create card.
        btnAdd.setOnAction(evt -> {
            mainStage.setScene(this.mainScene);
            if (rbCredit.isSelected()) {
                System.out.println("akshan credit");
            } else if (rbDebit.isSelected()) {
                System.out.println("akshan debit");
            } else {
                System.out.println("Personal Card");
            }
        });


    }

    public Scene getScene() {
        return this.scene;
    }


}

