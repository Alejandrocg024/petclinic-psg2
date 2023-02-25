package org.springframework.samples.petclinic.hotel;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public void save(Booking p) throws DataAccessException{
        bookingRepository.save(p); 
    }
    
    @Transactional(readOnly = true)
    public List<Booking> findBookingsByOwner(Integer id){
        return bookingRepository.findBookingsByOwnerId(id);
    }

    @Transactional(readOnly = true)
    public List<Booking> getBookings(){
        return bookingRepository.findAll();
    }
    
}
