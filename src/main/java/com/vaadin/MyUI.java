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
    /**
     * Erzeugung der Karte(nkomponente)
     * zeigt nur Kartenkomponente ohne Hintergrund an (also graues Feld)
     * LMap Objekt nicht direkt in init, um es später leichter referenzieren zu können
     */
    private LMap map = new LMap();

    /**
     * Erzeugen eins Kartenlayers
     */
    private LTileLayer osmTileLayer = new LOpenStreetMapLayer();


    /**
     * Wird ausgelöst, wenn ein Nutzer die Webanwendung öffnet
     * @param vaadinRequest
     */
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        /**
         * Blendet OSM-Karte in die Kartenkomponente ein
         */
        map.addBaseLayer(osmTileLayer, "OSM");

        /**
         * Legt den Startpunkt der Kartenansicht fest, wenn die Webanwendung geöffnet wird
         */
        map.setCenter(51.068656, 13.773228);

        setMarkerHeadquarter();
        generateDummyMarker();

        // Hinzufügen der Kartenkpomponente (also inkl. erzeugter Marker darauf) zur Webanwendung
        setContent(map);
    }

    //************* Methoden welche Marker erzeugen **************
    /**
     * Setzt den ersten Marker am Standorts des Firmengebäudes
     */
    public void setMarkerHeadquarter() {
        LMarker headquarterMarker = new LMarker(51.068656, 13.773228);
        headquarterMarker.setPopup("Herzlich willkommen bei conimon!");
        map.addComponent(headquarterMarker);
    }

    /**
     * Dateneingabe für die Dummymarker, abgekapselt von deren Erzeugung
     */
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

    /**
     * Generiert die Dummymarker auf Grundlage der eingegeben Daten in generateDummyMarker
     * @param lati
     * @param longi
     * @param idMessung
     * @param idMessobjekt
     * @param nameMessobjekt
     * @param zeitpunktMessstart
     * @param grundTyp
     * @param grundBeschreibung
     * @param anzahlSensoren
     * @param frequenz
     */
    public void makeDummyMarker(double lati, double longi, String idMessung, String idMessobjekt, String nameMessobjekt,
                                ZonedDateTime zeitpunktMessstart, String grundTyp, String grundBeschreibung,
                                int anzahlSensoren, double frequenz) {
        // Erzeugung eines Objekts der Klasse Marker zur Speicherung und Darstellung der Attribute im Popup
        Marker dummyMarkerData = new Marker(lati, longi, idMessung, idMessobjekt, nameMessobjekt, zeitpunktMessstart,
                grundTyp, grundBeschreibung, anzahlSensoren, frequenz);
        // Erzeugung eines LMarkers (v-Leaflet) auf Grundlage des Objekts der Klasse Marker
        LMarker dummyMarker = new LMarker(dummyMarkerData.getLati(), dummyMarkerData.getLongi());
        // Um zu demonstrieren, dass das Einblenden ausgewählter Informationen im Popup übersichtlicher sein könnte
        if (!new Random().nextBoolean()) {
            dummyMarker.setPopup(dummyMarkerData.toString());
        } else {
            dummyMarker.setPopup(dummyMarkerData.toShortString());
        }
        // Hinzufügen des erzeugten LMarkers zur Karte
        map.addComponent(dummyMarker);
    }

    //************* Abschlussklausel ***************
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
