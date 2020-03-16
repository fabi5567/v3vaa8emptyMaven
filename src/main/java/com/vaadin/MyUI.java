package com.vaadin;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import org.vaadin.addon.leaflet.LMap;
import org.vaadin.addon.leaflet.LMarker;
import org.vaadin.addon.leaflet.LOpenStreetMapLayer;
import org.vaadin.addon.leaflet.LTileLayer;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    // LMap Objekt nicht direkt in init, um es später leichter referenzieren zu können
    // zeigt nur Kartenkomponente ohne Hintergrund an
    private LMap map = new LMap();
    // Blendet OSM-Karte mit ein
    private LTileLayer osmTileLayer = new LOpenStreetMapLayer();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        // triggered when a user enters web application

        /*
        final VerticalLayout layout = new VerticalLayout();
        
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        // Button whose click listener dynamically adds a new Label component to the main layout
        button.addClickListener(e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
        });
        */

        // Geht nicht, da map nicht vom Typ component
        //layout.addComponents(name, button, map);

        map.addBaseLayer(osmTileLayer, "OSM");

        setMarkerHeadquarter();

        generateDummyMarker();

        map.setCenter(51.068656, 13.773228);

        setContent(map);
    }

    public void setMarkerHeadquarter() {
        LMarker headquarterMarker = new LMarker(51.068656, 13.773228);
        headquarterMarker.setPopup("Herzlich willkommen bei conimon!");
        map.addComponent(headquarterMarker);
    }

    public void generateDummyMarker(){
        makeDummyMarker(51.066127, 13.741090,
                "Bahnhof Dresden-Neustadt", "f16d3", "Gleis 1");
        makeDummyMarker(51.072093, 13.746551,
                "Bahnhof Dresden Bischofsplatz", "f16d4", "Gleis 2");
    }

    public void makeDummyMarker(double lati, double longi, String idMessung, String idMessobjekt, String nameMessobjekt) {
        Marker dummyMarkerData = new Marker(lati, longi, idMessung, idMessobjekt, nameMessobjekt);
        LMarker dummyMarker = new LMarker(dummyMarkerData.getLati(), dummyMarkerData.getLongi());
        dummyMarker.setPopup(dummyMarkerData.toString());
        map.addComponent(dummyMarker);
    }



    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
