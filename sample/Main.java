package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DataSource;

public class Main extends Application {

    private int i;
    private int j;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("xmlFiles/sample.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.onFirstClicked();
        primaryStage.setTitle("Music Player");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
//        primaryStage.setFullScreen(true);
    }

    @Override
    public void init() throws Exception {
        super.init();
        if(!DataSource.getInstance().open()){
            System.out.println("problem in DataSource class : ( ");
            Platform.exit();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        DataSource.getInstance().close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}




/**C:\Users\KARTHIK\Desktop\all\DAtabases\Music Database\music_databse\src*/