package cz.uhk.fim.planapp.controller;

import cz.uhk.fim.planapp.domain.Trip;
import cz.uhk.fim.planapp.domain.TripType;
import cz.uhk.fim.planapp.service.MapValidationErrorService;
import cz.uhk.fim.planapp.service.TripService;
import cz.uhk.fim.planapp.service.TripTypeService;
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

    @Autowired
    private TripTypeService tripTypeService;

    @RequestMapping(value = "/create-trip", method = RequestMethod.POST)
    public ResponseEntity<?> createNewTrip(@Valid @RequestBody Trip trip, BindingResult result, Principal principal){ //Principal je ze security package - person who is currently logged in

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null){
            return errorMap;
        }

        Trip trip1 = tripService.saveOrUpdateTrip(trip, principal.getName());
        return new ResponseEntity<Trip>(trip1, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{tripId}")
    public ResponseEntity<?> getTripById(@PathVariable String tripId, Principal principal){
        Trip trip = tripService.findTripByTripIdentifier(tripId, principal.getName());

        return new ResponseEntity<Trip>(trip, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Trip> getAllTrips(Principal principal){
        return tripService.findAllTrips(principal.getName());
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<?> deleteProject(@PathVariable String tripId, Principal principal){
        tripService.deleteTripByIdentifier(tripId, principal.getName());

        return new ResponseEntity<String>("Trip with ID: '" + tripId + "' was deleted", HttpStatus.OK);
    }

    @RequestMapping(value = "/trip-type/{tripTypeId}")
    public ResponseEntity<?> getTripTypeByIdentifier(@PathVariable String tripTypeId){
        TripType tripType = tripTypeService.findTripTypeByTripIdentifier(tripTypeId);
        return new ResponseEntity<TripType>(tripType, HttpStatus.OK);
    }

    @GetMapping("/trip-type/all")
    public Iterable<TripType> getAllTripTypes(){
        return tripTypeService.findAllTripTypes();
    }

    @RequestMapping(value = "/group/{tripGroupId}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTripsByTripGroup(@PathVariable String tripGroupId, Principal principal){
        Iterable<Trip> trips = tripService.getAllTripsOfTripGroup(tripGroupId, principal.getName());
        return new ResponseEntity<Iterable<Trip>>(trips, HttpStatus.OK);
    }

    @RequestMapping(value = "/{tripId}/{tripGroupId}", method = RequestMethod.GET)
    public ResponseEntity<?> getTripByTripIdentifierAndTripGroupIdentifier(@PathVariable String tripId,
                                                                           @PathVariable String tripGroupId,
                                                                           Principal principal){
        Trip trip = tripService.findTripByTripIdentifierAndTripGroupIdentifier(tripId, tripGroupId, principal.getName());
        return new ResponseEntity<Trip>(trip, HttpStatus.OK);
    }
}
