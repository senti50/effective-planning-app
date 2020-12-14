package pl.senti.effectiveplanningapp.model.request;

import pl.senti.effectiveplanningapp.model.entities.Priority;
import pl.senti.effectiveplanningapp.model.entities.Task;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskWriteModel {

    private Long taskListId;

    @NotBlank(message = "Task name must  not be empty")
    private String name;

    private String date;

    private Priority priority;

    private String noteDescription;

    private String subtaskName;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public String getSubtaskName() {
        return subtaskName;
    }

    public void setSubtaskName(String subtaskName) {
        this.subtaskName = subtaskName;
    }


    public Task toTaskEntity()  {
        Task newTask = new Task();
        newTask.setTaskListId(taskListId);
        newTask.setName(name);

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date parsedDate = dateFormat.parse(date);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            newTask.setDate(timestamp);

        }catch (Exception e){
            e.printStackTrace();
        }
        newTask.setPriority(priority);
        newTask.setNoteDescription(noteDescription);
        newTask.setSubtaskName(subtaskName);
        return newTask;
    }
}
