package IndiefyLogin;


import IndiefyLogin.Music;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

    
    // Initialise the TableView as FXML variables
public class MusicListController {
    
    @FXML
    private TableView<Music> musicList;

    @FXML
    private TableColumn<Music, String> artistColumn;

    @FXML
    private TableColumn<Music, String> albumColumn;

    @FXML
    private TableColumn<Music, String> genreColumn;

    @FXML
    private TableColumn<Music, String> yearColumn;
    
    Database database = new Database();
    
    @FXML
    public void initialize() {
        artistColumn.setCellValueFactory(
                cellData -> cellData.getValue().getArtistProperty());
        albumColumn.setCellValueFactory(
                cellData -> cellData.getValue().getAlbumProperty());
        genreColumn.setCellValueFactory(
                cellData -> cellData.getValue().getGenreProperty());
        yearColumn.setCellValueFactory(
                cellData -> cellData.getValue().getYearProperty());

        musicList.setItems(this.getMusicListData());
    }

    private ObservableList<Music> getMusicListData() {
        List<Music> musicListToReturn = new ArrayList<>();
        try {
            ResultSet rs = database.getResultSet("SELECT * FROM MUSICLIST");
            while(rs.next()) {
                musicListToReturn.add(
                        new Music(rs.getString("ALBUM"),rs.getString("ARTIST"),rs.getString("GENRE"),rs.getString("YEAR"))
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(musicListToReturn);
       return FXCollections.observableArrayList(musicListToReturn);
    }
    
}
