package com.example.test1;

public class hotels {
    private int rooms;
    private boolean pool;
    private String RoomType;
    private boolean wifi;
    private boolean parking;
    private boolean roomServices;
    private boolean restaurant;
    private boolean hotTub;
    private boolean gym;
    private boolean massage;
    private boolean pingPong;
    private boolean spa;

    public hotels() {
    }




    public hotels(int rooms, boolean pool, String roomType, boolean wifi, boolean parking, boolean roomServices, boolean restaurant, boolean hotTub, boolean gym, boolean massage, boolean pingPong, boolean spa) {
        this.rooms = rooms;
        this.pool = pool;
        RoomType = roomType;
        this.wifi = wifi;
        this.parking = parking;
        this.roomServices = roomServices;
        this.restaurant = restaurant;
        this.hotTub = hotTub;
        this.gym = gym;
        this.massage = massage;
        this.pingPong = pingPong;
        this.spa = spa;
    }



    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public String getRoomType() {
        return RoomType;
    }

    public void setRoomType(String roomType) {
        RoomType = roomType;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public boolean isRoomServices() {
        return roomServices;
    }

    public void setRoomServices(boolean roomServices) {
        this.roomServices = roomServices;
    }

    public boolean isRestaurant() {
        return restaurant;
    }

    public void setRestaurant(boolean restaurant) {
        this.restaurant = restaurant;
    }

    public boolean isHotTub() {
        return hotTub;
    }

    public void setHotTub(boolean hotTub) {
        this.hotTub = hotTub;
    }

    public boolean isGym() {
        return gym;
    }

    public void setGym(boolean gym) {
        this.gym = gym;
    }

    public boolean isMassage() {
        return massage;
    }

    public void setMassage(boolean massage) {
        this.massage = massage;
    }

    public boolean isPingPong() {
        return pingPong;
    }

    public void setPingPong(boolean pingPong) {
        this.pingPong = pingPong;
    }

    public boolean isSpa() {
        return spa;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }



    @Override
    public String toString() {
        return "hotels{" +
                "rooms=" + rooms +
                ", pool=" + pool +
                ", RoomType='" + RoomType + '\'' +
                ", wifi=" + wifi +
                ", parking=" + parking +
                ", roomServices=" + roomServices +
                ", restaurant=" + restaurant +
                ", hotTub=" + hotTub +
                ", gym=" + gym +
                ", massage=" + massage +
                ", pingPong=" + pingPong +
                ", spa=" + spa +
                '}';
    }



}
