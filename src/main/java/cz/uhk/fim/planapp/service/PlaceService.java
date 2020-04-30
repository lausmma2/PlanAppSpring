package cz.uhk.fim.planapp.service;

import cz.uhk.fim.planapp.domain.Place;
import cz.uhk.fim.planapp.domain.Trip;
import cz.uhk.fim.planapp.domain.TripGroup;
import cz.uhk.fim.planapp.domain.User;
import cz.uhk.fim.planapp.repository.PlaceRepository;
import cz.uhk.fim.planapp.repository.TripGroupRepository;
import cz.uhk.fim.planapp.repository.TripRepository;
import cz.uhk.fim.planapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripGroupRepository tripGroupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TripService tripService;

    public Place savePlace(String title, Double latitude, Double longitude, String distance, String tripIdentifier, String username){
        Place newPlace = new Place();
        Trip trip = tripService.findTripByTripIdentifier(tripIdentifier, username);

        newPlace.setTitle(title);
        newPlace.setLatitude(latitude);
        newPlace.setLongitude(longitude);
        newPlace.setDistance(distance);
        newPlace.setPlaceOwner(username);

        newPlace.setTrip(trip);

        System.out.println(newPlace.getTitle());

        return placeRepository.save(newPlace);
    }

    public Iterable<Place> findAllPlaces(String username, String tripIdentifier){
        Trip trip = tripService.findTripByTripIdentifier(username, tripIdentifier);
        return placeRepository.findAllByTrip(trip);
    }

    public void deletePlaceByTitleAndUsernameAndTrip(Double latitude, Double longitude, String username, String tripId){
        //Find trip
        Trip trip = tripRepository.findTripByTripIdentifier(tripId);

        //Check all places and if place is duplicated => delete all the duplicate ones
        for (int i = 0; i < trip.getPlaces().size(); i++) {
            if(trip.getPlaces().get(i).getLatitude().equals(latitude)
                    && trip.getPlaces().get(i).getLongitude().equals(longitude)){
                placeRepository.delete(trip.getPlaces().get(i));
            }
        }
    }

    public Iterable<Place> findAllPlacesByTripIdentifierAndTripGroupIdentifier(String tripIdentifier, String tripGroupIdentifier, String username){
        Trip trip = tripRepository.findTripByTripIdentifier(tripIdentifier);
        TripGroup tripGroup = tripGroupRepository.findTripGroupByTripGroupIdentifier(tripGroupIdentifier);
        User user = userRepository.findByUsername(username);

        return placeRepository.findAllByTrip(trip);
    }
}
