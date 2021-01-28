package com.example.reservationbusinessservices.controller;

import com.example.reservationbusinessservices.client.RoomService;
import com.example.reservationbusinessservices.domain.Room;
import com.example.reservationbusinessservices.domain.RoomMixin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class RoomReservationController {
    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public List<Room> getAllRooms(@RequestParam(name="roomNumber", required = false)String roomNumber) {
        List<Room> rooms = roomService.findAll(roomNumber);

        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(Room.class, RoomMixin.class);

        try {
            String json = mapper.writeValueAsString(rooms);
            log.info(json);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }

        return rooms;
    }
}
