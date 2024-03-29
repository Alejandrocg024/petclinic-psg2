package org.springframework.samples.petclinic.cause;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.owner.Owner;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="donations")
public class Donation extends BaseEntity{
    @NotNull
    @Column(name = "amount")
    private Double amount;

    @Column(name = "donation_date")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate date;

	@NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id")
    private Owner owner;

	@NotNull
    @ManyToOne
	@JoinColumn(name = "cause_id")
	private Cause cause;
	


	/**
	 * Creates a new instance of Donation for the current date
	 */
	public Donation() {
		this.date = LocalDate.now();
	}
}
