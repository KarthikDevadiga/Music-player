package sample;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.DataSource;
import model.Music;

import java.io.File;
import java.util.ArrayList;

public class MusicController {
    public static int id;

     public static String url;

    public MediaPlayer mediaPlayer;


    public void playMusic(String str){
        System.out.println("playing:" + str);
//        String url="";
        ArrayList<Music> musics = DataSource.getInstance().play(str);
        for(Music music : musics){
            url = music.getUrl();

            System.out.println(url + ":(");
            break;
        }
//        System.out.println(url);

        System.out.println("no songs to return ");
    }

    public void play(){
        Media media = new Media(new File(url).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public void pause(){
        mediaPlayer.pause();
    }

}
