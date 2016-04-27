import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.Label;

import java.io.File;

public class SudokuGUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
		
	BorderPane lerret = new BorderPane();
	Scene scene = new Scene(lerret, 1230, 800);

	lerret.setLeft(hentVBoks());
	lerret.setCenter(hentStortSudokubrett(3,3));

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

	return valgtFil;
    }

    public VBox hentVBoks() {
	VBox vboks = new VBox();
	
	GridPane liteBrett = hentLiteSudokubrett(3,3);
	
	Button knapp1 = new Button("Last inn brett");
	Button knapp2 = new Button("Lag eget brett");
	Button knapp3 = new Button("Avslutt");

	vboks.getChildren().addAll(liteBrett, knapp1, knapp2, knapp3);
	
	return vboks;
    }

    public GridPane hentStortSudokubrett(int antallBoksRader, int antallBoksKolonner) {
	GridPane stortBrett = new GridPane();
	//stortBrett.setHgap(80);
	//stortBrett.setVgap(80);
	stortBrett.setGridLinesVisible(true);
	
	int brettStorrelse = antallBoksRader*antallBoksKolonner;
	for (int j = 0; j < brettStorrelse; j++) {
	    for (int i = 0; i < brettStorrelse; i++) {
		StackPane rute = new StackPane();
		rute.getChildren().add(new Label(String.format("%d,%d", i, j)));
		stortBrett.add(rute, i, j);
	    }
	}	
	return stortBrett;
    }

    public GridPane hentLiteSudokubrett(int antallBoksRader, int antallBoksKolonner) {
	GridPane liteBrett = new GridPane();
        liteBrett.setHgap(40);
	liteBrett.setVgap(40);
	liteBrett.setGridLinesVisible(true);

	
	
	int brettStorrelse = antallBoksRader*antallBoksKolonner;
	for (int j = 0; j < brettStorrelse; j++) {
	    for (int i = 0; i < brettStorrelse; i++) {
		StackPane rute = new StackPane();
		liteBrett.add(rute, i, j);
	    }
	}
	return liteBrett;
    }

    public static void main(String[] args) {
	launch(args);
    }
}
