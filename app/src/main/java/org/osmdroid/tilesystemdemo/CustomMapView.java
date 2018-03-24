package org.osmdroid.tilesystemdemo;

import android.content.Context;
import android.util.AttributeSet;

import org.osmdroid.util.ITileSystem;
import org.osmdroid.util.TileSystem;
import org.osmdroid.views.MapView;

public class CustomMapView extends MapView {

    private ITileSystem mTileSystem = TileSystem.DEFAULT;

    public CustomMapView(Context pContext) {
        super(pContext);
    }

    public CustomMapView(Context pContext, AttributeSet pAttrs) {
        super(pContext, pAttrs);
    }

    @Override
    public ITileSystem getTileSystem() {
        return mTileSystem;
    }

    public void setTileSystem(ITileSystem pTileSystem) {
        mTileSystem = pTileSystem;
    }
}
