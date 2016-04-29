import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.PrintWriter;

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

public class SudokuMain extends Application {
    private static Scanner innFil;
    private static Brett brettet; 
    private static int antBoksRader;
    private static int antBoksKolonner;

    /***************************** HJELPEMETODER ******************************/

    /**
     * Oversetter en streng (String) til en tallverdi (int).
     * Metoden avslutter programmet om ikke dette ikke lar seg gjoere.
     *
     * @param data   strengen som skal gjoeres om
     * @return       tallverdien 
     */
    private static int parseInt(String data) { 

	try {
	    return Integer.parseInt(data);

	} catch (NumberFormatException e) { 
	    System.out.printf("Kunne ikke konvertere '%s' til int.\n", data);
	    innFil.close();
	    System.exit(1);
	}
	return Integer.MIN_VALUE;
    }

    /**
     * Oversetter ett tegn (char) til en tallverdi (int) ved bruk av metoden
     * verdiTilTegn(), innenfor intervallet fra 0 til en maksverdi (int).
     * Metoden avslutter programmet om ikke dette ikke lar seg gjoere.
     *
     * @param tegn        tegnet som skal gjoeres om
     * @param maksVerdi   tallet maa vaere mindre enn denne
     * @return            tallverdien 
     */
    private static int parseInt(char tegn, int maksVerdi) {
	try {
	    int verdi = brettet.tegnTilVerdi(tegn);

	    if (verdi < 0 || verdi > maksVerdi) {
		throw new IllegalArgumentException(String.format("Tall '%c' utenfor lovlig intervall.\n", tegn));
	    }
	    return verdi;

	} catch (IllegalArgumentException e) {
	    System.out.println(e.getMessage());
	    innFil.close();
	    System.exit(1);	    
	}
	return Integer.MIN_VALUE;	
    }

    /**
     * Metoden avslutter programmet om brettet er for stort.
     */
    private static void sjekkBrettStorrelse() {
	int maksBrettStorrelse = 64*64;
	int brettStorrelse = antBoksRader*antBoksKolonner*antBoksRader*antBoksKolonner;

	try {
	    if (brettStorrelse > maksBrettStorrelse) {
		throw new IllegalArgumentException(String.format("Storrelsen paa brettet er for stort: %dx%d. Maksimum brettstorrelse er 64x64.\n", antBoksRader*antBoksKolonner, antBoksRader*antBoksKolonner));
	    }
	} catch (IllegalArgumentException e) {
	    System.out.println(e.getMessage());
	    innFil.close();
	    System.exit(1);
	}
    }

    /*************************** LES OG SKRIV TIL FIL *************************/

    /**
     * Leser inn sudokubrettet fra en fil gitt ved en streng med filnavnet.
     * Alle Rute objektene opprettes og settes inn i et array i Brett objektet.
     * Metoden avslutter programmet om det er noe feil med filformatet.
     *
     * @param filnavn   navnet paa fila som skal leses inn
     */
    public static void lesFil(String filnavn) {
	try {

	    innFil = new Scanner(new File(filnavn));	
	    antBoksRader = parseInt(innFil.nextLine());
	    antBoksKolonner = parseInt(innFil.nextLine());
	    sjekkBrettStorrelse();

	    int maksVerdi = antBoksRader*antBoksKolonner;

	    brettet = new Brett(antBoksRader, antBoksKolonner);
	    char[] linje = new char[maksVerdi];
	    Rute nyRute;
	    Rute forrigeRute = null;

	
	    while (innFil.hasNext()) {
		linje = innFil.nextLine().toCharArray();

		for (char tegn : linje) {
		    nyRute = new Rute(parseInt(tegn, maksVerdi), brettet);
		    brettet.settInnRute(nyRute);

		    if (forrigeRute != null) {
			forrigeRute.settNeste(nyRute);
		    }
		    forrigeRute = nyRute;
		} 
	    }

	    if (!brettet.erFyltUt()) {
		throw new IllegalArgumentException(String.format("Antall tegn stemmer ikke: Filen '%s' inneholder for faa tegn.\n", filnavn));
	    }

	} catch (FileNotFoundException e) {
	    System.out.printf("Kunne ikke finne filen '%s'.\n", filnavn);
	    System.exit(1);
	} catch (NoSuchElementException e) {
	    System.out.printf("Filen '%s' inneholder ikke nok informasjon.\n", filnavn);
	    System.exit(1);
	} catch (IndexOutOfBoundsException e) {
	    System.out.printf("Antall tegn stemmer ikke: Filen '%s' inneholder for mange tegn.\n", filnavn);
	    System.exit(1);
	} catch (IllegalArgumentException e) {
	    System.out.println(e.getMessage());
	    System.exit(1);
	} finally {
	    innFil.close();
	}
    }



