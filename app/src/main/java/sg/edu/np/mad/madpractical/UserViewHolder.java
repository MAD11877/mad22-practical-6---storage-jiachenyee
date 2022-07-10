package sg.edu.np.mad.madpractical;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {

    TextView nameTextView;
    TextView descriptionTextView;

    ImageView profileImageViewExpanded;

    UserViewHandler handler;

    public UserViewHolder(View v) {
        super(v);

        nameTextView = v.findViewById(R.id.nameTextView);
        descriptionTextView = v.findViewById(R.id.descriptionTextView);
        profileImageViewExpanded = v.findViewById(R.id.profileImageViewExpanded);

        ImageView imageView = v.findViewById(R.id.profileImageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.onProfileImageClicked();
            }
        });
    }

    public void setHandler(UserViewHandler userViewHandler) {
        this.handler = userViewHandler;
    }

    public interface UserViewHandler {
        void onProfileImageClicked();
    }
}
