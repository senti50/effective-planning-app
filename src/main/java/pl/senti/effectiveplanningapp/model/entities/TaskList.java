package pl.senti.effectiveplanningapp.model.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String name;

    @OneToMany(mappedBy="taskListId",cascade = CascadeType.PERSIST,orphanRemoval=true)
    private Set<Task> taskSet;

    public TaskList(){}

    public TaskList(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
