package pl.senti.effectiveplanningapp.model.response;

import pl.senti.effectiveplanningapp.model.entities.Task;

import java.sql.Date;

public class TaskReadModel {

    private Long id;

    private Long taskListId;

    private String name;

    private boolean isComplete;

    private Date date;

    public TaskReadModel(Task source) {
        this.id = source.getId();
        this.taskListId = source.getTaskListId();
        this.name = source.getName();
        this.isComplete = source.isComplete();
        this.date = source.getDate();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(Long taskListId) {
        this.taskListId = taskListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
