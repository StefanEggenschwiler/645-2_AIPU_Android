package ch.hevs.aipu_2016_guide.object;

import java.util.Date;

/**
 * Created by Arnaud on 27.11.2015.
 */
public class Partner {

    private int IdPartner;
    private String Name;
    private String Description;
    private String Website;
    private String Image;
    private Date Timestamp;

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

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public Date getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(Date timestamp) {
        Timestamp = timestamp;
    }
}
