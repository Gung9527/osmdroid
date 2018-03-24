package org.osmdroid.util;

public interface ITileSystem {

    BoundingBox getGeographicBoundingBox();

    double getMapSize(double pZoomLevel);

    double getTileSize(double pZoomLevel);

    PointL toPixels(double pLatitude, double pLongitude, double pMapSize, PointL pReuse);

    GeoPoint fromPixels(long pX, long pY, double pMapSize, GeoPoint pReuse);
}
