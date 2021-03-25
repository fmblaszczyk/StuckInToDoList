package stuckinjava.todolist.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stuckinjava.todolist.security.model.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);

}
