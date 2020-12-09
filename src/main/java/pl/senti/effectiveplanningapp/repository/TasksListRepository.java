package pl.senti.effectiveplanningapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.senti.effectiveplanningapp.model.entities.TaskList;

import java.util.List;
import java.util.Optional;

@Repository
public interface TasksListRepository extends JpaRepository<TaskList, Long> {

    List<TaskList> findAllByUserId(Long id);

    Optional<TaskList> findById(Long id);
}