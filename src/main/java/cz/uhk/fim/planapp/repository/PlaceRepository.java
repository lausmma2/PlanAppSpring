package cz.uhk.fim.planapp.repository;

import cz.uhk.fim.planapp.domain.Place;
import cz.uhk.fim.planapp.domain.Trip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends CrudRepository<Place, Long> {

    Iterable<Place> findAllByTrip(Trip trip);
    Place findPlaceByTrip(Trip trip);
    Place findPlaceByTitleAndPlaceOwnerAndTrip(String title, String username, Trip trip);

    @Override
    void delete(Place place);
}
