package sample;

import javafx.fxml.FXML;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    public void initialize(){
        getFonts();
    }

    public void getFonts(){
        List<String> list =  Font.getFamilies();
        for(String fint : list){
            System.out.println(fint);
        }
    }

}


//