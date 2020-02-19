package cz.uhk.fim.planapp.repository;

import cz.uhk.fim.planapp.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    User findByUsername(String username);
    User findUserByVisibleId(String visibleId);
    User getByUserId(Long id); //findByUserId se dá taky použít, ale je to Optional<>
}