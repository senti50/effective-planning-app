package pl.senti.effectiveplanningapp.model.response;

import pl.senti.effectiveplanningapp.model.entities.TaskList;

public class TaskListReadModel {

    private Long id;

    private String name;


    public TaskListReadModel( TaskList source){
        this.id = source.getId();
        this.name=source.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
