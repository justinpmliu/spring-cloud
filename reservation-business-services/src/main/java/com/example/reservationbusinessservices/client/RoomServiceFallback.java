package com.example.reservationbusinessservices.client;

import com.example.reservationbusinessservices.domain.Room;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class RoomServiceFallback implements FallbackFactory<RoomService> {
    @Override
    public RoomService create(Throwable e) {
        return new RoomService() {
            @Override
            public List<Room> findAll(String roomNumber) {
                log.error(e.getMessage(), e);
                return Collections.emptyList();
            }
        };
    }
}
