package com.parkingspace.ParkingSpaceReservation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.parkingspace.utils.RandomStringGenerator;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void randomStringTester() {
        RandomStringGenerator randString = new RandomStringGenerator();
        String str = randString.getAlphaNumericCode(10);
        System.out.println(str);
        assertEquals(str.length(), 10);
    }
}
