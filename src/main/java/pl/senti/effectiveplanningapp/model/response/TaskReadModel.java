package pl.senti.effectiveplanningapp.model.response;

import pl.senti.effectiveplanningapp.model.entities.Priority;
import pl.senti.effectiveplanningapp.model.entities.Task;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TaskReadModel {

    private Long id;

    private Long taskListId;

    private String name;

    private boolean isComplete;

    private Timestamp date;

    private Priority priority;

    private String noteDescription;

    private String subtaskName;

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
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH.mm").format(date);
        return timeStamp;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public String getSubtaskName() {
        return subtaskName;
    }
}
