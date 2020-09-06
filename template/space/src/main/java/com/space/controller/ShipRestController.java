package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ShipRestController {

    private ShipService shipService;
    @Autowired
    public ShipRestController(ShipService shipService) {
        this.shipService = shipService;
    }

    private ShipOrder shipOrder;


    @RequestMapping(value = "/rest/ships", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Ship>> getShipsList(@RequestParam(value = "name", required = false) String name,
                                                   @RequestParam(value = "planet", required = false) String planet,
                                                   @RequestParam(value = "shipType", required = false) ShipType shipType,
                                                   @RequestParam(value = "after", required = false) Long after,
                                                   @RequestParam(value = "before", required = false) Long before,
                                                   @RequestParam(value = "isUsed", required = false) Boolean isUsed,
                                                   @RequestParam(value = "maxSpeed", required = false) Double maxSpeed,
                                                   @RequestParam(value = "minSpeed", required = false) Double minSpeed,
                                                   @RequestParam(value = "minCrewSize", required = false) Integer minCrewSize,
                                                   @RequestParam(value = "maxCrewSize", required = false) Integer maxCrewSize,
                                                   @RequestParam(value = "minRating", required = false) Double minRating,
                                                   @RequestParam(value = "maxRating", required = false) Double maxRating,
                                                   @RequestParam(value = "order", required = false) ShipOrder order,
                                                   @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false, defaultValue = "3") Integer pageSize
                                                   ) {



        List <Ship> shipList = shipService.getAll();
        List <Ship> resultList;  //!


        if (name == null && planet == null && shipType == null && isUsed == null && after == null && before == null) {

            resultList = shipService.pagingShipsWithoutParameters(shipList, pageNumber, pageSize);

            return new ResponseEntity<>(resultList, HttpStatus.OK);
        }

        else {

            if (name != null)
                shipList = shipList.stream().filter(ship -> ship.getName().contains(name)).collect(Collectors.toList());

            if (planet != null)
                shipList = shipList.stream().filter(ship -> ship.getPlanet().contains(planet)).collect(Collectors.toList());

            if (shipType != null)
                shipList = shipList.stream().filter(ship -> ship.getShipType().equals(shipType)).collect(Collectors.toList());

            if (isUsed != null)
                shipList = shipList.stream().filter(ship -> ship.getUsed() == isUsed).collect(Collectors.toList());

            if (after != null)
                shipList = shipList.stream().filter(ship -> ship.getProdDate().after(new Date(after))).collect(Collectors.toList());

            if (before != null)
                shipList = shipList.stream().filter(ship -> ship.getProdDate().before(new Date(before))).collect(Collectors.toList());

            if (minSpeed != null)
                shipList = shipList.stream().filter(ship -> ship.getSpeed() >= minSpeed).collect(Collectors.toList());

            if (maxSpeed != null)
                shipList = shipList.stream().filter(ship -> ship.getSpeed() <= maxSpeed).collect(Collectors.toList());

            if (minCrewSize != null)
                shipList = shipList.stream().filter(ship -> ship.getCrewSize() >= minCrewSize).collect(Collectors.toList());

            if (maxCrewSize != null)
                shipList = shipList.stream().filter(ship -> ship.getCrewSize() <= maxCrewSize).collect(Collectors.toList());

            if (minRating != null)
                shipList = shipList.stream().filter(ship -> ship.getRating() >= minRating).collect(Collectors.toList());

            if (maxRating != null)
                shipList = shipList.stream().filter(ship -> ship.getRating() <= maxRating).collect(Collectors.toList());

            if(order != null) {
                shipList = shipService.orderList(order, shipList);
            }

            resultList = shipService.pagingShipsWithParameters(shipList, pageNumber, pageSize);

            resultList = resultList.stream().distinct().collect(Collectors.toList());
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        }
    }



    @RequestMapping(value = "/rest/ships/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Integer> getShipsCount(@RequestParam(value = "name", required = false) String name,
                                                 @RequestParam(value = "planet", required = false) String planet,
                                                 @RequestParam(value = "shipType", required = false) ShipType shipType,
                                                 @RequestParam(value = "after", required = false) Long after,
                                                 @RequestParam(value = "before", required = false) Long before,
                                                 @RequestParam(value = "isUsed", required = false) Boolean isUsed,
                                                 @RequestParam(value = "maxSpeed", required = false) Double maxSpeed,
                                                 @RequestParam(value = "minSpeed", required = false) Double minSpeed,
                                                 @RequestParam(value = "minCrewSize", required = false) Integer minCrewSize,
                                                 @RequestParam(value = "maxCrewSize", required = false) Integer maxCrewSize,
                                                 @RequestParam(value = "minRating", required = false) Double minRating,
                                                 @RequestParam(value = "maxRating", required = false) Double maxRating) {
        int count;
        List <Ship> shipList = shipService.getAll();

        if (name != null)
            shipList = shipList.stream().filter(ship -> ship.getName().contains(name)).collect(Collectors.toList());

        if (planet != null)
            shipList = shipList.stream().filter(ship -> ship.getPlanet().contains(planet)).collect(Collectors.toList());

        if (shipType != null)
            shipList = shipList.stream().filter(ship -> ship.getShipType().equals(shipType)).collect(Collectors.toList());

        if (isUsed != null)
            shipList = shipList.stream().filter(ship -> ship.getUsed() == isUsed).collect(Collectors.toList());

        if (after != null)
            shipList = shipList.stream().filter(ship -> ship.getProdDate().after(new Date(after))).collect(Collectors.toList());

        if (before != null)
            shipList = shipList.stream().filter(ship -> ship.getProdDate().before(new Date(before))).collect(Collectors.toList());

        if (minSpeed != null)
            shipList = shipList.stream().filter(ship -> ship.getSpeed() >= minSpeed).collect(Collectors.toList());

        if (maxSpeed != null)
            shipList = shipList.stream().filter(ship -> ship.getSpeed() <= maxSpeed).collect(Collectors.toList());

        if (minCrewSize != null)
            shipList = shipList.stream().filter(ship -> ship.getCrewSize() >= minCrewSize).collect(Collectors.toList());

        if (maxCrewSize != null)
            shipList = shipList.stream().filter(ship -> ship.getCrewSize() <= maxCrewSize).collect(Collectors.toList());

        if (minRating != null)
            shipList = shipList.stream().filter(ship -> ship.getRating() >= minRating).collect(Collectors.toList());

        if (maxRating != null)
            shipList = shipList.stream().filter(ship -> ship.getRating() <= maxRating).collect(Collectors.toList());

        count = shipList.size();

        if(count == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @RequestMapping(value = "rest/ships/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public ResponseEntity<Ship> getShipById (@PathVariable("id") Long id) {
        if(id == 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List <Ship> list = shipService.getAll();
        List<Long> idList = new ArrayList<>();
        for (Ship ship:list) {
            idList.add(ship.getId());
        }
        if(!idList.contains(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Ship ship = shipService.getShipById(id);

        if(ship.getId() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/ships", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Ship> createShip (@RequestBody Ship ship) {
        if (ship.getName().equals("null") || ship.getPlanet() == null || ship.getShipType() == null ||
                ship.getProdDate() == null || ship.getSpeed() == 0.00 || ship.getCrewSize() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String name = ship.getName();
        String planet = ship.getPlanet();
        ShipType shipType = ship.getShipType();
        Date prodDate = ship.getProdDate();
        Integer crewSize = ship.getCrewSize();
        Boolean isUsed = ship.getUsed();
        Double speed = ship.getSpeed();
        Double rating = ship.getRating();

        double coefficient = isUsed ? 0.5 : 1;

        if (name.length() > 50 || name.equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (planet.length() > 50) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (name.isEmpty() && planet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (crewSize < 1 || crewSize > 9999) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (speed < 0.01 || speed > 0.99) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (prodDate.before(new Date(26174880000000L)) || prodDate.after(new Date(33081264000000L))) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(prodDate);
        rating = (80 * speed * coefficient) / (3019 - calendar.get(Calendar.YEAR) + 1);

        ship.setRating(rating);


        shipService.saveShip(ship);
        return new ResponseEntity<>(ship, HttpStatus.OK);

    }

















    @RequestMapping(value = "/rest/ships/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Ship> updateShip(@RequestBody Ship ship,
                                           @PathVariable("id") Long id) {

        if(id == 0 || id < 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        List <Ship> list = shipService.getAll();             //добавить метод в утилитный класс findShipById
        List<Long> idList = new ArrayList<>();
        for (Ship s : list) {
            idList.add(s.getId());
        }
        if(!idList.contains(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Ship entity = shipService.getShipById(id);

        if (!ship.getName().equals("null")) {

            if (ship.getName().length() > 50 || ship.getName().equals("")) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                entity.setName(ship.getName());
            }
        }

        if(!ship.getPlanet().equals("null")){
            if (ship.getPlanet().length() > 50) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            else
                entity.setPlanet(ship.getPlanet());
        }

        System.out.println("type = " + ship.getShipType());
        if(ship.getShipType() != null) {
            entity.setShipType(ship.getShipType());
        }

        if(ship.getProdDate() != null) {
            if (ship.getProdDate().before(new Date(26174880000000L)) || ship.getProdDate().after(new Date(33081264000000L))) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            else
                entity.setProdDate(ship.getProdDate());
        }

        if (ship.getSpeed() != 0.00) {
            if (ship.getSpeed() < 0.01 || ship.getSpeed() > 0.99) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            else
                entity.setSpeed(ship.getSpeed());
        }

        if(ship.getCrewSize() != 0) {
            if (ship.getCrewSize() < 1 || ship.getCrewSize() > 9999) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            else
                entity.setCrewSize(ship.getCrewSize());
        }
        double coefficient = entity.getUsed() ? 0.5 : 1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(entity.getProdDate());
        double rating = (80 * entity.getSpeed() * coefficient) / (3019 - calendar.get(Calendar.YEAR) + 1);
        entity.setRating(Math.round(rating * 100.0) / 100.0);

        entity.setId(id);
        shipService.saveShip(entity);

        return new ResponseEntity<>(entity, HttpStatus.OK);
    }





















    @RequestMapping(value = "/rest/ships/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Ship> deleteShip(@PathVariable("id") Long id) {
        if(id <= 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List <Ship> list = shipService.getAll();
        List<Long> idList = new ArrayList<>();
        for (Ship ship:list) {
            idList.add(ship.getId());
        }
        if(!idList.contains(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        shipService.deleteShip(shipService.getShipById(id).getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
