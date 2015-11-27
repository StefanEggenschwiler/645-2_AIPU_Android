package ch.hevs.aipu_2016_guide.object;

import java.util.Date;

/**
 * Created by Arnaud on 27.11.2015.
 */
public class News {

    private int IdNew;
    private User user;
    private String Title;
    private String Description;
    private Date date;

    public int getIdNew() {
        return IdNew;
    }

    public void setIdNew(int idNew) {
        IdNew = idNew;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
