package cz.uhk.fim.planapp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private String visibleId;

    @NotBlank(message = "Firstname field is required")
    private String firstname;

    @NotBlank(message = "Lastname field is required")
    private String lastname;

    @NotBlank(message = "Password field is required")
    private String password;

    @Transient
    private String confirmPassword;

    @Email(message = "Username has to be an email")
    @NotBlank(message = "Username is required")
    @Column(unique = true)
    private String username;

    @Column(length = 20)
    private String phone;

    @Column(length = 20)
    private String country;

    @Column(length = 200)
    private String aboutMe;

    @Column(nullable = false)
    private Boolean isConfirmed = false;

    @Lob
    private byte[] photo;

    @Column(length = 50)
    private String authKey;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime created_On;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime update_On;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    private List<Trip> trips = new ArrayList<>();

    @ManyToMany
    @JoinColumn(name = "GROUP", foreignKey = @ForeignKey(name = "FK_USER_GROUP"))
    private Set<TripGroup> tripGroup;

    @OneToOne
    @JoinColumn(name = "GROUP_MEMBER", foreignKey = @ForeignKey(name = "FK_USER_GROUPMEMBER"))
    private GroupMember groupMember;

    @Column(length = 6)
    private Integer points;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        isConfirmed = confirmed;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public LocalDateTime getCreated_On() {
        return created_On;
    }

    public void setCreated_On(LocalDateTime created_On) {
        this.created_On = created_On;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public LocalDateTime getUpdate_On() {
        return update_On;
    }

    public void setUpdate_On(LocalDateTime update_On) {
        this.update_On = update_On;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public Set<TripGroup> getTripGroup() {
        return tripGroup;
    }

    public void setTripGroup(Set<TripGroup> tripGroup) {
        this.tripGroup = tripGroup;
    }

    public void setGroup(Set<TripGroup> tripGroup) {
        this.tripGroup = tripGroup;
    }

    public GroupMember getGroupMember() {
        return groupMember;
    }

    public void setGroupMember(GroupMember groupMember) {
        this.groupMember = groupMember;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getVisibleId() {
        return visibleId;
    }

    public void setVisibleId(String visibleId) {
        this.visibleId = visibleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }
}
