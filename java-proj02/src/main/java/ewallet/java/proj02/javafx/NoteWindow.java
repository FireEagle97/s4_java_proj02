package ewallet.java.proj02.javafx;

import ewallet.java.proj02.WalletController;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NoteWindow {

    private Scene scene;
    private Scene mainScene;
    private VBox vbNotesPanel;

    private TextField tfMonth;
    private TextField tfDay;
    private TextField tfYear;
    private TextArea taNoteBody;
    private WalletController walletC;

    public NoteWindow(Stage mainStage, Scene mainScene, WalletController walletC) {
        mainStage.setTitle("Create a new Note");
        this.vbNotesPanel = new VBox();
        this.tfMonth = new TextField();
        this.tfDay = new TextField();
        this.tfYear = new TextField();

        Text tMonthInput = new Text("Month");
        Text tDayInput = new Text("Day");
        Text tYearInput = new Text("Year");

        this.taNoteBody = new TextArea();
        this.taNoteBody.setPromptText("// Write your note here...");
        this.taNoteBody.setWrapText(true);
        Button btnAdd = new Button("Add Note");
        Button btnBack = new Button("Cancel");


        this.vbNotesPanel.getChildren().addAll(tMonthInput, this.tfMonth, tDayInput, this.tfDay, tYearInput, this.tfYear, this.taNoteBody, btnAdd, btnBack);
        this.scene = new Scene(this.vbNotesPanel, 400, 300);
        this.mainScene = mainScene;
        this.walletC = walletC;

        btnBack.setOnAction(evt -> {
            mainStage.setScene(this.mainScene);
        });

        //TODO get the controller to retrieve the input values and add functionality to tell model to create card.
        btnAdd.setOnAction(evt -> {
            mainStage.setTitle("My E-wallet 2022");
            mainStage.setScene(this.mainScene);
            this.walletC.handleNoteCreationInput(this);
        });


    }

    public Scene getScene() {
        return this.scene;
    }

    public String getMonthInput() {
        return this.tfMonth.getText();
    }

    public String getDayInput() {
        return this.tfDay.getText();
    }

    public String getYearInput() {
        return this.tfYear.getText();
    }

    public String getNoteBodyInput() {
        return this.taNoteBody.getText();
    }
}
