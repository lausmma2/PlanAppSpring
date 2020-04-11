package cz.uhk.fim.planapp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "PLACE")
public class Place {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer placeId;

    @NotBlank(message = "Place title is required")
    private String title;

    private Double latitude;

    private Double longitude;

    @NotBlank(message = "Distance is required")
    private String distance;

    @ManyToOne(fetch = FetchType.LAZY)
    private Trip trip;

    private String placeOwner;

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPlaceOwner() {
        return placeOwner;
    }

    public void setPlaceOwner(String placeOwner) {
        this.placeOwner = placeOwner;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
