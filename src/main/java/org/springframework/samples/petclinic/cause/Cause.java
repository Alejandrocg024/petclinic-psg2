package org.springframework.samples.petclinic.cause;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "causes")
public class Cause extends BaseEntity {
	
	@NotNull
	@Column(name = "cause_name")
	private String name;
	
	@NotNull
	@Column(name = "cause_description")
	private String description;
	
	@NotNull
	@Column(name = "cause_target")
	private Double budgetTarget;
	
	@NotEmpty
	@Column(name = "cause_organization")
	private String organization;

	/* esta propiedad tomará true por predeterminado y cuando la suma de las donaciones sea 
	mayor o igual al target, este booleano pasará a false para que ya no se puedan hacer 
	mas donaciones */
	@NotNull
	@Column(name = "cause_active")
	private Boolean active;
}
