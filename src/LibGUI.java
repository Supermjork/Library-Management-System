import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class LibGUI extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        StackPane layout = new StackPane();
        Scene scene = new Scene(layout, 400, 300);

        primaryStage.setTitle("Library Project GUI Window");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}