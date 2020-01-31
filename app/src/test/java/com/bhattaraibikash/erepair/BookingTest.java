package com.bhattaraibikash.erepair;

import com.bhattaraibikash.erepair.bll.BookingBLL;
import com.bhattaraibikash.erepair.models.Booking;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class BookingTest {
    @Test
    public void testBooking(){
        Booking booking = new Booking("AC not heating.", "01/08/2020", "12 – 02 PM", "Jadibuti, Ktm", "service_id");
        BookingBLL bookingBLL = new BookingBLL();
        boolean result = bookingBLL.booking("Bearer Token", booking);
        assertTrue(result);
    }
}
