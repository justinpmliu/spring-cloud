package com.example.roomservices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * Created by frankmoley on 5/22/17.
 */
@RestController
@RequestMapping(value="/rooms")
@Api(value="rooms", tags=("room"))
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get all rooms", notes = "Get all rooms in the system", nickname = "getRooms")
    public ResponseEntity<List<Room>> findAll(@RequestParam(name="roomNumber", required = false)String roomNumber){
        List<Room> rooms;
        if(StringUtils.isNotEmpty(roomNumber)){
            Room room = this.roomRepository.findByRoomNumber(roomNumber);
            rooms = (room == null) ? Collections.emptyList() : Collections.singletonList(room);
        } else {
            rooms = (List<Room>) this.roomRepository.findAll();
        }
        return ResponseEntity.ok(rooms);
    }
}
