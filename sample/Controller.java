package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;




public class Controller {



    @FXML
    private VBox vbox, tile;

    @FXML
    public void initialize(){
        vbox.setVisible(false);
        for(int i=0; i<100; i++){
            Label label = new Label(i + "lorem20");
            label.getStyleClass().add("labal");

            tile.getChildren().add(label);
        }
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





}


//



/* public void getFonts(){
 List<String> list =  Font.getFamilies();
 for(String fint : list){
 System.out.println(fint);
 }
 }
 */