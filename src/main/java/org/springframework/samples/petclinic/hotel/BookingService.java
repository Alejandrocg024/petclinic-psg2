package org.springframework.samples.petclinic.hotel;

import java.time.LocalDate;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.hotel.exceptions.DuplicatedPetInHotelException;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public void save(Booking b) throws DataAccessException, DuplicatedPetInHotelException{
        List<Booking> petBookings = bookingRepository.findBookingsByPetId(b.getPet().getId());
        if((b.getStartDate().isAfter(b.getEndDate()) || b.getStartDate().isEqual(b.getEndDate())) ||
        (b.getStartDate().isBefore(LocalDate.now()))){
            throw new DuplicatedPetInHotelException();
        } else if(!petBookings.isEmpty()){
            for(Booking previousBooking : petBookings){
                if((b.getStartDate().isAfter(previousBooking.getStartDate()) && b.getEndDate().isBefore(previousBooking.getEndDate())) || //Fecha introducida está dentro de una fecha que ya existe
                (b.getStartDate().isBefore(previousBooking.getStartDate()) && b.getEndDate().isAfter(previousBooking.getEndDate())) || //Fecha introducida contiene a una fecha que ya existe
                (b.getStartDate().isBefore(previousBooking.getStartDate()) && b.getEndDate().isAfter(previousBooking.getStartDate())) || //Fecha introducida termina cuando ya ha comenzado una fecha existente
                (b.getStartDate().isBefore(previousBooking.getEndDate()) && b.getEndDate().isAfter(previousBooking.getEndDate())) //Fecha introducida empieza antes de que termine una fecha existente
                ){ 
                throw new DuplicatedPetInHotelException();
                }  
            }
        }
        bookingRepository.save(b); 
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
