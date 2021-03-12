package stuckinjava.todolist.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stuckinjava.todolist.security.model.ERole;
import stuckinjava.todolist.security.model.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName (ERole name);
}
