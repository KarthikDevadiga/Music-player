package sample;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.DataSource;
import model.Music;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MusicController {
    public static int id;

    private Timer timer;

    public static String url = "src/sample/music/music22.mp3";

    public MediaPlayer mediaPlayer;

    public Media media;

    public TimerTask task;

    public boolean running;

    @FXML
    Slider slider;

    @FXML
    public ProgressBar bar;

    @FXML
    public static Label label;

    public void initialize() {

        media = new Media(new File(url).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        slider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                mediaPlayer.setVolume(slider.getValue() * 0.01);
            }
        });
    }

    public void playMusic(String str) {

//        String url="";
        ArrayList<Music> musics = DataSource.getInstance().play(str);
        for (Music music : musics) {
            url = music.getUrl();

            System.out.println(url + ":(");
            break;
        }

        media = new Media(new File(url).toURI().toString());
        mediaPlayer = new MediaPlayer(media);


//        System.out.println(url);

        System.out.println("no songs to return ");
    }

    public void play() {


        mediaPlayer.play();
        beginTimer();
    }

    public void pause() {

        mediaPlayer.pause();
        cancelTimer();
    }


    public void beginTimer() {

        timer = new Timer();

        task = new TimerTask() {
            public void run() {
                running = true;
                if (mediaPlayer == null) {
                    return;
                }
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
//                bar.setProgress(current/end);
                Platform.runLater(() -> bar.setProgress(current / end));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(current / end);
                if (current / end == 1) {
                    cancelTimer();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    public void cancelTimer() {
        running = false;
        timer.cancel();
    }

}


