package org.springframework.samples.petclinic.cause;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "causeDonations")
public class CauseDonation extends BaseEntity{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cause_id")
    private Cause cause;
    
    private Double amount;

    public CauseDonation(Cause cause, double totalDonations) {
        this.cause = cause;
        this.amount = totalDonations;
    }
    
}
