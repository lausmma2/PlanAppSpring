package cz.uhk.fim.planapp.controller;

import cz.uhk.fim.planapp.domain.Trip;
import cz.uhk.fim.planapp.service.MapValidationErrorService;
import cz.uhk.fim.planapp.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/trip")
@CrossOrigin
public class TripController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private TripService tripService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createNewTrip(@Valid @RequestBody Trip trip, BindingResult result, Principal principal){ //Principal je ze security package - person who is currently logged in

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null){
            return errorMap;
        }

        Trip trip1 = tripService.saveOrUpdateTrip(trip, principal.getName());
        return new ResponseEntity<Trip>(trip, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{tripId}")
    public ResponseEntity<?> getTripById(@PathVariable String tripId, Principal principal){
        Trip trip = tripService.findTripByTripIdentifier(tripId, principal.getName());

        return new ResponseEntity<Trip>(trip, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Trip> getAllTrips(Principal principal){
        return tripService.findAllProjects(principal.getName());
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<?> deleteProject(@PathVariable String tripId, Principal principal){
        tripService.deleteTripByIdentifier(tripId, principal.getName());

        return new ResponseEntity<String>("Trip with ID: '" + tripId + "' was deleted", HttpStatus.OK);
    }
}
