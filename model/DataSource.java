package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    public static final String DB_NAME = "music.db";  //       "C:\Users\KARTHIK\Desktop\all\DAtabases\Music Database\music_databse"
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\KARTHIK\\Desktop\\all\\DAtabases\\Music Database\\music_databse\\" + DB_NAME;
    public static final String TABLE_ALBUMS = "albums";
    public static final String TABLE_ARTISTS = "artists";
    public static final String TABLE_SONGS = "songs";
    public static final String TABLE_MUSIC = "music";

    public static final String ARTIST_ID = "_id";
    public static final String ARTIST_NAME = "name";
    public static final int COLUMN_ARTIST_ID = 1;
    public static final int COLUMN_ARTIST_NAME = 2;


    public static final String ALBUM_ID = "_id";
    public static final String ALBUM_NAME = "name";
    public static final String ALBUM_ARTIST = "artist";
    public static final int COLUMN_ALBUM_ID = 1;
    public static final int COLUMN_ALBUM_NAME = 2;
    public static final int COLUMN_ALBUM_ARTIST = 3;


    public static final String SONG_ID = "_id";
    public static final String SONG_TRACK = "track";
    public static final String SONG_TITLE = "title";
    public static final String SONG_ALBUM = "album";
    public static final int COLUMN_SONG_ID = 1;
    public static final int COLUMN_SONG_TRACK = 2;
    public static final int COLUMN_SONG_TITLE = 3;
    public static final int COLUMN_SONG_ALBUM = 4;

    public static final String MUSIC_ID = "_id";
    public static final String MUSIC_TRACK = "track";
    public static final String MUSIC_TITLE = "title";
    public static final String MUSIC_ALBUM = "album";
    public static final int COLUMN_MUSIC_ID = 1;
    public static final int COLUMN_MUSIC_TRACK = 2;
    public static final int COLUMN_MUSIC_TITLE = 3;
    public static final int COLUMN_MUSIC_ALBUM = 4;

    /** oder */
    public static final int ORDER_NONE = 0;
    public static final int ORDER_ASC = 1;
    public static final int ORDER_DSC = 2;

    /**SELECT albums.name from albums INNER JOIN artists ON albums.artist = artists._id WHERE artists.name="Iron Maiden" ORDER BY albums.name COLLATE NOCASE ASC; */
    public static final String QUERY_ALBUMS_BY_ARTIST = "SELECT " + TABLE_ALBUMS + "." + ALBUM_NAME + " FROM " + TABLE_ALBUMS + " INNER " +
            "JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." +
            ARTIST_ID + " WHERE " + TABLE_ARTISTS + "." + ARTIST_NAME + " = \"";

    public static final String ORDER_ALBUM_NAME = " ORDER BY " + TABLE_ALBUMS + "." + ALBUM_NAME + " COLLATE NOCASE";

    public static final String NEW_VIEW = "CREATE VIEW IF NOT EXISTS new_view AS SELECT artists.name AS artist, albums.name AS album, songs.title AS song FROM songs INNER JOIN albums ON songs.album = albums._id" +
            " INNER JOIN artists ON albums.artist = artists._id ORDER BY artists.name, albums.name, songs.title";

    //quering satatement

    public static final String QUERY_ARTIST = "SELECT " + ARTIST_ID + " FROM " + TABLE_ARTISTS + " WHERE " + ARTIST_NAME + " = ?";
    public static final String QUERY_ALBUM = "SELECT " + ALBUM_ID + " FROM " + TABLE_ALBUMS + " WHERE " + ALBUM_NAME + " = ?";
//    public static final String QUERY_SONG = "SELECT " + SONG_TITLE + " FROM " + TABLE_SONGS + " WHERE " + SONG_TITLE + " = ?";

    //modified the above line
    public static final String QUERY_SONG = "SELECT " + SONG_TITLE + " FROM " + TABLE_SONGS ;
////    public static final String QUERY_MUSIC = "SELECT url FROM " + TABLE_SONGS + " WHERE " + TABLE_MUSIC ;
    public static final String QUERY_MUSIC = "SELECT url FROM " + TABLE_MUSIC + " WHERE "+ MUSIC_TITLE + " = ?" ;
