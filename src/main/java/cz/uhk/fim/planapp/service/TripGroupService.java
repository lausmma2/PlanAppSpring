package cz.uhk.fim.planapp.service;

import cz.uhk.fim.planapp.domain.Trip;
import cz.uhk.fim.planapp.domain.TripGroup;
import cz.uhk.fim.planapp.domain.User;
import cz.uhk.fim.planapp.exceptions.TripGroupIdException;
import cz.uhk.fim.planapp.exceptions.TripNotFoundException;
import cz.uhk.fim.planapp.repository.TripGroupRepository;
import cz.uhk.fim.planapp.repository.TripRepository;
import cz.uhk.fim.planapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TripGroupService {

    @Autowired
    private TripGroupRepository tripGroupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TripService tripService;

    @Autowired
    private TripRepository tripRepository;

    public TripGroup findTripGroupByTripGroupId(String tripGroupIdentifier, String username){
        return tripGroupRepository.findTripGroupByTripGroupIdentifier(tripGroupIdentifier);
    }

    public void setTripGroupToTrip(String tripGroupIdentifier, String username, String tripIdentifier){
        TripGroup tripGroup = tripGroupRepository.findTripGroupByTripGroupIdentifier(tripGroupIdentifier);
        Trip trip = tripService.findTripByTripIdentifier(tripIdentifier, username);
        trip.setTripGroup(tripGroup);

        tripRepository.save(trip);
    }

    public TripGroup saveOrUpdateTripGroup(TripGroup tripGroup, String username){

        if(tripGroup.getTripGroupId() != null){
            TripGroup existingTripGroup = tripGroupRepository.findTripGroupByTripGroupIdentifier(tripGroup.getTripGroupIdentifier());

            /*if(existingTripGroup != null && !existingTripGroup.getUser().getUsername().equals(username)){
                throw new TripNotFoundException("Trip not found in your account!");
            }else */
            if(existingTripGroup == null){
                throw new TripNotFoundException("Trip Group with ID: '" + tripGroup.getTripGroupIdentifier() + "' cannot be updated because it doesn't exist!");
            }
        }
        try{
            // TODO: 03.02.2020 - Mrknout na backlog...
            // TODO: 03.02.2020 - Update nefunguje update_at... pořád null
            User user = userRepository.findByUsername(username);
            //tripGroup.setUser(user);
            tripGroup.setTripGroupCreator(user.getUsername());
            tripGroup.setTripGroupIdentifier(tripGroup.getTripGroupIdentifier().toUpperCase());

            return tripGroupRepository.save(tripGroup);

        }catch (Exception e){
            throw new TripGroupIdException("Trip Group ID: '" +
                    tripGroup.getTripGroupIdentifier().toUpperCase() +
                    "' already exists");
        }
    }

    public Iterable<TripGroup> findAllTripGroups(String username){
        return tripGroupRepository.findAllByTripGroupCreator(username);
    }

    public Iterable<TripGroup> findAllTripGroups(Set<User> user){
        //return tripGroupRepository.findAll();
        return tripGroupRepository.getAllByUsers(user);
    }

    public void deleteTripGroupByIdentifier(String tripGroupId, String username){
        //TripGroup tripGroup = tripGroupRepository.findTripGroupByTripGroupIdentifier(tripGroupId);
        //System.out.println(tripGroup.getTrips());
        /*if(!tripGroup.getTrips().isEmpty()){
            Trip trip = tripRepository.findTripByTripGroup(tripGroup);
            trip.setTripGroup(null);
        }*/

        tripGroupRepository.delete(findTripGroupByTripGroupId(tripGroupId, username));
    }
}
