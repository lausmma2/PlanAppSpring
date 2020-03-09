package cz.uhk.fim.planapp.service;

import cz.uhk.fim.planapp.domain.TripType;
import cz.uhk.fim.planapp.repository.TripTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripTypeService {

    @Autowired
    private TripTypeRepository tripTypeRepository;

    public TripType findTripTypeByTripIdentifier(String tripTypeIdentifier){
        TripType tripType = tripTypeRepository.getTripTypeByTripTypeIdentifier(tripTypeIdentifier);

        /*if(trip == null){
            throw new TripIdException("Trip ID: '" + tripIdentifier + "' does not exist!");
        }

        if(!trip.getTripCreator().equals(username))
            throw new TripNotFoundException("Project not found in your account");*/

        return tripType;
    }

    public Iterable<TripType> findAllTripTypes(){
        return tripTypeRepository.findAll();
    }
}
