package cz.uhk.fim.planapp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "TRIP")
public class Trip {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer tripId;

    @NotBlank(message = "Trip name is required")
    private String name;

    @NotBlank(message = "Trip Identifier is required")
    @Size(min = 4, max = 5, message = "Please use 4 to 5 characters")
    @Column(updatable = false, unique = true)
    private String tripIdentifier;

    private String tripCreator;

    private String description;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date start_date;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date end_date;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(updatable = false)
    private Date created_at;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_at;

    @Column(length = 6)
    private Integer kmCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private TripGroup tripGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    @OneToMany
    @JoinColumn(name = "TRIP_PART", foreignKey = @ForeignKey(name = "FK_TRIP_TRIPPART"))
    private Set<TripPart> tripPart;

    public Trip() {
    }

    @PrePersist
    protected void onCreate(){
        this.created_at = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updated_at = new Date();
    }

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

    public String getTripIdentifier() {
        return tripIdentifier;
    }

    public void setTripIdentifier(String tripIdentifier) {
        this.tripIdentifier = tripIdentifier;
    }

    public String getTripCreator() {
        return tripCreator;
    }

    public void setTripCreator(String tripCreator) {
        this.tripCreator = tripCreator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Integer getKmCount() {
        return kmCount;
    }

    public void setKmCount(Integer kmCount) {
        this.kmCount = kmCount;
    }

    public TripGroup getTripGroup() {
        return tripGroup;
    }

    public void setTripGroup(TripGroup tripGroup) {
        this.tripGroup = tripGroup;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<TripPart> getTripPart() {
        return tripPart;
    }

    public void setTripPart(Set<TripPart> tripPart) {
        this.tripPart = tripPart;
    }
}