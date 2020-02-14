package com.bhattaraibikash.erepair;

import com.bhattaraibikash.erepair.bll.BookingBLL;
import com.bhattaraibikash.erepair.models.Booking;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class BookingTest {
    @Test
    public void testBooking(){
        Booking booking = new Booking("AC not cooling.", "02/14/2020", "12 – 02 PM", "Jadibuti, Ktm", "5e3fa3b554987216abcaf2bb");
        BookingBLL bookingBLL = new BookingBLL();
        boolean result = bookingBLL.booking("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZTQ2ZjcwNGY2NTJhNTM2NjY4Mjk4N2MiLCJpYXQiOjE1ODE3MTI3Mjd9.0FAp7WjVM89nwKipQxzqZ-D5sGh4i_KB_t08nT2FoGE", booking);
        assertTrue(result);
    }
}
