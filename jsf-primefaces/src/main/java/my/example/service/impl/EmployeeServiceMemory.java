package my.example.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import my.example.model.Employee;
import my.example.service.EmployeeServiceable;

@Slf4j
@ApplicationScoped
public class EmployeeServiceMemory implements EmployeeServiceable {

	protected static Map<String, Employee> employeeMap = new HashMap<>();

	private List<Employee> emloyees;

	
	@PostConstruct
	public void init() {
		log.debug("begin init EmployeeServiceMemory -> {}", emloyees);
		emloyees = new ArrayList<>();
		emloyees.add(new Employee("12", "Fu", "Xu", LocalDate.of(2003, 04, 06)));
		emloyees.add(new Employee("10", "Hi", "Sa", LocalDate.of(2011, 03, 16)));
		emloyees.add(new Employee("9", "Hu", "Wu", LocalDate.of(2020, 11, 03)));
		emloyees.add(new Employee("5", "Ca", "Je", LocalDate.of(2013, 02, 19)));
		emloyees.add(new Employee("4", "Ib", "dI", LocalDate.of(2002, 07, 26)));
		emloyees.add(new Employee("3", "Ax", "Se", LocalDate.of(2004, 12, 25)));
		emloyees.add(new Employee("1", "Rx", "Po", LocalDate.of(2000, 02, 01)));
		emloyees.add(new Employee("11", "Ga", "Ku", LocalDate.of(2009, 01, 18)));
		emloyees.add(new Employee("15", "Go", "Fo", LocalDate.of(2012, 05, 28)));
		log.debug("end init EmployeeServiceMemory -> {}", emloyees);
	}

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
		List<Employee> employeeList = new ArrayList<>();
		for (Map.Entry<String, Employee> entry : employeeMap.entrySet()) {
			employeeList.add(entry.getValue());
		}
		return employeeList;
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
		if (size > employeeList.size()) {
			for (int i = employeeList.size(); i < size; i++) {
				if (i < emloyees.size()) {
					Employee additionalEmployee = emloyees.get(i);
					employeeList.add(additionalEmployee);
					employeeMap.put(additionalEmployee.getId(), additionalEmployee);
				} else {
					log.warn("Not enough dummy employees to fill the requested size.");
					break;
				}
			}
		}
		return new ArrayList<>(employeeList.subList(0, size));

	}

}
