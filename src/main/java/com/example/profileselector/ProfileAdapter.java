package com.example.profileselector;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.profileselector.databinding.RowProfileBinding;
import com.example.profileselector.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {
    List<Profile> list = new ArrayList<>();
    private Context context;
    private TaskListener taskListener;

    // Interface for the listener
    public interface TaskListener {
        // On Success
        void onClicked(int status, int position);
    }

    public ProfileAdapter(TaskListener taskListener) {
        this.taskListener = taskListener;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_profile, parent, false);
        RowProfileBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.row_profile, parent, false);
        return new ProfileViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        Profile model = list.get(position);
        holder.bind(model);
        holder.itemRowBinding.button.setText(model.getStatus()==2?"Message":"Accept");
        holder.itemRowBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(model.getStatus()!=2)
                    taskListener.onClicked(2, position);
                else
                    Toast.makeText(context, "Messaging Not Implemented", Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemRowBinding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskListener.onClicked(1, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setProfile(List<Profile> list){
        this.list = list;
        Log.d("TAG", "setProfile: List Size - "+list.size());
        notifyDataSetChanged();
    }

//    public class ProfileViewHolder extends  RecyclerView.ViewHolder {
//        public ProfileViewHolder(@NonNull View itemView) {
//            super(itemView);
//        }
//    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        public RowProfileBinding itemRowBinding;

        public ProfileViewHolder(RowProfileBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            itemRowBinding.setVariable(BR.model, obj);
            itemRowBinding.executePendingBindings();
        }
    }
}
