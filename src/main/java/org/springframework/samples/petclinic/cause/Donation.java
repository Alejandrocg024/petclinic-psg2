package org.springframework.samples.petclinic.cause;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="donations")
public class Donation extends BaseEntity{
    @NotEmpty
    @Column(name = "amount")
    private Double amount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cause_id")
	private Cause cause;
}
