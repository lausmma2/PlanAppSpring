package cz.uhk.fim.planapp.domain;

import javax.persistence.*;

@Entity
@Table(name = "TRIP_TYPE")
public class TripType {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer tripTypeId;

    @Column(length = 50, nullable = false)
    private String name;

    public Integer getTripTypeId() {
        return tripTypeId;
    }

    public void setTripTypeId(Integer tripTypeId) {
        this.tripTypeId = tripTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
