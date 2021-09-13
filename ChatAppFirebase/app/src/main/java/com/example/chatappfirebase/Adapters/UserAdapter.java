package com.example.chatappfirebase.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chatappfirebase.MessageActivity;
import com.example.chatappfirebase.Model.Users;
import com.example.chatappfirebase.R;
import com.google.firebase.auth.FirebaseUser;

import java.text.BreakIterator;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyHolder> {
    Context context;
    List<Users> usersList;
    boolean isChat;

    String friendId;

    public UserAdapter(Context context, List<Users> usersList, boolean isChat) {
        this.context = context;
        this.usersList = usersList;
        this.isChat = isChat;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layoutofusers, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

            Users user = usersList.get(position);
            friendId = user.getId();
            holder.username.setText(user.getUsername());

            if(user.getImageURL().equals("default")){
                holder.imageView.setImageResource(R.drawable.user);
            }
            else{
                Glide.with(context).load(user.getImageURL()).into(holder.imageView);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MessageActivity.class);
                    intent.putExtra("friendid", friendId);
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView username;
        CircleImageView imageView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username_userfrag);
            imageView = itemView.findViewById(R.id.image_user_userfrag);
            itemView.setOnClickListener((View.OnClickListener) this);

        }

        @Override
        public void onClick(View v) {

            Users users = usersList.get(getAdapterPosition());

            friendId = users.getId();

            Intent intent = new Intent(context, MessageActivity.class);
            intent.putExtra("friendid", friendId);
            context.startActivity(intent);

        }
    }
}









