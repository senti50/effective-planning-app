package pl.senti.effectiveplanningapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.senti.effectiveplanningapp.model.entities.TaskList;

public interface TasksListRepository extends JpaRepository<TaskList,Long> {

}