package cz.uhk.fim.planapp.repository;

import cz.uhk.fim.planapp.domain.TripType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripTypeRepository extends CrudRepository<TripType, Long> {

    TripType getTripTypeByTripTypeIdentifier(String tripTypeIdentifier);
}
