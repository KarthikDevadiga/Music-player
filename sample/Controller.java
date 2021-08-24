package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;




public class Controller {
    @FXML
    private VBox vbox;

    @FXML
    public void initialize(){
        vbox.setVisible(false);
    }

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