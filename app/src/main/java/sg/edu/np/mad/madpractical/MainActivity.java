package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intents = getIntent();

        String name = intents.getStringExtra("name");
        String description = intents.getStringExtra("description");
        Integer id = intents.getIntExtra("id", 0);
        Boolean followed = intents.getBooleanExtra("followed", false);

        user = new User(name, description, id, followed);

        TextView usernameTextView = (TextView)findViewById(R.id.usernameTextView);
        usernameTextView.setText(user.name);

        TextView descriptionTextView = (TextView)findViewById(R.id.descriptionTextView);
        descriptionTextView.setText(user.description);

        Button followButton = (Button) findViewById(R.id.followButton);
        followButton.setText(user.followed ? "Unfollow" : "Follow");

        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.followed = !user.followed;
                Button followButton = (Button) view;
                followButton.setText(user.followed ? "Unfollow" : "Follow");
                Toast.makeText(getApplicationContext(),user.followed ? "Followed" : "Unfollowed",Toast.LENGTH_SHORT).show();

                Intent data = new Intent();
                data.putExtra("followed", user.followed);

                setResult(RESULT_OK, data);
            }
        });

        Button messageButton = findViewById(R.id.messageButton);
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent messageGroup = new Intent(MainActivity.this, MessageGroup.class);

                startActivity(messageGroup);
            }
        });
    }
}