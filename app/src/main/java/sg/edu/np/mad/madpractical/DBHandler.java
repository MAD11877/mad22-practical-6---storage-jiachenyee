package sg.edu.np.mad.madpractical;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    public DBHandler(@Nullable Context context) {
        super(context, "user.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createCommand = "CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, name STRING, description STRING, followed BOOLEAN)";
        sqLiteDatabase.execSQL(createCommand);

        String addData = "INSERT INTO users (id, name, description, followed) VALUES ";

        for (int i = 1; i <= 20; i++) {
            int id = (int) Math.round(Math.random() * 1000000);
            String randomNameSuffix = String.valueOf((int) Math.round(Math.random() * 1000000));
            String randomDescriptionSuffix = String.valueOf((int) Math.round(Math.random() * 100000000));

            String name = "User " + randomNameSuffix;
            String description = "This is a description. Here's a random number! " + randomDescriptionSuffix;
            Boolean followed = Math.random() > 0.5;

            addData += "(" + String.valueOf(id) + ", \"" + name + "\", \"" + description + "\", " + followed + ")," ;
        }

        sqLiteDatabase.execSQL(addData.substring(0, addData.length() - 1));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        onCreate(sqLiteDatabase);
    }

    public ArrayList<User> getUsers() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ArrayList<User> userList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users", null);

        while (cursor.moveToNext()){
            String name = cursor.getString((int)cursor.getColumnIndex("name"));
            String description = cursor.getString((int)cursor.getColumnIndex("description"));
            Integer id = cursor.getInt((int)cursor.getColumnIndex("id"));
            Boolean follow = Boolean.parseBoolean(cursor.getString((int)cursor.getColumnIndex("followed")));

            User user = new User(name, description, id, follow) ;
            userList.add(user);
        }

        return userList;
    }

    public void updateUser(User user){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        Integer refId = user.id;

        // Update followed in DB
        String UPDATE = "UPDATE users SET followed = \"" + user.followed + "\" WHERE id \"" + refId + "\"";

        sqLiteDatabase.execSQL(UPDATE);
        sqLiteDatabase.close();
    }
}
