package org.urdad.services.mocking.example.legalEntities;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author fritz@solms.co.za
 */
public abstract class LegalEntity {
    public LegalEntity(String name, Address address) {
        this.name = name;
        this.address = address;}

    public String getName()	{return name;}
    public Address getAddress() {return address;}
    public void setAddress(Address address) {this.address = address;}

    @NotEmpty(message="Name must be specified") private String name;
    @NotNull @Valid	private Address address;
}
