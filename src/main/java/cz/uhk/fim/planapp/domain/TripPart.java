package cz.uhk.fim.planapp.domain;

import javax.persistence.*;

@Entity
@Table(name = "TRIP_PART")
public class TripPart {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer tripPartId;

    /*// TODO: 07.09.2019
    private Place place;*/

    public Integer getTripPartId() {
        return tripPartId;
    }

    public void setTripPartId(Integer tripPartId) {
        this.tripPartId = tripPartId;
    }

    /*public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }*/
}
