package com.example.reservationbusinessservices.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public abstract class RoomMixin {
    @JsonProperty("room_id")
    private String id;
    private String name;
    private String roomNumber;
    private String bedInfo;
}
