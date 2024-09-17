package my.example.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import my.example.exception.AgeUnderLimitException;
import my.example.model.Employee;
import my.example.service.EmployeeServiceable;

@Slf4j
@Setter
@Getter
@ViewScoped
@Named
public class CrudBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mode;
	private Employee employeeCriteria;
	private Employee employeeEdit;
	private Employee selectedMember;
	private List<Employee> employeeList;

	@Inject
	private EmployeeServiceable service;

	@PostConstruct
	public void init() {
		mode = "R";
		employeeCriteria = new Employee();
		employeeList = service.getEmployees(9);
		employeeList = service.search(employeeCriteria);
	}

	public void searchBtnOnclick() {
		log.debug("begin searchBtnOnclick employeeList -> {}", employeeList);
		employeeList = service.search(employeeCriteria);
		log.debug("end searchBtnOnclick, {}", employeeList);
	}

	public void addBtnOnclick() {
		log.debug("begin addBtnOnclick employeeEdit -> {}", employeeEdit);
		this.mode = "C";
		employeeEdit = new Employee();
		log.debug("end addBtnOnclick, {}", employeeEdit);
	}

	public void editBtnOnclick(Employee p) {
		log.debug("begin editBtnOnclick employeeEdit -> {}", employeeEdit);
		mode = "U";
		employeeEdit = new Employee(p);

		log.debug("end editBtnOnclick, {}", employeeEdit);
	}

	public void saveBtnOnclick() {
		try {
			log.debug("begin saveBtnOnclick employeeEdit -> {}, selectedMember -> {}", employeeEdit, selectedMember);
			if (this.employeeEdit.getAgeBean(this.employeeEdit.getBirthDate()).getYears() < 2) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "อายุต้องมากกว่า 2 ปี", "อายุต้องมากกว่า 2 ปี"));
				throw new AgeUnderLimitException("Age must be more than 2 years.");
			} 

			
			service.add(employeeEdit);
			mode = "U";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "บันทึกข้อมูลเรียบร้อย", "บันทึกข้อมูลเรียบร้อย"));
			this.selectedMember = new Employee(employeeEdit);
		} catch (AgeUnderLimitException e) {
			log.error("error employeeEdit {}", employeeEdit);
			log.error("error Exception {}", e);
		} finally {
			log.debug("end saveBtnOnclick, {}", employeeEdit);
		}
	}

	public void updateBtnOnclick() {
		try {
			log.debug("begin updateBtnOnclick employeeEdit -> {}, selectedMember -> {}", employeeEdit, selectedMember);
			if (this.employeeEdit.getAgeBean(this.employeeEdit.getBirthDate()).getYears() < 2) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "อายุต้องมากกว่า 2 ปี", "อายุต้องมากกว่า 2 ปี"));
				employeeEdit = new Employee(selectedMember);
				throw new AgeUnderLimitException("Age must be more than 2 years.");
			}
			service.update(employeeEdit);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "บันทึกข้อมูลเรียบร้อย", "บันทึกข้อมูลเรียบร้อย"));
		} catch (AgeUnderLimitException e) {
			log.error("error employeeEdit {}", employeeEdit);
			log.error("error Exception {}", e);
		} finally {
			log.debug("end updateBtnOnclick, {}", employeeEdit);
		}

	}

	public void deleteBtnOnclick() {
		log.debug("begin deleteBtnOnclick employeeEdit -> {}", employeeEdit);
		service.delete(employeeEdit.getId());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "ลบสำเร็จ", "ลบสำเร็จ"));
		log.debug("end deleteBtnOnclick, {}", employeeEdit);
	}

	public void backBtnOnclick() {
		log.debug("begin backBtnOnclick mode -> {}", mode); // * เช้าเป็น R
		mode = "R";
		employeeList = service.search(employeeCriteria);
		log.debug("end backBtnOnclick, {}", mode);
	}

	public void resetBtnOnclick() {
		log.debug("begin resetBtnOnclick mode -> {}", mode); // * เช้าเป็น R
		switch (mode) {
		case "C":
			this.employeeEdit = new Employee();
			break;
		case "U":
			this.employeeEdit = new Employee(selectedMember);
			break;
		case "R":
			this.employeeCriteria = new Employee();
			break;
		default:
			break;
		}

		log.debug("end resetBtnOnclick {}", mode);
	}

	public void onRowSelect(SelectEvent<Employee> event) {
		log.debug("begin onRowSelect mode{}", "mode -> {}", mode, mode);
		selectedMember = event.getObject();
		if (selectedMember != null) {
			employeeEdit = new Employee(selectedMember);
			mode = "U";
		}
		log.debug("end onRowSelect {}", mode);
	}
}