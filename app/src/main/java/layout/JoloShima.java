package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androboys.marineresource.R;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.api.IMapController;
import org.osmdroid.api.Marker;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.MyLocationOverlay;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.SimpleLocationOverlay;


import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class JoloShima extends Fragment {

    Button mark;
    TextView t1;
    Context appState;

    int stats=0;
    private MapView mapView;
    private IMapController mapController;
    private SimpleLocationOverlay mMyLocationOverlay;
    private ScaleBarOverlay mScaleBarOverlay;
    ItemizedIconOverlay<OverlayItem> currentLocationOverlay;
    DefaultResourceProxyImpl resourceProxy;

    Drawable myCurrentLocationMarker;
    final ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();


    String lati = "21.694560";
    String longi = "89.115177";

    ////Convert string to double
    double lati1 = Double.parseDouble(lati);
    double longi1 = Double.parseDouble(longi);



    public GeoPoint BERLIN = new GeoPoint(lati1,longi1);
    public GeoPoint BERLIN2 = new GeoPoint(21.7129989,91.0160191);



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_jolo_shima, container, false);
        t1 = (TextView)rootView.findViewById(R.id.t1);
        mark= (Button) rootView.findViewById(R.id.btn_pin);
        mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMyLocationOverlay = new SimpleLocationOverlay(getActivity());
                mapView.getOverlays().add(mMyLocationOverlay);

                mScaleBarOverlay = new ScaleBarOverlay(getActivity());
                mapView.getOverlays().add(mScaleBarOverlay);
//      this.mapView

                /////////////////
                resourceProxy = new DefaultResourceProxyImpl(getActivity().getApplicationContext());
                GeoPoint  currentLocation = new GeoPoint(55.860863,37.115046);
                GeoPoint  currentLocation2 = new GeoPoint(63.557413,-156.102119);


                OverlayItem myLocationOverlayItem2 = new OverlayItem("Here", "Current Position", BERLIN2);
////2nd
                Drawable myCurrentLocationMarker = getResources().getDrawable(R.drawable.nouka);
                myLocationOverlayItem2.setMarker(myCurrentLocationMarker);
                // myLocationOverlayItem.setMarkerHotspot(HotspotPlace.CENTER); //no working/


                items.add(myLocationOverlayItem2);

                currentLocationOverlay = new ItemizedIconOverlay<OverlayItem>(items,
                        new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                            public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                                return true;
                            }
                            public boolean onItemLongPress(final int index, final OverlayItem item) {
                                return true;
                            }
                        }, resourceProxy);


                mapView.getOverlays().add(currentLocationOverlay);

            }
        });

        //Sayem: All code write here






        if((lati1 > 19.694560 || lati1 < 22.038344 ) && longi1 > 89.094560 || longi1 < 92.038344  )
        {
            t1.setText("Under Range");
        }
        else
        {
            t1.setText("Out Of Range");
        }



        appState = getActivity().getApplicationContext();
        mapView = (MapView) rootView.findViewById(R.id.mapview);
        mapView.setClickable(true);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        mapView.setUseDataConnection(true);
        mapView.setTileSource(TileSourceFactory.MAPQUESTOSM);

        IMapController mapViewController = mapView.getController();
        mapViewController.setZoom(8);
        mapViewController.setCenter(BERLIN);


        this.mMyLocationOverlay = new SimpleLocationOverlay(getActivity());
        this.mapView.getOverlays().add(mMyLocationOverlay);

        this.mScaleBarOverlay = new ScaleBarOverlay(getActivity());
        this.mapView.getOverlays().add(mScaleBarOverlay);
//      this.mapView

        /////////////////
        resourceProxy = new DefaultResourceProxyImpl(getActivity().getApplicationContext());
        GeoPoint  currentLocation = new GeoPoint(55.860863,37.115046);
        GeoPoint  currentLocation2 = new GeoPoint(63.557413,-156.102119);



        OverlayItem myLocationOverlayItem = new OverlayItem("Here", "Current Position", BERLIN);
////2nd
        Drawable myCurrentLocationMarker = this.getResources().getDrawable(R.drawable.nouka);
        myLocationOverlayItem.setMarker(myCurrentLocationMarker);
        // myLocationOverlayItem.setMarkerHotspot(HotspotPlace.CENTER); //no working/


        items.add(myLocationOverlayItem);



        currentLocationOverlay = new ItemizedIconOverlay<OverlayItem>(items,
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                        return true;
                    }
                    public boolean onItemLongPress(final int index, final OverlayItem item) {
                        return true;
                    }
                }, resourceProxy);


        this.mapView.getOverlays().add(this.currentLocationOverlay);



        return rootView;
    }


}
