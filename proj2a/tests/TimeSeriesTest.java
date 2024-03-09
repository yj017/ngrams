import ngrams.TimeSeries;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/** Unit Tests for the TimeSeries class.
 *  @author Josh Hug
 */
public class TimeSeriesTest {
    @Test
    public void testFromSpec() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);
        // expected: 1991: 0,
        //           1992: 100
        //           1994: 600
        //           1995: 500

        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(1991, 1992, 1994, 1995));

        assertThat(totalPopulation.years()).isEqualTo(expectedYears);

        List<Double> expectedTotal = new ArrayList<>
                (Arrays.asList(0.0, 100.0, 600.0, 500.0));

        for (int i = 0; i < expectedTotal.size(); i += 1) {
            assertThat(totalPopulation.data().get(i)).isWithin(1E-10).of(expectedTotal.get(i));
        }
    }

    @Test
    public void testEmptyBasic() {
        TimeSeries catPopulation = new TimeSeries();
        TimeSeries dogPopulation = new TimeSeries();

        assertThat(catPopulation.years()).isEmpty();
        assertThat(catPopulation.data()).isEmpty();

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);

        assertThat(totalPopulation.years()).isEmpty();
        assertThat(totalPopulation.data()).isEmpty();
    }
    @Test
    public void testYear() {
        TimeSeries cat = new TimeSeries();
        cat.put(1, 1.0);
        cat.put(2, 2.0);
        cat.put(3, 4.0);
        cat.put(4, 5.0);

        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(1, 2, 3, 4));
        assertThat(cat.years()).isEqualTo(expectedYears);

        cat.put(5, 3.0);

        List<Integer> expectedYears2 = new ArrayList<>
                (Arrays.asList(1, 2, 3, 4, 5));
        assertThat(cat.years()).isEqualTo(expectedYears2);
    }

    @Test
    public void testData() {
        TimeSeries cat = new TimeSeries();
        cat.put(1, 1.0);
        cat.put(2, 2.0);
        cat.put(3, 3.0);
        cat.put(4, 4.0);

        List<Double> expectedYears = new ArrayList<>
                (Arrays.asList(1.0, 2.0, 3.0, 4.0));
        assertThat(cat.data()).isEqualTo(expectedYears);

        cat.put(5, 5.0);

        List<Double> expectedYears2 = new ArrayList<>
                (Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0));
        assertThat(cat.data()).isEqualTo(expectedYears2);
    }
    @Test
    public void testPlus() {
        TimeSeries cat = new TimeSeries();
        TimeSeries dog = new TimeSeries();

        cat.put(1, 1.0);
        cat.put(2, 2.0);
        cat.put(3, 3.0);
        cat.put(4, 4.0);

        dog.put(1, 1.0);
        dog.put(2, 2.0);
        dog.put(3, 3.0);
        dog.put(4, 4.0);

        List<Double> expectedYears = new ArrayList<>
                (Arrays.asList(2.0, 4.0, 6.0, 8.0));

        assertThat(cat.plus(dog).data()).isEqualTo(expectedYears);

        dog.put(5, 5.0);

        List<Double> expectedYears2 = new ArrayList<>
                (Arrays.asList(2.0, 4.0, 6.0, 8.0, 5.0));

        assertThat(cat.plus(dog).data()).isEqualTo(expectedYears2);
    }
    @Test
    public void testDivide() {
        TimeSeries cat = new TimeSeries();
        TimeSeries dog = new TimeSeries();

        cat.put(1, 1.0);
        cat.put(2, 2.0);
        cat.put(3, 3.0);
        cat.put(4, 4.0);

        dog.put(1, 1.0);
        dog.put(2, 2.0);
        dog.put(3, 3.0);
        dog.put(4, 4.0);

        List<Double> expectedYears = new ArrayList<>
                (Arrays.asList(1.0, 1.0, 1.0, 1.0));

        assertThat(cat.dividedBy(dog).data()).isEqualTo(expectedYears);

        dog.put(5, 5.0);

        assertThat(cat.dividedBy(dog).data()).isEqualTo(expectedYears);
    }
    @Test
    public void testConstructor() {
        TimeSeries cat = new TimeSeries();
        TimeSeries dog = new TimeSeries();

        cat.put(1, 1.0);
        cat.put(2, 2.0);
        cat.put(3, 3.0);
        cat.put(4, 4.0);

        dog.put(1, 1.0);
        dog.put(2, 2.0);

        assertThat(new TimeSeries(cat, 1, 2)).isEqualTo(dog);
    }
} 