package pl.senti.effectiveplanningapp.model.request;

import javax.validation.constraints.NotEmpty;

public class TaskListWriteModel {

    @NotEmpty(message = "Name of the list must be not empty")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
