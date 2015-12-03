package ch.hevs.aipu_2016_guide.database;

import android.provider.BaseColumns;

/**
 * Created by Arnaud on 27.11.2015.
 */
public class SQLModel {

    public abstract class NewsEntry implements BaseColumns
    {
        //Table name
        public static final String Table_News="news";

        //Column name
        public static final String KEY_ID="IdNews";
        public static final String KEY_IDUSER="IdUser";
        public static final String KEY_TITLE="Title";
        public static final String KEY_DESCRIPTION="Description";
        public static final String KEY_DATE="Date";

        //Creation table
        public static final String CREATE_TABLE_NEWS="Create Table "
                +Table_News+"( "+KEY_ID
                +" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_IDUSER
                +" INTEGER NOT NULL,"+KEY_TITLE
                +" TEXT NOT NULL," +KEY_DESCRIPTION
                +" TEXT NOT NULL,"+KEY_DATE
                +" TEXT, FOREIGN KEY("+KEY_IDUSER+") REFERENCES User(IdUser));";
    }
    public abstract class UserEntry implements BaseColumns
    {
        //Table name
        public static final String Table_Users="user";

        //Column name
        public static final String KEY_ID="IdUser";
        public static final String KEY_USERNAME="Username";

        //Creation of table
        public static final String CREATE_TABLE_USER="Create Table "
                +Table_Users+"( "+KEY_ID
                +" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_USERNAME
                +" TEXT NOT NULL);,";
    }
    public abstract class PartnerEntry implements BaseColumns
    {
        //Table name
        public static final String Table_Partner="partner";

        //Column name
        public static final String KEY_ID="IdPartner";
        public static final String KEY_NAME="Name";
        public static final String KEY_DESCRIPTION="Description";
        public static final String KEY_WEBSITE="Website";
        public static final String KEY_LOGO="Logo";
        public static final String KEY_TIMESTAMP="Timestamp";

        //Creation of table
        public static final String CREATE_TABLE_PARTNER="Create Table "
                +Table_Partner+"("+KEY_ID
                +" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_NAME
                +" TEXT NOT NULL,"+KEY_DESCRIPTION
                +" TEXT NOT NULL,"+KEY_WEBSITE
                +" TEXT NOT NULL,"+KEY_LOGO
                +" BLOB NOT NULL,"+KEY_TIMESTAMP
                +" TEXT NOT NULL);";
    }
    public abstract class OrganiserEntry implements BaseColumns
    {
        //Table name
        public static final String Table_Organisers="ograniser";

        //Column name
        public static final String KEY_ID="IdOrganiser";
        public static final String KEY_NAME="Name";
        public static final String KEY_FIRSTNAME="Firstname";
        public static final String KEY_FUNCTION="Function";
        public static final String KEY_COMPANY="Company";
        public static final String KEY_PICTURE="Picture";
        public static final String KEY_TIMESTAMP="Timestamp";

        //Creation of table
        public static final String CREATE_TABLE_ORGANISER="Create Table "
                +Table_Organisers+"("+KEY_ID
                +" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_NAME
                +" TEXT NOT NULL,"+KEY_FIRSTNAME
                +" TEXT NOT NULL,"+KEY_FUNCTION
                +" TEXT NOT NULL,"+KEY_COMPANY
                +" TEXT NOT NULL,"+KEY_PICTURE
                +" BLOB NOT NULL,"+KEY_TIMESTAMP
                +" TEXT NOT NULL);";
    }
    public abstract class RoomsEntry implements BaseColumns
    {
        //Table name
        public static final String Table_Rooms="room";

        //Column name
        public static final String KEY_ID="IdRoom";
        public static final String KEY_NAME="Name";
        public static final String KEY_FLOOR="Floor";
        public static final String KEY_TIMESTAMP="Timestamp";

        //Creation of table
        public static final String CREATE_TABLE_ROOMS="Create Table "
                +Table_Rooms+"("+KEY_ID
                +" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_NAME
                +" TEXT NOT NULL,"+KEY_FLOOR
                +" TEXT NOT NULL,"+KEY_TIMESTAMP
                +" TEXT NOT NULL);";
    }
    public abstract class SpeakersEntry implements BaseColumns
    {
        //Table name
        public static final String Table_Speaker="speaker";

        //Column name
        public static final String KEY_ID="IdSpeaker";
        public static final String KEY_NAME="Name";
        public static final String KEY_FIRSTNAME="Firstname";
        public static final String KEY_EMAIL="Email";
        public static final String KEY_FUNCTION="Function";
        public static final String KEY_COMPANY="Company";
        public static final String KEY_WEBSITE="Website";
        public static final String KEY_INFORMATIONS="Informations";
        public static final String KEY_PICTURE="Picture";
        public static final String KEY_TIMESTAMP="Timestamp";

        //Creation of table
        public static final String CREATE_TABLE_SPEAKER="Create Table "
                +Table_Speaker+"("+KEY_ID
                +" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_NAME
                +" TEXT NOT NULL,"+KEY_FIRSTNAME
                +" TEXT NOT NULL,"+KEY_EMAIL
                +" TEXT NOT NULL,"+KEY_FUNCTION
                +" TEXT NOT NULL,"+KEY_COMPANY
                +" TEXT NOT NULL,"+KEY_WEBSITE
                +" TEXT NOT NULL,"+KEY_INFORMATIONS
                +" TEXT NOT NULL,"+KEY_PICTURE
                +" BLOB NOT NULL,"+KEY_TIMESTAMP
                +" TEXT NOT NULL);";
    }
    public abstract class TalksEntry implements BaseColumns
    {
        //Table name
        public static final String Table_Talks="talk";

        //Column name
        public static final String KEY_ID="IdTalk";
        public static final String KEY_TITLE="Title";
        public static final String KEY_DESCRIPTION="Description";
        public static final String KEY_DATEHOUR="DateHour";
        public static final String KEY_DURATION="Duration";
        public static final String KEY_IDROOM="IdRoom";
        public static final String KEY_KEYWORDS="KeyWords";
        public static final String KEY_TIMESTAMP="Timestamp";

        //Creation of table
        public static final String CREATE_TABLE_TALKS="Create Table "
                +Table_Talks+"("+KEY_ID
                +" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_TITLE
                +" TEXT NOT NULL,"+KEY_DESCRIPTION
                +" TEXT NOT NULL,"+KEY_DATEHOUR
                +" TEXT NOT NULL,"+KEY_DURATION
                +" FLOAT NOT NULL,"+KEY_IDROOM
                +" INTEGER NOT NULL,"+KEY_KEYWORDS
                +" TEXT NOT NULL,"+KEY_TIMESTAMP
                +" TEXT NOT NULL, FOREIGN KEY("+KEY_IDROOM+") REFERENCES Room(IdRoom));";
    }
    public abstract class TalkSpeakerEntry implements BaseColumns
    {
        //Table name
        public static final String Table_TalksSpeaker="TalkSpeaker";

        //Column name
        public static final String KEY_IDTALK="IdTalk";
        public static final String KEY_IDSPEAKER="IdSpeaker";

        //Creation of table
        public static final String CREATE_TABLE_TALKSPEAKER="Create Table "
                +Table_TalksSpeaker+"("+KEY_IDTALK
                +" INTEGER NOT NULL,"+KEY_IDSPEAKER
                +" INTEGER NOT NULL,FOREIGN KEY("+KEY_IDTALK+") REFERENCES Talk(IdTalk)," +
                "FOREIGN KEY("+KEY_IDSPEAKER+") REFERENCES Speaker(IdSpeaker));";
    }


}
