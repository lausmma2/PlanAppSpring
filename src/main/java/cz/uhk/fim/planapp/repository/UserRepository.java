package cz.uhk.fim.planapp.repository;

import cz.uhk.fim.planapp.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    User findByUsername(String username);
    User findUserByVisibleId(String visibleId);
    User getByUserId(Long id); //findByUserId can be used too, but its Optional<>
    Set<User> findUserByUsername(String username);
}