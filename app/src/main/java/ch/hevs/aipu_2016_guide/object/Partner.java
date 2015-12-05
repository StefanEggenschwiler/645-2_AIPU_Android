package ch.hevs.aipu_2016_guide.object;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by Arnaud on 27.11.2015.
 */
public class Partner {

    private int IdPartner;
    private String Name;
    private String Description;
    private String Website;
    private Bitmap Image;
    private Date Timestamp;

    public Partner(int IdPartner, String Name, String Description, String Website, Bitmap Image){
        this.IdPartner = IdPartner;
        this.Name = Name;
        this.Description = Description;
        this.Website = Website;
        this.Image = Image;
    }

    public int getIdPartner() {
        return IdPartner;
    }

    public void setIdPartner(int idPartner) {
        IdPartner = idPartner;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public Bitmap getImage() {
        return Image;
    }

    public void setImage(Bitmap image) {
        Image = image;
    }

    public Date getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(Date timestamp) {
        Timestamp = timestamp;
    }
}