    /**
     * Skriver sudokubrettet til en fil gitt ved en streng med filnavnet.
     *
     * @param filnavn   navnet paa fila som skal skrives til
     */
    public static void skrivBrettTilFil(String filnavn) {
	try {
	    PrintWriter utFil = new PrintWriter(new File(filnavn));

	    utFil.print(brettet.hentBrettutskrift(Brett.Utskriftsformat.FIL));
	    utFil.close();

	} catch (FileNotFoundException e) {
	    System.out.printf("Kunne ikke finne filen '%s'.\n", filnavn); 
	}
    }

    /**
     * Skriver losninger fra SudokuBeholder til en fil gitt ved en streng med 
     * filnavnet.
     *
     * @param filnavn   navnet paa fila som skal skrives til
     */
    public static void skrivLosningerTilFil(String filnavn) {
	try {
	    PrintWriter utFil = new PrintWriter(new File(filnavn));
	    SudokuBeholder beholder = brettet.hentBeholder();
	    
	    if (beholder.hentAntallLosninger() == 1) {
		utFil.print(brettet.hentBrettutskrift(Brett.Utskriftsformat.FIL, beholder.taUt(), 0));

	    } else if (beholder.hentAntallLosninger() > 1) {
		int losningNr = 0;

		while (!beholder.erTom()) {
		    utFil.print(brettet.hentBrettutskrift(Brett.Utskriftsformat.KOMPAKT, beholder.taUt(), losningNr++));
		}		
	    }
	    utFil.close();

	} catch (FileNotFoundException e) {
	    System.out.printf("Kunne ikke finne filen '%s'.\n", filnavn); 
	}
    }

    /*************************** SKRIV TIL SKJERM *****************************/

    /**
     * Skriver sudokubrettet til skjerm.
     */
    public static void skrivBrettTilSkjerm() {
	System.out.print(brettet.hentBrettutskrift(Brett.Utskriftsformat.SKJERM));
    }

    /**
     * Skriver losninger fra SudokuBeholder til en fil til skjerm.
     */
    public static void skrivLosningerTilSkjerm() {
	SudokuBeholder beholder = brettet.hentBeholder();
	System.out.printf("Antall losninger: %d\n\n", beholder.hentAntallLosninger());
	    
	if (beholder.hentAntallLosninger() == 1) {
	    System.out.print(brettet.hentBrettutskrift(Brett.Utskriftsformat.SKJERM, beholder.taUt(), 0));
	    
	} else if (beholder.hentAntallLosninger() > 1) {
	    int losningNr = 0;

	    while (!beholder.erTom()) {
		System.out.print(brettet.hentBrettutskrift(Brett.Utskriftsformat.KOMPAKT, beholder.taUt(), losningNr++));
	    }		
	}
    }

    /******************************** GUI *************************************/

    @Override
    public void start(Stage stage) {
		
	BorderPane lerret = new BorderPane();
	Scene scene = new Scene(lerret, 1230, 800);

	File forsteFil = hentFil(stage);
	lesFil(forsteFil.getPath());
	brettet.opprettDatastruktur();
	brettet.finnAlleLosninger();
	skrivLosningerTilSkjerm();
		
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

	lastInnKnapp.setOnAction( new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
		    File nyttBrett = hentFil(stage);
		    lesFil(nyttBrett.getPath());
		    brettet.opprettDatastruktur();
		    brettet.finnAlleLosninger();
		    skrivLosningerTilSkjerm();
		}
	    });

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

    /**************************************************************************/

    public static void main(String[] args) {

	launch(args);
    } 
}
