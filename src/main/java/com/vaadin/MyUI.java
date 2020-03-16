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

import java.time.Period;
import java.time.ZonedDateTime;
import java.util.Random;

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

    // Wird ausgelöst, wenn ein Nutzer die Webanwendung öffnet
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        map.addBaseLayer(osmTileLayer, "OSM");
        map.setCenter(51.068656, 13.773228);

        setMarkerHeadquarter();
        generateDummyMarker();

        setContent(map);
    }

    // Methoden die Marker erzeugen
    public void setMarkerHeadquarter() {
        LMarker headquarterMarker = new LMarker(51.068656, 13.773228);
        headquarterMarker.setPopup("Herzlich willkommen bei conimon!");
        map.addComponent(headquarterMarker);
    }

    public void generateDummyMarker(){
        makeDummyMarker(51.066127, 13.741090,
                "Linie S1", "f16d3", "Bahnhof Dresden-Neustadt",
                ZonedDateTime.now().minus(Period.ofDays(5)), "Vorausschauende Instandhaltung",
                "Vorzeitiger Austausch der Gleise nötig?", 5, 24056.6);
        makeDummyMarker(51.072093, 13.746551,
                "Linie S1", "f16d4", "Bahnhof Dresden Bischofsplatz",
                ZonedDateTime.now().minus(Period.ofDays(4)), "Vorausschauende Instandhaltung",
                "Vorzeitiger Austausch der Gleise nötig?", 3, 24274.9);
        makeDummyMarker(51.055777, 13.724295,
                "Linie S1", "f16d5", "Bahnhof Dresden Mitte",
                ZonedDateTime.now().minus(Period.ofDays(3)), "Vorausschauende Instandhaltung",
                "Vorzeitiger Austausch der Gleise nötig?", 7, 23856.3);
    }

    public void makeDummyMarker(double lati, double longi, String idMessung, String idMessobjekt, String nameMessobjekt,
                                ZonedDateTime zeitpunktMessstart, String grundTyp, String grundBeschreibung, int anzahlSensoren,
                                double frequenz) {
        Marker dummyMarkerData = new Marker(lati, longi, idMessung, idMessobjekt, nameMessobjekt, zeitpunktMessstart, grundTyp, grundBeschreibung, anzahlSensoren, frequenz);
        LMarker dummyMarker = new LMarker(dummyMarkerData.getLati(), dummyMarkerData.getLongi());
        if (new Random().nextBoolean() == false) {
            dummyMarker.setPopup(dummyMarkerData.toString());
        } else {
            dummyMarker.setPopup(dummyMarkerData.toShortString());
        }
        map.addComponent(dummyMarker);
    }

    // Abschlussklausel
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
