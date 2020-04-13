package cz.uhk.fim.planapp.controller;

import cz.uhk.fim.planapp.domain.TripGroup;
import cz.uhk.fim.planapp.domain.User;
import cz.uhk.fim.planapp.repository.TripGroupRepository;
import cz.uhk.fim.planapp.repository.UserRepository;
import cz.uhk.fim.planapp.service.MapValidationErrorService;
import cz.uhk.fim.planapp.service.TripGroupService;
import cz.uhk.fim.planapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/api/trip-group")
@CrossOrigin
public class TripGroupController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private TripGroupService tripGroupService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/create-trip-group", method = RequestMethod.POST)
    public ResponseEntity<?> createNewTrip(@Valid @RequestBody TripGroup tripGroup, BindingResult result, Principal principal){ //Principal je ze security package - person who is currently logged in

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null){
            return errorMap;
        }

        tripGroupService.saveOrUpdateTripGroup(tripGroup, principal.getName());

        User user1 = userRepository.findByUsername(principal.getName());
        TripGroup tripGroup1 = tripGroupService.findTripGroupByTripGroupId(tripGroup.getTripGroupIdentifier(), principal.getName());
        user1.getTripGroups().add(tripGroup1);

        userRepository.save(user1);
        return new ResponseEntity<User>(user1, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/add-trip-to-group/{tripId}/{tripGroupId}", method = RequestMethod.POST)
    public void addTripToGroup(@PathVariable String tripId, @PathVariable String tripGroupId, Principal principal){ //Principal je ze security package - person who is currently logged in
        tripGroupService.setTripGroupToTrip(tripGroupId, principal.getName(), tripId);
    }

    @RequestMapping(value = "/{tripGroupId}/{username}", method = RequestMethod.POST)
    public ResponseEntity<?> addUserToGroup(@PathVariable String tripGroupId, @PathVariable String username, Principal principal){
        tripGroupService.addUserToTripGroup(tripGroupId, username);
        return new ResponseEntity<String>("Added", HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<TripGroup> getAllTripGroups(Principal principal){
        Set<User> user = userRepository.findUserByUsername(principal.getName());
        return  tripGroupService.findAllTripGroups(user);
    }

    @DeleteMapping("/{tripGroupId}")
    public ResponseEntity<?> deleteProject(@PathVariable String tripGroupId, Principal principal){
        tripGroupService.deleteTripGroupByIdentifier(tripGroupId, principal.getName());

        return new ResponseEntity<String>("Trip Group with ID: '" + tripGroupId + "' was deleted", HttpStatus.OK);
    }

    @RequestMapping(value = "/{tripGroupId}")
    public ResponseEntity<?> getTripGroupById(@PathVariable String tripGroupId, Principal principal){
        TripGroup tripGroup = tripGroupService.findTripGroupByTripGroupId(tripGroupId, principal.getName());

        return new ResponseEntity<TripGroup>(tripGroup, HttpStatus.OK);
    }
}
