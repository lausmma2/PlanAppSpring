package cz.uhk.fim.planapp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer userId;

    @Column(length = 10, nullable = false)
    private String visibleId;

    @NotBlank(message = "Username is required")
    @Column(length = 50, nullable = false)
    private String username;

    @NotBlank(message = "Firstname is required")
    @Column(length = 50, nullable = false)
    private String firstname;

    @NotBlank(message = "Lastname is required")
    @Column(length = 50, nullable = false)
    private String lastname;

    @NotBlank(message = "Password is required")
    @Column(length = 50, nullable = false)
    private String password;

    @NotBlank(message = "E-mail is required")
    @Column(length = 50, unique = true, nullable = false)
    private String email;

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

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @ManyToOne
    @JoinColumn(name = "PERMISSION", foreignKey = @ForeignKey(name = "FK_USER_PERMISSION"))
    private Permission permission;

    @ManyToMany
    @JoinColumn(name = "GROUP", foreignKey = @ForeignKey(name = "FK_USER_GROUP"))
    private Set<TripGroup> tripGroup;

    @OneToOne
    @JoinColumn(name = "GROUP_MEMBER", foreignKey = @ForeignKey(name = "FK_USER_GROUPMEMBER"))
    private GroupMember groupMember;

    @Column(length = 6)
    private Integer points;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

   public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
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
