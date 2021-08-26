package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import model.DataSource;
import model.Songs;


import java.io.File;
import java.util.ArrayList;
import java.util.List;




public class Controller {

    private Media media;

    @FXML
    private VBox vbox, tile;

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
            Label label = new Label(song.getTitle());
            label.getStyleClass().add("labal");

            tile.getChildren().add(label);
        }

    }

    public void onclickTwo(){
        String url = "src/sample/music/ambient_hum_air_conditioner.ogg";
        media = new Media(new File(url).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        player.play();
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