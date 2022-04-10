package ewallet.java.proj02;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

//        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        GridPane gpWalletView = new GridPane();
        VBox vbNotesPanel = new VBox();
        Text tMonthInputLabel = new Text("Month");
        Text tDayInputLabel = new Text("Day");
        Text tYearInputLabel = new Text("Year");
        TextField tfMonthInput = new TextField();
        TextField tfDayInput = new TextField();
        TextField tfYearInput = new TextField();
        ComboBox cbNoteDropdown = new ComboBox();
        Text noteDescription = new Text();
        vbNotesPanel.getChildren().addAll(new Text("My Notes"), cbNoteDropdown, tMonthInputLabel, tfMonthInput, tDayInputLabel, tfDayInput, tYearInputLabel, tfYearInput);
        gpWalletView.add(vbNotesPanel, 0, 1);


        var scene = new Scene(gpWalletView, 640, 480);


        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }

}