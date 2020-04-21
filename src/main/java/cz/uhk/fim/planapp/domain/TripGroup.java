package cz.uhk.fim.planapp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
public class TripGroup {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer tripGroupId;

    @NotBlank(message = "Trip Group name is required")
    private String name;

    @NotBlank(message = "Trip Group Identifier is required")
    @Size(min = 4, max = 5, message = "Please use 4 to 5 characters")
    @Column(updatable = false, unique = true)
    private String tripGroupIdentifier;

    private String tripGroupCreator;

    @Column(length = 200)
    private String description;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(updatable = false)
    private Date created_at;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_at;

    @ManyToMany(mappedBy = "tripGroups", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "tripGroup", orphanRemoval = true)
    private List<Trip> trips = new ArrayList<>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

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

    public String getTripGroupIdentifier() {
        return tripGroupIdentifier;
    }

    public void setTripGroupIdentifier(String tripGroupIdentifier) {
        this.tripGroupIdentifier = tripGroupIdentifier;
    }

    public String getTripGroupCreator() {
        return tripGroupCreator;
    }

    public void setTripGroupCreator(String tripGroupCreator) {
        this.tripGroupCreator = tripGroupCreator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
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
}