package ch.hevs.aipu_2016_guide.object;

import android.graphics.Bitmap;

import java.sql.Blob;
import java.util.Date;

/**
 * Created by Arnaud on 27.11.2015.
 */
public class Partner {

    private Long id;
    private String name;
    private String description;
    private String website;
    private Blob logo;
    private Date lastmodified;

    public Partner(Long IdPartner, String Name, String Description, String Website, Blob Image){
        this.id = IdPartner;
        this.name = Name;
        this.description = Description;
        this.website = Website;
        this.logo = Image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Blob getLogo() {
        return logo;
    }

    public void setLogo(Blob logo) {
        this.logo = logo;
    }

    public Date getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified;
    }
}
