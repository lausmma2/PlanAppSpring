package cz.uhk.fim.planapp.repository;

import cz.uhk.fim.planapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByUserId(Integer userId);
    List<User> findAll();
}