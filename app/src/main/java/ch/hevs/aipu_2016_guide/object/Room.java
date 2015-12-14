package ch.hevs.aipu_2016_guide.object;

import java.util.Date;

/**
 * Created by Arnaud on 27.11.2015.
 */
public class Room {
    private Long id;
    private String name;
    private String floor;
    private Date lastModified;

    public Long getIdRoom() {return id;}

    public void setIdRoom(Long idRoom) {
        id = idRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        floor = floor;
    }

    public Date getTimestamp() {
        return lastModified;
    }

    public void setTimestamp(Date lastModified) {
        lastModified = lastModified;
    }
}
