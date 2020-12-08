package pl.senti.effectiveplanningapp.model.response;

import pl.senti.effectiveplanningapp.model.entities.TaskList;

public class TaskListReadModel {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskListReadModel(Long id, TaskList source){
        this.id = id;
        this.name=source.getName();
    }
}
