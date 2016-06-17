package org.urdad.services.mocking.example.legalEntities;

import java.time.Duration;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

public class Person extends LegalEntity
{
	public Person(String name, LocalDate dateOfBirth, Address address)
	{
            super(name, address);
            this.dateOfBirth = dateOfBirth;
	}
	public int getAge() {
            return (int) Duration.between(dateOfBirth, LocalDate.now()).toDays() / 365;
	}	

	@NotNull @Past private LocalDate dateOfBirth;
}