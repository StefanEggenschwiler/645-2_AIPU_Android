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
        public static final String CREATE_TABLE_ACTOR="Create Table "
                +Table_News+"( "+KEY_ID
                +" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_IDUSER
                +" INTEGER NOT NULL,"+KEY_TITLE
                +" TEXT NOT NULL," +KEY_DESCRIPTION
                +" TEXT NOT NULL,"+KEY_DATE
                +" DATE, FOREIGN KEY("+KEY_IDUSER+") REFERENCES User(IdUser));";
    }
}
