package com.web.app.worldgames.domain;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private long roomId;
    private String roomName;
    private List<User> roomMembers = new ArrayList<>();

    public long getRoomId() {
	return roomId;
    }

    public void setRoomId(long roomId) {
	this.roomId = roomId;
    }

    public String getRoomName() {
	return roomName;
    }

    public void setRoomName(String roomName) {
	this.roomName = roomName;
    }

    public List<User> getRoomMembers() {
	return roomMembers;
    }

    public void addRoomMembers(User user) {
	this.roomMembers.add(user);
    }

}
