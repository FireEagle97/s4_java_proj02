package ewallet.java.proj02.javafx;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NoteWindow {

    private Scene scene;
    private Scene mainScene;
    private VBox vbNotesPanel;

    public NoteWindow(Stage mainStage, Scene mainScene) {
        mainStage.setTitle("Create a new Note");
        this.vbNotesPanel = new VBox();

        Text tMonthInput = new Text("Month");
        Text tDayInput = new Text("Day");
        Text tYearInput = new Text("Year");
        TextField tfMonth = new TextField();
        TextField tfDay = new TextField();
        TextField tfYear = new TextField();
        TextArea noteBody = new TextArea();
        noteBody.setPromptText("// Write your note here...");
        noteBody.setWrapText(true);
        Button btnAdd = new Button("Add Note");
        Button btnBack = new Button("Cancel");


        this.vbNotesPanel.getChildren().addAll(tMonthInput, tfMonth, tDayInput, tfDay, tYearInput, tfYear, noteBody, btnAdd, btnBack);
        this.scene = new Scene(this.vbNotesPanel, 400, 300);
        this.mainScene = mainScene;

        btnBack.setOnAction(e -> {
            mainStage.setScene(mainScene);
        });

        //TODO get the controller to retrieve the input values and add functionality to tell model to create card.
        btnAdd.setOnAction(e -> {
            mainStage.setTitle("My E-wallet 2022");
            mainStage.setScene(mainScene);
        });


    }

    public Scene getScene() {
        return this.scene;
    }
}
