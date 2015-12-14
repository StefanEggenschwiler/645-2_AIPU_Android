package ch.hevs.aipu_2016_guide.object;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by Arnaud on 27.11.2015.
 */
public class Organiser {

    private Long IdOrganiser;
    private String Name;
    private String Firstname;
    private String Function;
    private String Company;
    private Bitmap Picture;
    private Date Timestamp;

    public Organiser(Long IdOrganiser, String Name, String Firstname, String Function, String Company, Bitmap Picture){
        this.IdOrganiser = IdOrganiser;
        this.Name = Name;
        this.Firstname = Firstname;
        this.Function = Function;
        this.Company = Company;
        this.Picture = Picture;
    }


    public Long getIdOrganiser() {
        return IdOrganiser;
    }

    public void setIdOrganiser(Long idOrganiser) {
        IdOrganiser = idOrganiser;
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

    public String getFunction() {
        return Function;
    }

    public void setFunction(String function) {
        Function = function;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
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
