package my.example.service;
import java.util.List;
import my.example.model.Employee;

public interface EmployeeServiceable {
	public void add(Employee employee);
	public int update(Employee employee);
	public List<Employee> search(Employee employee);
	public int delete(String id);
	public List<Employee> getEmployees(int size);
}
