package my.example.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String firstName;

	private String lastName;

	private LocalDate birthDate;
	

	public Employee() {
		id = UUID.randomUUID().toString();
	}
	
	
	public String getAge(LocalDate birthDate) {
		if (birthDate != null) {
			Period period = Period.between(birthDate, LocalDate.now());
			return period.getYears() + " years " + period.getMonths() + " months " + "and " + period.getDays()
					+ " days.";
		}
		return null;
	}
	
	public Period getAgeBean(LocalDate birthDate) {
	     if (birthDate != null) {
	         return Period.between(birthDate, LocalDate.now()); 
	     }
	     return null; 
	 }
	
	//Clone method
    public Employee(Employee other) {
        this.id = other.id;
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.birthDate = other.birthDate;
    }
	 }
	