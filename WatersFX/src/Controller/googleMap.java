package Controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by joon1 on 2017-04-24.
 */
public class googleMap implements Initializable, MapComponentInitializedListener {
    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();

    @FXML
    protected GoogleMapView mapView;

    @FXML
    protected TextField fromTextField;

    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();
        options.center(new LatLong(47.606189, -122.335842))
                .zoomControl(true)
                .zoom(12)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);

        GoogleMap map = mapView.createMap(options);

        Double latitude = 0.0;
        Double longitude = 0.0;
        String name = "";



        try {
            ConnectDB db = new ConnectDB();
            Connection conn = db.getConnection();

            ResultSet rs = conn.createStatement().executeQuery("SELECT `latitude`, `longitude`, `name` FROM source_report");

            while (rs.next()) {
                latitude = rs.getDouble("latitude");
                longitude = rs.getDouble("longitude");
                name = rs.getString("name");

                MarkerOptions markerOptions = new MarkerOptions();

                markerOptions.position( new LatLong(latitude, longitude) )
                        .visible(Boolean.TRUE)
                        .title(name);

                Marker marker = new Marker( markerOptions );

                map.addMarker(marker);
            }

        } catch (SQLException e) {

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapView.addMapInializedListener(this);
    }
}
