package cz.uhk.fim.planapp.domain;

import javax.persistence.*;

@Entity
@Table(name = "PLACE")
public class Place {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer placeId;

    @Column
    private String title;

    @Column
    private String latitude;

    @Column
    private String longitude;

    @Column
    private String vicinity;

    @Column
    private String distance;

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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
