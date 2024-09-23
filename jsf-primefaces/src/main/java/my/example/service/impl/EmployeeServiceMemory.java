package my.example.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import com.github.javafaker.Faker;

import my.example.model.Employee;
import my.example.service.EmployeeServiceable;
import my.example.service.qulifier.Repository;


@ApplicationScoped
@Repository(name = Repository.MEMORY)
public class EmployeeServiceMemory implements EmployeeServiceable {

	protected static Map<String, Employee> employeeMap = new HashMap<>();

	private static final Faker faker = new Faker();

	public void add(Employee employee) {
		employeeMap.put(employee.getId(), employee);
	}

	public int update(Employee employee) {
		if (employeeMap.containsKey(employee.getId())) {
			employeeMap.put(employee.getId(), employee);
			return 1;
		} else {
			return 0;
		}
	}
	public List<Employee> search(Employee employee) {
		String firstNameQuery = (employee.getFirstName() != null) ? employee.getFirstName().toLowerCase() : "";
		String lastNameQuery = (employee.getLastName() != null) ? employee.getLastName().toLowerCase() : "";

		List<Employee> results = new ArrayList<>();

		for (Employee e : employeeMap.values()) {
			boolean matchesFirstName = firstNameQuery.isEmpty()
					|| e.getFirstName().toLowerCase().contains(firstNameQuery);
			boolean matchesLastName = lastNameQuery.isEmpty() || e.getLastName().toLowerCase().contains(lastNameQuery);

			if (matchesFirstName && matchesLastName) {
				results.add(e);
			}
		}
		return results;
	}

	public int delete(String id) {
		if (employeeMap.containsKey(id)) {
			employeeMap.remove(id);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<Employee> getEmployees(int size) {
		List<Employee> employeeList = new ArrayList<>(employeeMap.values());
		int minAge = 2;
		int maxAge = 100;
		if (size > employeeList.size()) {
			for (int i = employeeList.size(); i < size; i++) {
				int randomAge = faker.number().numberBetween(minAge, maxAge);
				LocalDate hireDate = LocalDate.now().minusYears(randomAge).withDayOfYear(faker.number().numberBetween(1, 365));
				Employee mockEmployee = new Employee();
				mockEmployee.setFirstName(faker.name().firstName());
				mockEmployee.setLastName(faker.name().lastName());
				mockEmployee.setBirthDate(hireDate);
				employeeList.add(mockEmployee);
				employeeMap.put(mockEmployee.getId(), mockEmployee);

			}
		}
		return employeeList.subList(0, Math.min(size, employeeList.size()));
	}
}
