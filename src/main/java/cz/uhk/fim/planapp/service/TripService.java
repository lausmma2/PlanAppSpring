package cz.uhk.fim.planapp.service;

import cz.uhk.fim.planapp.domain.Trip;
import cz.uhk.fim.planapp.domain.User;
import cz.uhk.fim.planapp.exceptions.TripIdException;
import cz.uhk.fim.planapp.exceptions.TripNotFoundException;
import cz.uhk.fim.planapp.repository.TripRepository;
import cz.uhk.fim.planapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserRepository userRepository;

    public Trip saveOrUpdateTrip(Trip trip, String username){

        //Nechápu?
        if(trip.getTripId() != null){
            Trip existingTrip = tripRepository.findTripByTripIdentifier(trip.getTripIdentifier());

            if(existingTrip != null && !existingTrip.getUser().getUsername().equals(username)){
                throw new TripNotFoundException("Trip not found in your account!");
            }else if(existingTrip == null){
                throw new TripNotFoundException("Trip with ID: '" + trip.getTripIdentifier() + "' cannot be updated because it doesn't exist!");
            }
        }
        try{
            // TODO: 03.02.2020 - Mrknout na backlog...
            // TODO: 03.02.2020 - Update nefunguje update_at... pořád null
            User user = userRepository.findByUsername(username);
            trip.setUser(user);
            trip.setTripCreator(user.getUsername());
            trip.setTripIdentifier(trip.getTripIdentifier().toUpperCase());

            return tripRepository.save(trip);

        }catch (Exception e){
            throw new TripIdException("Trip ID: '" +
                    trip.getTripIdentifier().toUpperCase() +
                    "' already exists");
        }
    }

    public Trip findTripByTripIdentifier(String tripIdentifier, String username){
        Trip trip = tripRepository.findTripByTripIdentifier(tripIdentifier);

        if(trip == null){
            throw new TripIdException("Trip ID: '" + tripIdentifier + "' does not exist!");
        }

        if(!trip.getTripCreator().equals(username))
            throw new TripNotFoundException("Project not found in your account");

        return trip;
    }

    public void deleteTripByIdentifier(String tripId, String username){
        /*Trip trip = tripRepository.findTripByTripIdentifier(tripId.toUpperCase());

        if(trip == null){
            throw new TripIdException("Cannot delete trip with ID: '" + tripId + "'. This trip does not exist");
        }*/
        tripRepository.delete(findTripByTripIdentifier(tripId, username));
    }

    public Iterable<Trip> findAllTrips(String username){
        return tripRepository.findAllByTripCreator(username);
    }
}
