package ch.hevs.aipu_2016_guide.object;

import java.util.Date;
import java.util.Set;

/**
 * Created by Arnaud on 27.11.2015.
 */
public class Talk {

    private int IdTalk;
    private String Title;
    private String Description;
    private Set<Speaker> speakers;
    private Date DateHour;
    private float Duration;
    private Room room;
    private String Keywords;
    private Date Timestamp;

    public int getIdTalk() {
        return IdTalk;
    }

    public void setIdTalk(int idTalk) {
        IdTalk = idTalk;
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

    public Set<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Set<Speaker> speakers) {
        this.speakers = speakers;
    }

    public Date getDateHour() {
        return DateHour;
    }

    public void setDateHour(Date dateHour) {
        DateHour = dateHour;
    }

    public float getDuration() {
        return Duration;
    }

    public void setDuration(float duration) {
        Duration = duration;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getKeywords() {
        return Keywords;
    }

    public void setKeywords(String keywords) {
        Keywords = keywords;
    }

    public Date getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(Date timestamp) {
        Timestamp = timestamp;
    }
}
