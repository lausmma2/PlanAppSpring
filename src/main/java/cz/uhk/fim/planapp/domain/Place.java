package cz.uhk.fim.planapp.domain;

import javax.persistence.*;

@Entity
@Table(name = "PLACE")
public class Place {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer placeId;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 200)
    private String info;

    @Column(length = 50, nullable = false)
    private String coordX;

    @Column(length = 50, nullable = false)
    private String coordY;

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCoordX() {
        return coordX;
    }

    public void setCoordX(String coordX) {
        this.coordX = coordX;
    }

    public String getCoordY() {
        return coordY;
    }

    public void setCoordY(String coordY) {
        this.coordY = coordY;
    }
}
