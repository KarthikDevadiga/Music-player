package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.DataSource;
import model.Songs;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Controller {
    private Label label;
    private Media media;
    private Stage primaryStage;

    @FXML
    private VBox vbox, tile;

    @FXML
    TextField field1, field2, field3;

    @FXML
    public void initialize(){
        vbox.setVisible(false);
    }
    //tile.getChildren().add(new Label(i + ": OMG").getStyleClass().add("side-bar-label"));

    //hand-burger
    public void showSideBar(ActionEvent e){
        if(vbox.isVisible()){
            vbox.setVisible(false);
        }else{
            vbox.setVisible(true);
        }

    }


    //to list all songs

    public void loadsFirst(){  // here tile is reference to VBox
        ArrayList<Songs> list = DataSource.getInstance().query_song();
//        ObservableList<Songs> task = (ObservableList<Songs>) new GetSongs();

        for(Songs song : list){
            Hyperlink link = new Hyperlink(song.getTitle());
            /** adding class in code */
            link.getStyleClass().add("labal");
            link.setOnAction((event) -> {

                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("xmlFiles/musicPlayer.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Stage primaryStage = new Stage();
                primaryStage.setTitle("Create Account");
                assert root != null;
                primaryStage.setScene(new Scene(root, 500, 250));
                primaryStage.show();

                MusicController music = new MusicController();
                music.playMusic(link.getText());



                // setting Action event for link in vBox
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("xmlFiles/sample.fxml"));
//                try {
//                    Parent root = loader.load();
//                    primaryStage.setTitle("Music Player");
//                    primaryStage.setScene(new Scene(root, 500, 250));
//                    primaryStage.show();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            });
            tile.getChildren().add(link);
        }
    }

    public void onclickOne(){
        //receives artist Name

    }

    public void onclickTwo(){
        //receives artist Name && album name
        System.out.println(field1.getText());
    }

    public void onclickThree(){
        //receives artist Name && album name && SongName
        System.out.println(field1.getText());
    }

    public void onLabelClicked(){
//        String name = e.getTarget().toString();
//        System.out.println(name);

    }





}

class GetSongs extends Task {
    @Override
    protected ObservableList<Songs> call()  {
        return FXCollections.observableArrayList(DataSource.getInstance().query_song());
    }
}


//



/* public void getFonts(){
 List<String> list =  Font.getFamilies();
 for(String fint : list){
 System.out.println(fint);
 }
 }
 */