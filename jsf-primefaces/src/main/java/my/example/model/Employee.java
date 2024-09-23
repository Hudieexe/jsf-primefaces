package my.example.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Entity
@Table(name = "DATA_EMPLOYEE")
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name= "ID")
	private String id;
	@Column(name= "FIRST_NAME")
	private String firstName;
	@Column(name= "LAST_NAME")
	private String lastName;
	@Column(name= "BIRTH_DATE")
	private LocalDate birthDate;

	public Employee() {
		id = UUID.randomUUID().toString();
	}

	public Period getAge(LocalDate birthDate) {
		if (birthDate != null) {
			return Period.between(birthDate, LocalDate.now());
		}
		return null;
	}

	// Clone method
	public Employee(Employee other) {
		this.id = other.id;
		this.firstName = other.firstName;
		this.lastName = other.lastName;
		this.birthDate = other.birthDate;
	}
}
