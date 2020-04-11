package cz.uhk.fim.planapp.controller;

import cz.uhk.fim.planapp.domain.Place;
import cz.uhk.fim.planapp.service.MapValidationErrorService;
import cz.uhk.fim.planapp.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/place")
@CrossOrigin
public class PlaceController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private PlaceService placeService;

    @RequestMapping(value = "/{title}/{latitude}/{longitude}/{distance}/{tripIdentifier}", method = RequestMethod.POST)
    public ResponseEntity<?> savePlaceToDB(@Valid @PathVariable String title,
                                           @PathVariable Double latitude,
                                           @PathVariable Double longitude,
                                           @PathVariable String distance,
                                           @PathVariable String tripIdentifier,
                                           Principal principal){
        placeService.savePlace(title, latitude, longitude, distance, tripIdentifier, principal.getName());
        return new ResponseEntity<String>("Place saved", HttpStatus.CREATED);
    }

    @GetMapping("/all/{tripIdentifier}")
    public Iterable<Place> getAllPlaces(@PathVariable String tripIdentifier, Principal principal){
        return placeService.findAllPlaces(tripIdentifier, principal.getName());
    }

    @DeleteMapping("/delete/{latitude}/{longitude}/{tripId}")
    public ResponseEntity<?> deletePlace(@PathVariable Double latitude,
                                         @PathVariable Double longitude,
                                         @PathVariable String tripId,
                                         Principal principal){

        placeService.deletePlaceByTitleAndUsernameAndTrip(latitude, longitude, principal.getName(), tripId);

        return new ResponseEntity<String>("Place with latitude: '" + latitude + "' was deleted", HttpStatus.OK);
    }

    @GetMapping("/all/{tripIdentifier}/{tripGroupIdentifier}")
    public Iterable<Place> getAllPlacesByTripIdentifierAndTripGroupIdentifier(@PathVariable String tripIdentifier,
                                                                              @PathVariable String tripGroupIdentifier,
                                                                              Principal principal){
        return placeService.findAllPlacesByTripIdentifierAndTripGroupIdentifier(tripIdentifier, tripGroupIdentifier, principal.getName());
    }
}
