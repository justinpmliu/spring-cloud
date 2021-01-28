package com.example.reservationbusinessservices.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class RoomMixin {
    @JsonProperty("room_id")
    private String id;
    private String name;
    private String roomNumber;
    private String bedInfo;
}
