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

@RestController
@RequestMapping("/api/trip")
@CrossOrigin
public class TripController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private TripService tripService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createNewTrip(@Valid @RequestBody Trip trip, BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null){
            return errorMap;
        }

        Trip trip1 = tripService.saveOrUpdateTrip(trip);
        return new ResponseEntity<Trip>(trip, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Iterable<Trip> getAllProjects(){return tripService.findAllProjects();}
}
