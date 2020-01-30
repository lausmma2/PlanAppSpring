package cz.uhk.fim.planapp.service;

import cz.uhk.fim.planapp.domain.Trip;
import cz.uhk.fim.planapp.exceptions.TripIdException;
import cz.uhk.fim.planapp.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public Trip saveOrUpdateTrip(Trip trip){
        try{
            trip.setTripIdentifier(trip.getTripIdentifier().toUpperCase());
            return tripRepository.save(trip);

        }catch (Exception e){
            throw new TripIdException("Project ID " +
                    trip.getTripIdentifier().toUpperCase() +
                    " already exists");
        }
    }

    public Iterable<Trip> findAllProjects(){
        return tripRepository.findAll();
    }
}
