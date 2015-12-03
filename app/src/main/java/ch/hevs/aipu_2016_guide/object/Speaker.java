package ch.hevs.aipu_2016_guide.object;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by Arnaud on 27.11.2015.
 */
public class Speaker {

    private int IdSpeaker;
    private String Name;
    private String Firstname;
    private String Email;
    private String Function;
    private String Company;
    private String Website;
    private String Informations;
    private Bitmap Picture;
    private Date Timestamp;

    public Speaker(int IdSpeaker, String Name, String Firstname, String Email, String Function, String Company, String Website, String Informations, Bitmap Picture){
        this.IdSpeaker = IdSpeaker;
        this.Name = Name;
        this.Firstname = Firstname;
        this.Email = Email;
        this.Function = Function;
        this.Company = Company;
        this.Website = Website;
        this.Informations = Informations;
        this.Picture = Picture;
    }


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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String Company) {
        Company = Company;
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

    public Bitmap getPicture() {
        return Picture;
    }

    public void setPicture(Bitmap picture) {
        Picture = picture;
    }

    public Date getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(Date timestamp) {
        Timestamp = timestamp;
    }
}
