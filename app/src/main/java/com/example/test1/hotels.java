package com.example.test1;
enum HotelCategory {
    oneStar , towStars , threeStars , fourStars , fiveStars
}
public class hotels {
    private int rooms;
    private boolean pool;
    private String Name;
    private boolean wifi;
    private boolean parking;
    private boolean roomServices;
    private boolean restaurant;
    private boolean hotTub;
    private boolean gym;
    private boolean massage;
    private boolean pingPong;
    private boolean spa;
    private String country;
    private int floors;
    private HotelCategory hotelCategory;
    private String photo;
    private String phone;
    private String description;
    private String address;

    public String getPhoto() {
        return photo;
    }

    public hotels(String name, String description, String address,
                      HotelCategory category, String photo, String phone) {
        this.Name = name;
        this.description = description;
        this.address = address;
        this.hotelCategory = category;
        this.photo = photo;
        this.phone = phone;
    }

    public hotels(int rooms, boolean pool, String Name, boolean wifi, boolean parking,
                  boolean roomServices, boolean restaurant, boolean hotTub,
                  boolean gym, boolean massage, boolean pingPong, boolean spa)
    {
        this.rooms = rooms;
        this.pool = pool;
        this.Name = Name;
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

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        Name = Name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HotelCategory getCategory() {
        return hotelCategory;
    }

    public void setCategory(HotelCategory category) {
        this.hotelCategory = category;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    @Override
    public String toString() {
        return "hotels{" +
                "rooms=" + rooms +
                ", pool=" + pool +
                ", Name='" + Name + '\'' +
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
