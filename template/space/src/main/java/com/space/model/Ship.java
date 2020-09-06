package com.space.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ship")
public class Ship {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String planet;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private ShipType shipType;

    @Column
    private Date prodDate;

    @Column
    private Boolean isUsed;

    @Column
    private Double speed;

    @Column
    private Integer crewSize;

    @Column
    private Double rating;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        if(name != null)
            return name;
        else return "null";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanet() {
        if(planet != null)
            return planet;
        else return "null";
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public Date getProdDate() {
        return prodDate;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    public Boolean getUsed() {
        if(isUsed == null) {
            return isUsed = false;
        }
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public Double getSpeed() {
        if (speed != null)
            return Math.round(speed * 100.0) / 100.0;
        else return 0.00;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getCrewSize() {
        if(crewSize != null)
            return crewSize;
        else return 0;
    }

    public void setCrewSize(Integer crewSize) {
        this.crewSize = crewSize;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

}


//    В проекте должна использоваться сущность Ship, которая имеет поля:
//        Long id ID корабля
//        String name Название корабля (до 50 знаков включительно)
//        String planet Планета пребывания (до 50 знаков включительно)
//        ShipType shipType Тип корабля
//        Date prodDate Дата выпуска.
//        Диапазон значений года 2800..3019 включительно
//        Boolean isUsed Использованный / новый
//        Double speed Максимальная скорость корабля. Диапазон значений
//        0,01..0,99 включительно. Используй математическое
//        округление до сотых.
//        Integer crewSize Количество членов экипажа. Диапазон значений
//        1..9999 включительно.
//        Double rating Рейтинг корабля. Используй математическое
//        округление до сотых.