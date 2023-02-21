package org.springframework.samples.petclinic.hotel;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.pet.Pet;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "pet_hotel_bookings")
public class PetHotelBooking extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne(optional = false)
    private Owner owner;
    
    @Column(name = "start_date")
    private LocalDate startDate;
    
    @Column(name = "end_date")
    private LocalDate endDate;
    
    

}
