package cz.uhk.fim.planapp.repository;

import cz.uhk.fim.planapp.domain.Trip;
import cz.uhk.fim.planapp.domain.TripGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripGroupRepository extends CrudRepository<TripGroup, Long> {

    TripGroup findTripGroupByTripGroupIdentifier(String tripGroupIdentifier);
    Iterable<TripGroup> findAllByTripGroupCreator(String username);
}
