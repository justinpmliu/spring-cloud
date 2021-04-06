package com.example.roomservices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by frankmoley on 5/22/17.
 */
@RestController
@Api(value="rooms", tags=("room"))
@Slf4j
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/rooms")
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

    @GetMapping("/sleep1")
    public String sleep(Integer timeout){
        try{
            log.info("begin sleep:{}",timeout);
            TimeUnit.SECONDS.sleep(timeout);
            log.info("end sleep:{}",timeout);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "sleep:" + timeout;
    }

    @GetMapping("/sleep2")
    public String sleep(){
        new Thread(new MyTask()).start();
        return "start sleep 30s";
    }

}
