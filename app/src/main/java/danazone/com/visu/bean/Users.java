package danazone.com.visu.bean;

import java.io.Serializable;

public class Users implements Serializable{
    private int id;
    private String name;
    private int type;
    private String gender;
    private String birthday;
    private String school;
    private String homeTown;
    private int point;
    private int experience;
    private String status;
    private int idTransaction;

    public Users(){

    }

    public Users(int id, String name, int type, String gender, String birthday, String school, String homeTown, int point, int experience, String status, int idTransaction) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.gender = gender;
        this.birthday = birthday;
        this.school = school;
        this.homeTown = homeTown;
        this.point = point;
        this.experience = experience;
        this.status = status;
        this.idTransaction = idTransaction;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }
}
