package ch.hevs.aipu_2016_guide.object;

import java.util.Date;

/**
 * Created by Arnaud on 27.11.2015.
 */
public class Room {
    private Long IdRoom;
    private String Name;
    private String Floor;
    private Date Timestamp;

    public Long getIdRoom() {
        return IdRoom;
    }

    public void setIdRoom(Long idRoom) {
        IdRoom = idRoom;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFloor() {
        return Floor;
    }

    public void setFloor(String floor) {
        Floor = floor;
    }

    public Date getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(Date timestamp) {
        Timestamp = timestamp;
    }
}
