package cz.uhk.fim.planapp.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "TRIP")
public class Trip {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer tripId;

    @Column(length = 50, nullable = false)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFrom;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTo;

    @Column(length = 6)
    private Integer kmCount;

    @OneToMany
    @JoinColumn(name = "TRIP_PART", foreignKey = @ForeignKey(name = "FK_TRIP_TRIPPART"))
    private Set<TripPart> tripPart;

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
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

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Integer getKmCount() {
        return kmCount;
    }

    public void setKmCount(Integer kmCount) {
        this.kmCount = kmCount;
    }

    public Set<TripPart> getTripPart() {
        return tripPart;
    }

    public void setTripPart(Set<TripPart> tripPart) {
        this.tripPart = tripPart;
    }
}