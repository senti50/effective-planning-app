package pl.senti.effectiveplanningapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.senti.effectiveplanningapp.model.entities.Task;

import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByTaskListId(Long id);
}
