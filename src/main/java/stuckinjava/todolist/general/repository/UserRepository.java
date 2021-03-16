package stuckinjava.todolist.general.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stuckinjava.todolist.general.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername (String username);

    Boolean existsByUsername (String username);
    Boolean existsByEmail (String email);
}
