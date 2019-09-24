package cz.uhk.fim.planapp.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "TRIP_GROUP")
public class TripGroup {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer tripGroupId;

    @Column(length = 50, nullable = false)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Column(length = 200)
    private String description;

    @OneToMany
    @JoinColumn(name = "TRIP", foreignKey = @ForeignKey(name = "FK_GROUP_TRIP"))
    private Set<Trip> trip;

    public Integer getTripGroupId() {
        return tripGroupId;
    }

    public void setTripGroupId(Integer tripGroupId) {
        this.tripGroupId = tripGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Trip> getTrip() {
        return trip;
    }

    public void setTrip(Set<Trip> trip) {
        this.trip = trip;
    }
}