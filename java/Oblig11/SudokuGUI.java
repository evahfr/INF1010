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

	File forsteFil = hentFil(stage);

	System.out.println(forsteFil.getPath());
		
	lerret.setLeft(hentVBoks(stage));
	lerret.setBottom(hentHBoks());
	
	GridPane stortBrett = hentStortSudokubrett(4,4);
	lerret.setCenter(stortBrett);
	lerret.setMargin(stortBrett, new Insets(10,10,10,10));

	stage.setScene(scene);
	stage.setTitle("Sudoku");
	stage.show();
    }

    public File hentFil(Stage stage) {

	FileChooser filVelger = new FileChooser();

	filVelger.setTitle("Open Resource File");
	filVelger.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));

	File valgtFil = filVelger.showOpenDialog(stage);

	return valgtFil;
    }

    public VBox hentVBoks(Stage stage) {
	VBox vboks = new VBox();
        
	vboks.setSpacing(20);
	vboks.setAlignment(Pos.BASELINE_CENTER);
	
	GridPane liteBrett = hentLiteSudokubrett(4,4);
	vboks.setMargin(liteBrett, new Insets(10,10,10,10));
	
	Button lastInnKnapp = new Button("Last inn brett");
	Button lagBrettKnapp = new Button("Lag eget brett");
	Button avsluttKnapp = new Button("Avslutt");

	lastInnKnapp.setPrefSize(200, 40);
	lagBrettKnapp.setPrefSize(200, 40);
	avsluttKnapp.setPrefSize(200, 40);

	lastInnKnapp.setOnAction( knappTrykka ->
				  hentFil(stage)
				  );

	lagBrettKnapp.setOnAction( new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
		    System.out.println("Ikke implementert enda");
		}
	    });

	avsluttKnapp.setOnAction( knappTrykka ->
				  Platform.exit()
				  );

	vboks.getChildren().addAll(liteBrett, lastInnKnapp, lagBrettKnapp, avsluttKnapp);
	
	return vboks;
    }

    public HBox hentHBoks() {

	HBox hboks = new HBox();

	hboks.setSpacing(20);

	Button forrigeKnapp = new Button("Forrige");
	Button nesteKnapp = new Button("Neste");
	
	forrigeKnapp.setPrefSize(100,20);
	nesteKnapp.setPrefSize(100,20);

	hboks.setMargin(forrigeKnapp, new Insets(0,0,10,600));

	forrigeKnapp.setOnAction( knappTrykka -> 
				  System.out.println("Ikke implementert.")
				  );

	nesteKnapp.setOnAction( knappTrykka ->
				System.out.println("Ikke implementert.")
				);

	hboks.getChildren().addAll(forrigeKnapp, nesteKnapp);
	
	return hboks;
	

    }

    public GridPane hentStortSudokubrett(int antallBoksRader, int antallBoksKolonner) {
	GridPane stortBrett = new GridPane();
	
	int brettStorrelse = antallBoksRader*antallBoksKolonner;
        double brettPikselLengde = 800.0;
	double rutePikselLengde = brettPikselLengde/brettStorrelse;

	for (int j = 0; j < brettStorrelse; j++) {
	    for (int i = 0; i < brettStorrelse; i++) {

		StackPane rute = new StackPane();
		rute.setStyle("-fx-border-width: 2px; -fx-border-style: solid; -fx-border-color: grey");
		rute.setPrefSize(rutePikselLengde,rutePikselLengde);
		rute.getChildren().add(new Label(String.format("%d,%d", i, j)));
		stortBrett.add(rute, i, j);
	    }
	}	
	return stortBrett;
    }

    public GridPane hentLiteSudokubrett(int antallBoksRader, int antallBoksKolonner) {
	GridPane liteBrett = new GridPane();
	
	int brettStorrelse = antallBoksRader*antallBoksKolonner;
        double brettPikselLengde = 300.0;
	double rutePikselLengde = brettPikselLengde/brettStorrelse;

	for (int j = 0; j < brettStorrelse; j++) {
	    for (int i = 0; i < brettStorrelse; i++) {

		StackPane rute = new StackPane();
		rute.setStyle("-fx-border-width: 1px; -fx-border-style: solid; -fx-border-color: grey");
		rute.setPrefSize(rutePikselLengde,rutePikselLengde);
		liteBrett.add(rute, i, j);
	    }
	}
	return liteBrett;
    }

    
    public static void main(String[] args) {
	launch(args);
	
    }
    
}
