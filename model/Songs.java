package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Songs {
    private SimpleIntegerProperty id;
    private SimpleIntegerProperty track;
    private SimpleStringProperty title;
    private SimpleIntegerProperty album;

    public Songs() {
        this.id = new SimpleIntegerProperty();
        this.track = new SimpleIntegerProperty();
        this.title = new SimpleStringProperty();
        this.album = new SimpleIntegerProperty();
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getTrack() {
        return track.get();
    }

    public void setTrack(int track) {
        this.track.set(track);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public int getAlbum() {
        return album.get();
    }

    public void setAlbum(int album) {
        this.album.set(album);
    }
}
