package sg.edu.np.mad.madpractical;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    ArrayList<User> users;
    ListActivity activity;

    public UserAdapter(ArrayList<User> users, ListActivity activity) {
        this.users = users;
        this.activity = activity;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.user,
                parent,
                false);

        return new UserViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);

        holder.nameTextView.setText(user.name);
        holder.descriptionTextView.setText(user.description);

        holder.profileImageViewExpanded.setVisibility(user.name.substring(user.name.length() - 1).contentEquals("7") ? View.VISIBLE : View.GONE);

        holder.setHandler(new UserViewHolder.UserViewHandler() {
            @Override
            public void onProfileImageClicked() {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Profile");
                builder.setMessage("MADness");
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Present MainActivity, pass over random number
                        Intent mainActivity = new Intent(activity, MainActivity.class);

                        mainActivity.putExtra("name", user.name);
                        mainActivity.putExtra("description", user.description);
                        mainActivity.putExtra("id", user.id);
                        mainActivity.putExtra("followed", user.followed);

                        activity.startActivityForResult(mainActivity, position);
                    }
                });

                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
