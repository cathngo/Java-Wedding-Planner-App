package IndiefyLogin;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Music {
    
    private StringProperty album;
    private StringProperty artist;
    private StringProperty genre;
    private StringProperty year;
    
    public Music() {
        this("","","","");
    }

    public Music(String album, String artist, String genre, String year) {
        this.album = new SimpleStringProperty(album);
        this.artist = new SimpleStringProperty(artist);
        this.genre = new SimpleStringProperty(genre);
        this.year = new SimpleStringProperty(year);
    }

    public String getAlbum() {
        return album.get();
    }

    public String getArtist() {
        return artist.get();
    }

    public String getGenre() {
        return genre.get();
    }

    public String getYear() {
        return year.get();
    }
    
    public StringProperty getAlbumProperty() {
        return album;
    }

    public void setAlbum(String album) {
        this.album.set(album);
    }

    public StringProperty getArtistProperty() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist.set(artist);
    }

    public StringProperty getGenreProperty() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public StringProperty getYearProperty() {
        return year;
    }

    public void setYear(String year) {
        this.year.set(year);
    }
    
}
