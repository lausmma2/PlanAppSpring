package cz.uhk.fim.planapp.domain;

import javax.persistence.*;

@Entity
@Table(name = "TRIP_GROUP_MEMBER")
public class GroupMember {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer adminId;

    @ManyToOne
    @JoinColumn(name = "TRIPGROUP", foreignKey = @ForeignKey(name = "FK_GROUPMEMBER_TRIPGROUP"), nullable = false)
    private TripGroup tripGroup;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public TripGroup getTripGroup() {
        return tripGroup;
    }

    public void setTripGroup(TripGroup tripGroup) {
        this.tripGroup = tripGroup;
    }
}