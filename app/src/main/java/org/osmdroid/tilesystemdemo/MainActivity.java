package org.osmdroid.tilesystemdemo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    enum Mode {
        MERCATOR,
        MERCATOR_PROJ4,
        MAAAMET,
        MAAAMET_PROJ4
    }

    public static final Mode MODE = Mode.MAAAMET;

    @Override
    protected void onCreate(Bundle pSavedInstanceState) {
        File cacheDir = new File(getCacheDir(), "tiles");
        final MapView mapView;
        Marker marker;

        if(cacheDir.exists()) {
            new File(cacheDir, "cache.db").delete();
            new File(cacheDir, "cache.db-journal").delete();
        }

        Configuration.getInstance().setOsmdroidTileCache(cacheDir);

        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_main);
        BoundingBox.tileSystem = new CustomTileSystem();

        mapView = findViewById(R.id.map);
        mapView.setHorizontalMapRepetitionEnabled(false);
        mapView.setVerticalMapRepetitionEnabled(false);

        switch(MODE) {
            case MERCATOR:
                // Do nothing
                break;
            case MERCATOR_PROJ4:
                mapView.setTileSystem(Proj4TileSystem.MERCATOR);
                break;
            case MAAAMET:
                mapView.setTileSystem(new CustomTileSystem());
                mapView.setTileSource(new CustomTileSource());
                break;
            case MAAAMET_PROJ4:
                mapView.setTileSystem(Proj4TileSystem.MAAAMET);
                mapView.setTileSource(new CustomTileSource());
                break;
        }

        mapView.setMaxZoomLevel(13.0);
        mapView.getController().setZoom(4.0);
        mapView.setMultiTouchControls(true);
        //mapView.setTilesScaledToDpi(true);
        mapView.setExpectedCenter(convertFromWgs84(new GeoPoint(59.4399863, 24.7371065)));

        marker = new Marker(mapView);
        marker.setPosition(convertFromWgs84(new GeoPoint(58.3738177, 26.7067371)));
        marker.setTitle("Tartu Railway Station");
        mapView.getOverlayManager().add(marker);

        marker = new Marker(mapView);
        marker.setPosition(convertFromWgs84(new GeoPoint(60.1718729, 24.9414217)));
        marker.setTitle("Helsinki Central Station");
        mapView.getOverlayManager().add(marker);

        marker = new Marker(mapView);
        marker.setPosition(convertFromWgs84(new GeoPoint(59.4399863, 24.7371065)));
        marker.setTitle("Baltic Station");
        mapView.getOverlayManager().add(marker);
    }

    @NonNull
    private GeoPoint convertFromWgs84(@NonNull GeoPoint pGeopoint) {
        return (MODE == Mode.MAAAMET) ? CustomTileSystem.fromWgs84(pGeopoint) : pGeopoint;
    }
}
