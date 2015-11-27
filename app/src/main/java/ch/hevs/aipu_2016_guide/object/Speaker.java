package ch.hevs.aipu_2016_guide.object;

import java.util.Date;

/**
 * Created by Arnaud on 27.11.2015.
 */
public class Speaker {

    private int IdSpeaker;
    private String Name;
    private String Firstname;
    private String Picture;
    private String Email;
    private String Function;
    private String Website;
    private String Informations;
    private Date Timestamp;


    public int getIdSpeaker() {
        return IdSpeaker;
    }

    public void setIdSpeaker(int idSpeaker) {
        IdSpeaker = idSpeaker;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFunction() {
        return Function;
    }

    public void setFunction(String function) {
        Function = function;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getInformations() {
        return Informations;
    }

    public void setInformations(String informations) {
        Informations = informations;
    }

    public Date getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(Date timestamp) {
        Timestamp = timestamp;
    }
}
