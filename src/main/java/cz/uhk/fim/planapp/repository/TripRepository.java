package cz.uhk.fim.planapp.repository;

import cz.uhk.fim.planapp.domain.Trip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends CrudRepository<Trip, Long> {

    Trip findTripByTripIdentifier(String tripIdentifier);
    Iterable<Trip> findAllByTripCreator(String username);

    @Override
    Iterable<Trip> findAll();

    @Override
    void delete(Trip trip);
}
