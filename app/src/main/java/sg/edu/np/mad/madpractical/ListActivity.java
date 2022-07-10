package sg.edu.np.mad.madpractical;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList<User> users = new ArrayList<>();
    DBHandler dbHandler = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        users = dbHandler.getUsers();
//        for (int i = 1; i <= 20; i++) {
//
//            int randomID = (int) Math.round(Math.random() * 1000000);
//            String randomNameSuffix = String.valueOf((int) Math.round(Math.random() * 1000000));
//            String randomDescriptionSuffix = String.valueOf((int) Math.round(Math.random() * 100000000));
//
//            users.add(new User("User " + randomNameSuffix, "This is a description. Here's a random number! " + randomDescriptionSuffix, randomID, Math.random() > 0.5));
//        }

        RecyclerView userRecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.Adapter mAdapter = new UserAdapter(users, this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false);

        userRecyclerView.setLayoutManager(mLayoutManager);
        userRecyclerView.setItemAnimator(new DefaultItemAnimator());
        userRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        User user = users.get(requestCode);
        user.followed = data.getBooleanExtra("followed", user.followed);
        dbHandler.updateUser(user);
    }
}