package pl.senti.effectiveplanningapp.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TaskListWriteModel {

    @NotBlank(message = "Name of the list must be not empty")
    @Size(min = 1,max = 50,message = "Name of the list must contain at least one character and no more than 50")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
