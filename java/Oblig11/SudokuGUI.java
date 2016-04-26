import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;

public class SudokuGUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
	
	
	Pane lerret = new BorderPane();
	Scene scene = new Scene(lerret, 1230, 800);

	 FileChooser filVelger = new FileChooser();
	 filVelger.setTitle("Open Resource File");
	 filVelger.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
	 File selectedFile = fileChooser.showOpenDialog(stage);
	 /*
	 if (selectedFile != null) {
	     stage.display(selectedFile);
	 }
	 */

	stage.setScene(scene);
	stage.setTitle("Sudoku");
	stage.show();
    }

    public static void main(String[] args) {
	launch(args);
    }
}
