import controller.LevelOneViewController;
import controller.ViewController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ETankApplication extends Application {

    private Stage stage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        showLevelOne();

    }

    public void showLevelOne() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ETankApplication.class.getResource("view/LevelOneView.fxml"));
            AnchorPane levelOne = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(levelOne);
            stage.setScene(scene);
            LevelOneViewController controller = loader.getController();
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {

                }

            });
            stage.show();


        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
