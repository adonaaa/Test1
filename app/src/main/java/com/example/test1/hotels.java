package com.example.test1;
enum HotelCategory {
    oneStar , towStars , threeStars , fourStars , fiveStars
}
public class hotels {
    private int rooms;
    private boolean pool;
    private String name;
    private boolean wifi;
    private boolean parking;
    private boolean roomServices;
    private boolean restaurant;
    private boolean gym;
    private boolean spa;
    private String country;
    private int floors;
    private HotelCategory hotelCategory;
    private String photo;
    private String phone;
    private String description;
    private String address;

    public hotels() {
    }

    public String getPhoto() {
        return photo;
    }

    public hotels(String name, String description, String address,
                      HotelCategory category, String photo, String phone, boolean pool) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.hotelCategory = category;
        this.photo = photo;
        this.phone = phone;
        this.pool = pool;
    }


    public hotels(int rooms, boolean pool, String name, boolean wifi, boolean parking, boolean roomServices, boolean restaurant, boolean hotTub, boolean gym, boolean massage, boolean pingPong, boolean spa, String country, int floors, HotelCategory hotelCategory, String photo, String phone, String description, String address) {
        this.rooms = rooms;
        this.pool = pool;
        this.name = name;
        this.wifi = wifi;
        this.parking = parking;
        this.roomServices = roomServices;
        this.restaurant = restaurant;
        this.gym = gym;
        this.spa = spa;
        this.country = country;
        this.floors = floors;
        this.hotelCategory = hotelCategory;
        this.photo = photo;
        this.phone = phone;
        this.description = description;
        this.address = address;
    }


    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public HotelCategory getCategory() {
        return hotelCategory;
    }

    public String getPhone() {
        return phone;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "hotels{" +
                "rooms=" + rooms +
                ", pool=" + pool +
                ", Name='" + name +  '\'' +
                ", wifi=" + wifi +
                ", parking=" + parking +
                ", roomServices=" + roomServices +
                ", restaurant=" + restaurant +
                ", gym=" + gym +
                ", spa=" + spa +
                '}';
    }



}
