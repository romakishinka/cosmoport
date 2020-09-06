package com.space.service;

import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipService{

    private ShipRepository shipRepository;

    @Autowired
    public ShipService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    public List<Ship> getAll() {
        return shipRepository.findAll();
    }

    public Ship saveShip(Ship ship) {
        return shipRepository.save(ship);
    }

    public void deleteShip(Long id) {
        shipRepository.deleteById(id);
    }

    public Ship getShipById (Long id) {
        return shipRepository.findById(id).get();
    }

    public List<Ship> pagingShipsWithoutParameters(List<Ship> shipList, Integer pageNumber, Integer pageSize) {

        List<Ship> resultList = new ArrayList<>();

        if(pageNumber == 0) {
            for (int i = 0; i < pageSize; i++)
                resultList.add(shipList.get(i));
        }
        else if (pageNumber == 1) {
            for(int i = pageSize; i < shipList.size(); i++)
                resultList.add(shipList.get(i));
        }

        resultList = resultList.stream().distinct().collect(Collectors.toList());
        return resultList;
    }


    public List<Ship> pagingShipsWithParameters(List<Ship> shipList, Integer pageNumber, Integer pageSize) {
        List<Ship> resultList = new ArrayList<>();

        if (pageNumber == 0) {
            if (pageSize > shipList.size()) {
                for (int i = 0; i < shipList.size(); i++) {
                    if (shipList.get(i) != null)
                        resultList.add(shipList.get(i));
                }
            } else {
                for (int i = 0; i < pageSize; i++) {
                    if (shipList.get(i) != null)
                        resultList.add(shipList.get(i));
                }
            }

        } else if (pageNumber == 1) {
            for (int i = pageSize; i < shipList.size(); i++)
                resultList.add(shipList.get(i));
        }

        resultList = resultList.stream().distinct().collect(Collectors.toList());

        return resultList;
    }

    public List<Ship> orderList (ShipOrder order, List<Ship> shipList) {
        if (order.equals(ShipOrder.ID))
            shipList.sort((s1, s2) ->  s1.getId().compareTo(s2.getId()));
        else if (order.equals(ShipOrder.SPEED))
            shipList.sort((s1, s2) ->  s1.getRating().compareTo(s2.getRating()));
        else if (order.equals(ShipOrder.DATE))
            shipList.sort((s1, s2) ->  s1.getProdDate().compareTo(s2.getProdDate()));
        else if (order.equals(ShipOrder.RATING))
            shipList.sort((s1, s2) ->  s1.getRating().compareTo(s2.getRating()));
        return shipList;
    }
}
