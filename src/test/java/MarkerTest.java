import com.vaadin.Marker;
import org.junit.Test;
import org.junit.Assert;

import java.time.Period;
import java.time.ZonedDateTime;

public class MarkerTest {

    @Test
    public void testToShortString() {
        Marker testMarker = new Marker(51.066127, 13.741090, "Linie S1", "f16d3", "Bahnhof Dresden-Neustadt",
                ZonedDateTime.now().minus(Period.ofDays(5)), "Vorausschauende Instandhaltung",
                "Vorzeitiger Austausch der Gleise nötig?", 5, 24056.6);
        String actual = testMarker.toShortString();
        String expected = "<b>ID der Messung: </b>Linie S1<br> " +
                "<b>ID des Messobjektes: </b>f16d3<br> " +
                "<b>Name des Messobjektes: </b>Bahnhof Dresden-Neustadt";
        Assert.assertEquals(actual, expected);
    }
}