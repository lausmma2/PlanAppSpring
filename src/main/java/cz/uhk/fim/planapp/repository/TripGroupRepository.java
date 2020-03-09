package cz.uhk.fim.planapp.repository;

import cz.uhk.fim.planapp.domain.TripGroup;
import cz.uhk.fim.planapp.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TripGroupRepository extends CrudRepository<TripGroup, Long> {

    TripGroup findTripGroupByTripGroupIdentifier(String tripGroupIdentifier);
    Iterable<TripGroup> findAllByTripGroupCreator(String username);
    Iterable<TripGroup> getAllByUsers(Set<User> user);

    @Override
    void delete(TripGroup tripGroup);
}
