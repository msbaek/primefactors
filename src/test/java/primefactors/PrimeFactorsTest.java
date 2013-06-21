package primefactors;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PrimeFactorsTest {
    @Test public void
    canFactorIntoPrimes() {
        assertEquals(list(), of(1));
        assertEquals(list(2), of(2));
    }

    private List<Integer> of(int n) {
        return new ArrayList<Integer>();
    }

    private List<Integer> list(Integer ... ints) {
        return Arrays.asList(ints);
    }
}
