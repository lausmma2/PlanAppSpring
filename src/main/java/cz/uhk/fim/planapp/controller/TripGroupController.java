package cz.uhk.fim.planapp.controller;

import cz.uhk.fim.planapp.domain.Trip;
import cz.uhk.fim.planapp.domain.TripGroup;
import cz.uhk.fim.planapp.service.MapValidationErrorService;
import cz.uhk.fim.planapp.service.TripGroupService;
import cz.uhk.fim.planapp.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/trip-group")
@CrossOrigin
public class TripGroupController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private TripGroupService tripGroupService;

    @Autowired
    private TripService tripService;


    @RequestMapping(value = "/create-trip-group", method = RequestMethod.POST)
    public ResponseEntity<?> createNewTrip(@Valid @RequestBody TripGroup tripGroup, BindingResult result, Principal principal){ //Principal je ze security package - person who is currently logged in

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null){
            return errorMap;
        }

        TripGroup tripGroup1 = tripGroupService.saveOrUpdateTripGroup(tripGroup, principal.getName());
        return new ResponseEntity<TripGroup>(tripGroup, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/add-trip-to-group/{tripId}/{tripGroupId}", method = RequestMethod.POST)
    public void addTripToGroup(@PathVariable String tripId, @PathVariable String tripGroupId, Principal principal){ //Principal je ze security package - person who is currently logged in

        tripGroupService.setTripGroupToTrip(tripGroupId, principal.getName(), tripId);
    }
}
