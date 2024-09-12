package Lap1;

import javax.faces.bean.RequestScoped;
import javax.faces.event.NamedEvent;

@NamedEvent
@RequestScoped
public class SelectoneB {
    private String option;
    public String getOption() {
        return option;
    }
    public void setOption(String option) {
        this.option = option;
    }
}