//    public static final String QUERY_MUSIC = "SELECT " + TABLE_MUSIC + ".url FROM "+ TABLE_MUSIC + " WHERE " + TABLE_MUSIC.COLUMN_MUSIC







    /**
     * INSERT INTO artists(name) VALUES("karthik");
     * INSERT INTO albums(name,artist) VALUES("KARTHIK", 4);
     * INSERT INTO songs(track, title, album) VALUES(3, "bachpankipyar", 5);
     * */

    public static final String INSERT_INTO_ARTISTS = "INSERT INTO " + TABLE_ARTISTS + "(" + ARTIST_NAME + ") VALUES(?)";
    public static final String INSERT_INTO_ALBUMS = "INSERT INTO " + TABLE_ALBUMS + "(" + ALBUM_NAME + ", " + ALBUM_ARTIST +  ") VALUES(?, ?)";
    public static final String INSERT_INTO_SONGS = "INSERT INTO " + TABLE_SONGS + "(" + SONG_TRACK + ", " + SONG_TITLE + ", " + SONG_ALBUM + ") VALUES(?, ?, ?)";

    private Connection conn;

    private PreparedStatement insertIntoArtist;
    private PreparedStatement insertIntoAlbum;
    private PreparedStatement insertIntoSongs;
    private PreparedStatement returnArtistId;
    private PreparedStatement returnAlbumId;
    private PreparedStatement querySong;
    private PreparedStatement queryMusic;


    public boolean open(){
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);

            insertIntoArtist = conn.prepareStatement(INSERT_INTO_ARTISTS, Statement.RETURN_GENERATED_KEYS);
            insertIntoAlbum = conn.prepareStatement(INSERT_INTO_ALBUMS, Statement.RETURN_GENERATED_KEYS);
            insertIntoSongs = conn.prepareStatement(INSERT_INTO_SONGS);
            returnArtistId = conn.prepareStatement(QUERY_ARTIST);
            returnAlbumId = conn.prepareStatement(QUERY_ALBUM);
            querySong = conn.prepareStatement(QUERY_SONG);
            queryMusic = conn.prepareStatement(QUERY_MUSIC);

            return true;
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

    }
    public void close(){
       try{
           if(insertIntoArtist != null){
               insertIntoArtist.close();
           }
           if(insertIntoAlbum != null){
               insertIntoAlbum.close();
           }
           if(insertIntoSongs != null){
               insertIntoSongs.close();
           }
           if(querySong != null){
               querySong.close();
           }

           if(queryMusic != null){
               queryMusic.close();
           }
           if(conn!=null){
               conn.close();
           }
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }

    }

    /** Creating instance OR singleton class */

    private static DataSource instance = new DataSource();

    private DataSource(){
        //EMPTY CONSTRUCTOR
    }

    //getInstance method . To give only one instance of this class to all Controllers and other files

    public static DataSource getInstance(){
        return instance;
    }

    public void dummy(){
        System.out.println("hello==");
    }




    public List<Artists> queryArtists(int order){
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_ARTISTS);

        if(order != ORDER_NONE){
            sb.append(" ORDER BY ");
            sb.append(ARTIST_NAME);
            sb.append(" COLLATE NOCASE");
            if(order == ORDER_DSC){
                sb.append(" DESC");
            }else{
                sb.append(" ASC");
            }
        }


        try(Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sb.toString())){
            List<Artists> list = new ArrayList<>();
            System.out.println(sb.toString());
            while(results.next()){
                Artists artist = new Artists();
                artist.setId(results.getInt(COLUMN_ARTIST_ID));
                artist.setName(results.getString(COLUMN_ARTIST_NAME));
                list.add(artist);
            }
            return list;
        }catch(SQLException e){
            System.out.println("artists " + e.getMessage());
            return null;
        }
    }

    public List<Albums> queryAlbums(String name, int order){
        StringBuilder sb = new StringBuilder(QUERY_ALBUMS_BY_ARTIST);
        sb.append(name).append("\"");

        if(order != ORDER_NONE){
            sb.append(ORDER_ALBUM_NAME);
            if(order == ORDER_DSC){
                sb.append(" DESC");
            }else{
                sb.append(" ASC");
            }
        }
        System.out.println(sb.toString());
        try(Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sb.toString())){

            ArrayList<Albums> list = new ArrayList<>();
            while(result.next()){
                Albums album = new Albums();
                album.setName(result.getString(1));
                list.add(album);
            }
            return list;
        }catch(SQLException se){
            System.out.println(se.getMessage());
            return null;
        }





    }


    public void create_view(){
        try(Statement statement = conn.createStatement();
        ){
            statement.execute(NEW_VIEW);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<NewView> query_view(){
        try(Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM new_view")){
            ArrayList<NewView> list = new ArrayList<>();
            while(result.next()){
                NewView view = new NewView();
                view.setArtistName(result.getString(1));
                view.setAlbumName(result.getString(2));
                view.setTitle(result.getString(3));
                list.add(view);
            }
            return list;
        }catch(SQLException  e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private int insertIntoArtist(String name) throws Exception{

        returnArtistId.setString(1, name);

        ResultSet result = returnArtistId.executeQuery();
        if(result.next()){
            return result.getInt(1);
        }else{
            insertIntoArtist.setString(1, name);
            int updatedRow = insertIntoArtist.executeUpdate();
            if(updatedRow != 1){
                throw new Exception("no rows updated");
            }else{
                ResultSet generatedKey = insertIntoArtist.getGeneratedKeys();   //TODO getGeneratedKeys()
                if (generatedKey.next()) {
                    return generatedKey.getInt(1);
                }else{
                    throw new Exception("error in generated key");
                }
            }
        }
    }

    //inserting album
    private int insertIntoAlbum(String name, int artist) throws Exception{

        returnAlbumId.setString(1, name);

        ResultSet result = returnAlbumId.executeQuery();
        if(result.next()){
            return result.getInt(1);
        }else{
            insertIntoAlbum.setString(1, name);
            insertIntoAlbum.setInt(2, artist);
            int updatedRow = insertIntoAlbum.executeUpdate();
            if(updatedRow != 1){
                throw new Exception("no rows updated");
            }else{
                ResultSet generatedKey = insertIntoAlbum.getGeneratedKeys();
                if (generatedKey.next()) {
                    return generatedKey.getInt(1);
                }else{
                    throw new Exception("error in generated key");
                }
            }
        }
    }

    public void insertSong(int track, String title, String artist, String album) {
        try{
            conn.setAutoCommit(false);
            System.out.println(":(");
            int artistId = insertIntoArtist(artist);

            int albumId = insertIntoAlbum(album, artistId);
            System.out.println(":(");
            insertIntoSongs.setInt(1,track);
            insertIntoSongs.setString(2,title);
            insertIntoSongs.setInt(3, albumId);
            System.out.println(":(");
            int rowsAffected = insertIntoSongs.executeUpdate();
            if(rowsAffected != 1){
                System.out.println("performing rollback" );
                try{
                    conn.rollback();
                }catch(SQLException e1){
                    System.out.println("roolback failed" + e1.getMessage());
                }
            }else{
                System.out.println("commited");
                conn.commit();
            }
        }catch(Exception e){
            System.out.println("performing rollback :)" + e.getMessage());
            try{
                conn.rollback();
            }catch(SQLException e1){
                System.out.println("roolback failed" + e1.getMessage());
            }
        }finally{
            System.out.println("saving int default");
            try{
                conn.setAutoCommit(true);
            }catch(SQLException e2){
                System.out.println("default problem" + e2.getMessage());
            }

        }
    }

    public ArrayList<Songs> query_song(){
        try{
            ResultSet result = querySong.executeQuery();
            ArrayList<Songs> list = new ArrayList<>();
            while(result.next()){
                Songs song = new Songs();
                song.setTitle(result.getString(1));
                list.add(song);
            }
            return list;
        }catch(SQLException  e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    public ArrayList<Music> play(String str){
        System.out.println(str);
        try{
            System.out.println("1");
            queryMusic.setString(1, str);
            System.out.println("2");
            ResultSet result = queryMusic.executeQuery();
            System.out.println("3");
            ArrayList<Music> list = new ArrayList<>();
            System.out.println("4");
            while(result.next()){
                System.out.println("5");
                Music song = new Music();
                System.out.println(result.getString(1));
                song.setUrl(result.getString(1));
                list.add(song);
            }
            return list;
        }catch(SQLException  e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}