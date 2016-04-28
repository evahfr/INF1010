import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.Label;
import javafx.event.*;
import javafx.geometry.*;

import java.io.File;

public class SudokuGUI extends Application {

    @Override
    public void start(Stage stage) {
		
	BorderPane lerret = new BorderPane();
	Scene scene = new Scene(lerret, 1230, 800);

	lerret.setLeft(hentVBoks(stage));
	
	GridPane stortBrett = hentStortSudokubrett(3,3);
	lerret.setCenter(stortBrett);
	lerret.setMargin(stortBrett, new Insets(10,10,10,10));

	stage.setScene(scene);
	stage.setTitle("Sudoku");
	stage.show();
    }

    public File hentFil(Stage stage) {

	FileChooser filVelger = new FileChooser();

	filVelger.setTitle("Open Resource File");
	filVelger.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));

	File valgtFil = filVelger.showOpenDialog(stage);

	return valgtFil;
    }

    public VBox hentVBoks(Stage stage) {
	VBox vboks = new VBox();
        
	vboks.setSpacing(20);
	vboks.setAlignment(Pos.BASELINE_CENTER);
	
	GridPane liteBrett = hentLiteSudokubrett(3,3);
	vboks.setMargin(liteBrett, new Insets(10,10,10,10));
	
	Button lastInnKnapp = new Button("Last inn brett");
	Button lagBrettKnapp = new Button("Lag eget brett");
	Button avsluttKnapp = new Button("Avslutt");

	lastInnKnapp.setPrefSize(200, 40);
	lagBrettKnapp.setPrefSize(200, 40);
	avsluttKnapp.setPrefSize(200, 40);

	lastInnKnapp.setOnAction( new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
		    hentFil(stage);
		}
	    });

	lagBrettKnapp.setOnAction( new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
		    System.out.println("Ikke implementert enda");
		}
	    });

	avsluttKnapp.setOnAction( new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
		    Platform.exit();
		}
	    });

	vboks.getChildren().addAll(liteBrett, lastInnKnapp, lagBrettKnapp, avsluttKnapp);
	
	return vboks;
    }

    public GridPane hentStortSudokubrett(int antallBoksRader, int antallBoksKolonner) {
	GridPane stortBrett = new GridPane();
	
	stortBrett.setMinSize(800,800);
	//stortBrett.setGridLinesVisible(true);
	
	int brettStorrelse = antallBoksRader*antallBoksKolonner;
	for (int j = 0; j < brettStorrelse; j++) {
	    for (int i = 0; i < brettStorrelse; i++) {
		StackPane rute = new StackPane();
		rute.setStyle("-fx-border-width: 2px; -fx-border-style: solid; -fx-border-color: grey");
		rute.getChildren().add(new Label(String.format("%d,%d", i, j)));
		stortBrett.add(rute, i, j);
	    }
	}	
	return stortBrett;
    }

    public GridPane hentLiteSudokubrett(int antallBoksRader, int antallBoksKolonner) {
	GridPane liteBrett = new GridPane();
	liteBrett.setPrefSize(350,350);
	liteBrett.setMinWidth(350);
	liteBrett.setMinHeight(350);
        //liteBrett.setHgap(40);
	//liteBrett.setVgap(40);
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
