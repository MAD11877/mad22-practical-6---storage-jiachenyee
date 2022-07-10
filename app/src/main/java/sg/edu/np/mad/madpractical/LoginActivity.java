package sg.edu.np.mad.madpractical;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDatabase = FirebaseDatabase.getInstance("https://mad-practical-89297-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();

        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("Users").child("mad").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            DataSnapshot snapshot = task.getResult();

                            String username = (String) snapshot.child("username").getValue();
                            String password = String.valueOf((long) snapshot.child("password").getValue());

                            Intent listActivity = new Intent(LoginActivity.this, ListActivity.class);

                            EditText usernameEditText = findViewById(R.id.usernameEditText);
                            EditText passwordEditText = findViewById(R.id.passwordEditText);

                            if (username.equals(usernameEditText.getText().toString()) && password.equals(passwordEditText.getText().toString())) {
                                startActivity(listActivity);
                            }
                        }
                        else {
                            System.out.println(task.getException());
                        }
                    }
                });
            }
        });
    }
}