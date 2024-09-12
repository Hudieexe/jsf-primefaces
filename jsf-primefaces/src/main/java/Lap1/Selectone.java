package Lap1;

import javax.faces.bean.RequestScoped;
import javax.faces.event.NamedEvent;
@NamedEvent
@RequestScoped
public class Selectone {
	 private String selectedOption;
	    public String getSelectedOption() {
	        return selectedOption;
	    }
}
	   