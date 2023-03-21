package org.springframework.samples.petclinic.adoptation;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.pet.Pet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Adoption extends BaseEntity{

    @NotNull
    private String description;

    @Column(name = "posting_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate posting_date;

    @OneToOne(optional = false)
    @JoinColumn(name = "pet_id")
    @NotNull
    private Pet pet;

    @OneToOne(optional = false)
    @NotNull
    private Owner owner;

    private Boolean status;

    
}
