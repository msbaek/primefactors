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
        assertEquals(list(3), of(3));
    }

    private List<Integer> of(int n) {
        ArrayList<Integer> factors = new ArrayList<Integer>();
        if(n > 1)
            factors.add(n);
        return factors;
    }

    private List<Integer> list(Integer ... ints) {
        return Arrays.asList(ints);
    }
}
