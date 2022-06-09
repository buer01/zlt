package com.zlt.Database_leakage_RSA.entity;

//public class User {
//
//    private int userId;
//    private String name;
//    private String password;
//    private String address;
//    private String ico;
//
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "userId=" + userId +
//                ", name='" + name + '\'' +
//                ", password='" + password + '\'' +
//                ", address='" + address + '\'' +
//                ", ico='" + ico + '\'' +
//                '}';
//    }
//
//    //快捷方式生成set和get方法 alt+insert
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getIco() {
//        return ico;
//    }
//
//    public void setIco(String ico) {
//        this.ico = ico;
//    }
//}

public class User {



    private int id;
    private String name;
    private String password;
    private String address;
    private String icon;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}









