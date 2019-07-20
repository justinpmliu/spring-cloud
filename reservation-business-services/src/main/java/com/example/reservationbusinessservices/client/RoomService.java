package com.example.reservationbusinessservices.client;

import com.example.reservationbusinessservices.domain.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "ROOM-SERVICES")
public interface RoomService {

    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    List<Room> findAll(@RequestParam(name="roomNumber", required = false)String roomNumber);
}
