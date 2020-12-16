package pl.senti.effectiveplanningapp.model.response;

import pl.senti.effectiveplanningapp.model.entities.Priority;
import pl.senti.effectiveplanningapp.model.entities.Task;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TaskReadModel {

    private final Long id;

    private final Long taskListId;

    private final String name;

    private final boolean isComplete;

    private final Timestamp date;

    private final Priority priority;

    private final String noteDescription;

    private final String subtaskName;

    public Priority getPriority() {
        return priority;
    }

    public TaskReadModel(Task source) {
        this.id = source.getId();
        this.taskListId = source.getTaskListId();
        this.name = source.getName();
        this.isComplete = source.isComplete();
        this.date = source.getDate();
        this.priority=source.getPriority();
        this.noteDescription= source.getNoteDescription();
        this.subtaskName=source.getSubtaskName();
    }


    public Long getId() {
        return id;
    }



    public Long getTaskListId() {
        return taskListId;
    }



    public String getName() {
        return name;
    }



    public boolean isComplete() {
        return isComplete;
    }



    public String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH.mm").format(date);
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public String getSubtaskName() {
        return subtaskName;
    }
}
