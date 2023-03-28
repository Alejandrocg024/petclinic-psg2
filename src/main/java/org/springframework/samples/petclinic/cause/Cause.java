package org.springframework.samples.petclinic.cause;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.samples.petclinic.model.BaseEntity;

@Entity
@Table(name = "causes")
public class Cause extends BaseEntity {
	
	@NotBlank
	@Column(name = "cause_name")
	private String name;
	
	@NotBlank
	@Column(name = "cause_description")
	private String description;
	
	@NotNull
	@Column(name = "cause_target")
	private Double budgetTarget;
	
	@NotBlank
	@Column(name = "cause_organization")
	private String organization;

	/* esta propiedad tomará true por predeterminado y cuando la suma de las donaciones sea 
	mayor o igual al target, este booleano pasará a false para que ya no se puedan hacer 
	mas donaciones */
	@Column(name = "cause_active")
	private boolean active;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cause", fetch = FetchType.EAGER)
	private Collection<Donation> donations;

	private Double amountDonated;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getBudgetTarget() {
		return this.budgetTarget;
	}

	public void setBudgetTarget(Double budgetTarget) {
		this.budgetTarget = budgetTarget;
	}

	public String getOrganization() {
		return this.organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	protected Collection<Donation> getDonationsInternal() {
		if (this.donations == null) {
			this.donations = new ArrayList<Donation>();
		}
		return this.donations;
	}

	protected void setDonationsInternal(Collection<Donation> donations) {
		this.donations = donations;
	}

	public List<Donation> getDonations() {
		List<Donation> sortedDonations = new ArrayList<>(getDonationsInternal());
		PropertyComparator.sort(sortedDonations, new MutableSortDefinition("date", false, false));
		return Collections.unmodifiableList(sortedDonations);
	}

	public void addDonation(Donation donation) {
		getDonationsInternal().add(donation);
		donation.setCause(this);
	}

    public void removeDonation(Donation donation) {
		getDonationsInternal().remove(donation);
		donation.setCause(this);
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setAmountDonated() {
		Double res = 0.;
		for(Donation d: getDonations()){
			res += d.getAmount();
		}
		this.amountDonated = res;
	}

	public Double getAmountDonated() {
		setAmountDonated();
		return this.amountDonated;
	}
}
