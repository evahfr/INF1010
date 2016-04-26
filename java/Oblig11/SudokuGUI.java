import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;

public class SudokuGUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
		
	BorderPane lerret = new BorderPane();
	Scene scene = new Scene(lerret, 1230, 800);

	lerret.setLeft(hentVBoks());

	stage.setScene(scene);
	stage.setTitle("Sudoku");
	stage.show();

	hentFil(stage);
    }

    public File hentFil(Stage stage) {

	FileChooser filVelger = new FileChooser();

	filVelger.setTitle("Open Resource File");
	filVelger.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));

	File valgtFil = filVelger.showOpenDialog(stage);
	/*
	if (selectedFile != null) {
	stage.display(selectedFile);
	}
	*/
	return valgtFil;
    }

    public VBox hentVBoks() {
	VBox vboks = new VBox();
	
	GridPane pane = new GridPane();
	
	Button knapp1 = new Button("Last inn brett");
	Button knapp2 = new Button("Lag eget brett");
	Button knapp3 = new Button("Avslutt");

	vboks.getChildren().addAll(pane, knapp1, knapp2, knapp3);
	
	return vboks;
    }

    public GridPane hentStortSudokubrett(int antallBoksRader, int antallBoksKolonner) {
	return null;
    }

    public GridPane hentLiteSudokubrett(int antallBoksRader, int antallBoksKolonner) {
	return null;
    }

    public static void main(String[] args) {
	launch(args);
    }
}
