package com.vaadin;

import java.time.ZonedDateTime;

/**
 * Repräsentiert Marker mit geforderten Attributen,
 * wird später mit LMarker aus dem v-Leaflet Add-On verknüpft
 * @author Fabian Wendel
 */
public class Marker { //TODO: implements Serializable, Cloneable ?

    /**
     * Längengrad
     */
    private double lati;
    /** Breitengrad */
    private double longi;

    /** ID (String) der Messung */
    private String idMessung;
    /** ID (String) des Messobjektes */
    private String idMessobjekt;
    /** Name des Messobjektes */
    private String nameMessobjekt;

    /** Startzeitpunkt der Messung (Java ZonedDateTime) */
    private ZonedDateTime zeitpunktMessstart;

    /** Grund der Messung, Typ (String) */
    private String grundTyp = "";
    /** Grund der Messung, Beschreibung (String) */
    private String grundBeschreibung = "";

    /** Anzahl der verwendeten Sensoren */
    private int anzahlSensoren;
    /** Maximale gemessene Frequenz */
    private double frequenz;


    /**
     * Konstruktor für ein Objekt der Klasse Marker
     * @param lati Längengrad
     * @param longi Breitengrad
     * @param idMessung ID der Messung
     * @param idMessobjekt ID des Messobjektes
     * @param nameMessobjekt Name des Messobjektes
     * @param zeitpunktMessstart Startzeitpunkt der Messung
     * @param grundTyp Grund der Messung, Typ
     * @param grundBeschreibung Grund der Messung, Beschreibung
     * @param anzahlSensoren Anzahl der verwendeten Sensoren
     * @param frequenz Maximale gemessene Frequenz
     */
    public Marker(double lati, double longi, String idMessung, String idMessobjekt, String nameMessobjekt,
                  ZonedDateTime zeitpunktMessstart, String grundTyp, String grundBeschreibung, int anzahlSensoren,
                  double frequenz) {
        this.lati = lati;
        this.longi = longi;
        this.idMessung = idMessung;
        this.idMessobjekt = idMessobjekt;
        this.nameMessobjekt = nameMessobjekt;
        this.zeitpunktMessstart = zeitpunktMessstart;
        this.grundTyp = grundTyp;
        this.grundBeschreibung = grundBeschreibung;
        this.anzahlSensoren = anzahlSensoren;
        this.frequenz = frequenz;
    }

    //TODO: Overwrite?

    /**
     * Ausgabe der Eigenschaften des Markers als Text für das Popup
     * @return Eigenschaften des Markers als String
     */
    public String toString() {
        return "<b>ID der Messung: </b>" + this.idMessung + "<br> "
                + "<b>ID des Messobjektes: </b>" + this.idMessobjekt + "<br> "
                + "<b>Name des Messobjektes: </b>" + this.nameMessobjekt + "<br> "
                + "<b>Startzeitpunkt der Messung: </b>" + this.zeitpunktMessstart.getDayOfMonth() + "."
                    + this.zeitpunktMessstart.getMonth().getValue() + "." + this.zeitpunktMessstart.getYear() + " um "
                    + this.zeitpunktMessstart.getHour() + ":" + this.zeitpunktMessstart.getMinute() + " Uhr" +   "<br> "
                + "<b>Grund der Messung (Typ): </b>" + this.grundTyp + "<br> "
                + "<b>Grund der Messung (Beschreibung): </b>" + this.grundBeschreibung + "<br> "
                + "<b>Anzahl der eingesetzten Sensoren: </b>" + this.anzahlSensoren + "<br> "
                + "<b>Maximal gemessene Frequenz: </b>" + this.frequenz + " Hz";
    }

    /**
     * Kurze Version der Ausgabe der Eigenschaften des Markers als Text für das Popup;
     * Für bessere Übersichtlichkeit
     * @return kurze Version der Eigenschaften des Markers als String
     */
    public String toShortString() {
        return "<b>ID der Messung: </b>" + this.idMessung + "<br> "
                + "<b>ID des Messobjektes: </b>" + this.idMessobjekt + "<br> "
                + "<b>Name des Messobjektes: </b>" + this.nameMessobjekt;
    }


    /**
     * Liefert den Längengrad des gewählten Markers zurück
     * @return Längengrad des Markers
     */
    public double getLati() {
        return lati;
    }

    /**
     * Setzen eines neuen Längengrades für den Marker
     * @param lati Längengrad des Markers
     */
    public void setLati(double lati) {
        this.lati = lati;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public String getIdMessung() {
        return idMessung;
    }

    public void setIdMessung(String idMessung) {
        this.idMessung = idMessung;
    }

    public String getIdMessobjekt() {
        return idMessobjekt;
    }

    public void setIdMessobjekt(String idMessobjekt) {
        this.idMessobjekt = idMessobjekt;
    }

    public String getNameMessobjekt() {
        return nameMessobjekt;
    }

    public void setNameMessobjekt(String nameMessobjekt) {
        this.nameMessobjekt = nameMessobjekt;
    }

    public ZonedDateTime getZeitpunktMessstart() {
        return zeitpunktMessstart;
    }

    public void setZeitpunktMessstart(ZonedDateTime zeitpunktMessstart) {
        this.zeitpunktMessstart = zeitpunktMessstart;
    }

    public String getGrundTyp() {
        return grundTyp;
    }

    public void setGrundTyp(String grundTyp) {
        this.grundTyp = grundTyp;
    }

    public String getGrundBeschreibung() {
        return grundBeschreibung;
    }

    public void setGrundBeschreibung(String grundBeschreibung) {
        this.grundBeschreibung = grundBeschreibung;
    }

    public int getAnzahlSensoren() {
        return anzahlSensoren;
    }

    public void setAnzahlSensoren(int anzahlSensoren) {
        this.anzahlSensoren = anzahlSensoren;
    }

    public double getFrequenz() {
        return frequenz;
    }

    public void setFrequenz(double frequenz) {
        this.frequenz = frequenz;
    }
}
