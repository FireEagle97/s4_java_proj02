package ewallet.java.proj02.javafx;

import ewallet.java.proj02.WalletController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This represents a window for creating cards as well as the buttons to go back and perform adding a card
 * Users can choose the type of card to create (either debit/credit/personal) and display all the necessary fields.
 */
public class CardWindow {

    private Scene scene;
    private Scene mainScene;
    private VBox vbCardPanel;

    private TextField tfExpMonth;
    private TextField tfExpYear;
    private TextField tfLimit;
    private TextField tfCardDescription;

    private RadioButton rbDebit;
    private RadioButton rbCredit;
    private RadioButton rbPersonal;

    private WalletController walletC;

    public CardWindow(Stage mainStage, Scene mainScene, WalletController walletC) {
        mainStage.setTitle("Create a new Card");

        this.vbCardPanel = new VBox();
        Text tExpMonth = new Text("EXP Month");
        Text tExpYear = new Text("EXP Year");
        Text tLimit = new Text("Limit (credit) / Available Funds (debit)");
        Text tCardDescription = new Text("description eg. Driver's Licence, Health card ...");
        Button btnAdd = new Button("Add Card");
        Button btnBack = new Button("Cancel");

        Label lblError = new Label();
        lblError.setStyle("-fx-text-fill: #FF0000; -fx-font-size: 150%;");

        this.tfExpMonth = new TextField();
        this.tfExpYear = new TextField();
        this.tfLimit = new TextField();
        this.tfCardDescription = new TextField();

        this.rbDebit = new RadioButton("Debit Card");
        this.rbCredit = new RadioButton("Credit Card");
        this.rbPersonal = new RadioButton("Personal Card");

        ToggleGroup radioButtons = new ToggleGroup();
        HBox hbButtonsPane = new HBox();
        hbButtonsPane.setAlignment(Pos.CENTER);
        hbButtonsPane.setSpacing(5.0);
        this.rbDebit.setToggleGroup(radioButtons);
        this.rbCredit.setToggleGroup(radioButtons);
        this.rbPersonal.setToggleGroup(radioButtons);
        rbDebit.setSelected(true);
        hbButtonsPane.getChildren().addAll(this.rbDebit, this.rbCredit, this.rbPersonal);

        this.vbCardPanel.getChildren().addAll(hbButtonsPane, tExpMonth, this.tfExpMonth, tExpYear, this.tfExpYear,
                tCardDescription, this.tfCardDescription,  tLimit, this.tfLimit, lblError, btnAdd, btnBack);
        tCardDescription.setVisible(false);
        this.tfCardDescription.setVisible(false);
        this.scene = new Scene(this.vbCardPanel, 400, 300);
        this.mainScene = mainScene;
        this.walletC = walletC;

        rbPersonal.setOnAction(evt -> {
            updateCardInputFieldDisplay(tCardDescription, tLimit);
        });
        rbDebit.setOnAction(evt -> {
            updateCardInputFieldDisplay(tCardDescription, tLimit);
        });
        rbCredit.setOnAction(evt -> {
            updateCardInputFieldDisplay(tCardDescription, tLimit);
        });

        btnBack.setOnAction(evt -> {
            mainStage.setTitle("My E-wallet 2022");
            mainStage.setScene(this.mainScene);
        });


        btnAdd.setOnAction(evt -> {
            try {
                this.walletC.handleCardCreationInput(this);
                mainStage.setScene(this.mainScene);
                lblError.setText("");
            } catch (IllegalArgumentException exc) {
                lblError.setText(exc.getMessage());
            }
        });


    }

    private void updateCardInputFieldDisplay(Text tCardDescription, Text tLimit) {
        tCardDescription.setVisible(rbPersonal.isSelected());
        tfCardDescription.setVisible(rbPersonal.isSelected());
        tLimit.setVisible(!rbPersonal.isSelected());
        tfLimit.setVisible(!rbPersonal.isSelected());
    }

    public Scene getScene() {
        return this.scene;
    }

    public String getTfExpYear() {
        return this.tfExpYear.getText();
    }

    public String getTfExpMonth() {
        return this.tfExpMonth.getText();
    }

    public String getTfLimit() {
        return this.tfLimit.getText();
    }

    public String getTfCardDescription() {
        return this.tfCardDescription.getText();
    }

    public boolean rbDebitIsSelected() {
        return this.rbDebit.isSelected();
    }

    public boolean rbCreditIsSelected() {
        return this.rbCredit.isSelected();
    }

    public boolean rbPersonalIsSelected() {
        return this.rbPersonal.isSelected();
    }
}

