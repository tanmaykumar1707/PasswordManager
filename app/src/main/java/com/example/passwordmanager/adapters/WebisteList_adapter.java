package com.example.passwordmanager.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.passwordmanager.R;
import com.example.passwordmanager.models.WebsiteListResponse;

import java.util.List;

public class WebisteList_adapter extends RecyclerView.Adapter<WebisteList_adapter.UsersViewHolder> {

    private Context mCtxt;
    private List<WebsiteListResponse> web_list;
    public WebisteList_adapter(Context mCtxt ,List<WebsiteListResponse> web_list){
        this.mCtxt = mCtxt;
        this.web_list = web_list;
    }
    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtxt).inflate(R.layout.recyclerview_weblist,parent,false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        WebsiteListResponse websiteList = web_list.get(position);
        holder.web_name.setText(websiteList.getWeb_name());
       holder.web_email.setText(websiteList.getWeb_email());
       holder.web_password.setText(websiteList.getWeb_password());

    }

    @Override
    public int getItemCount() {

        return web_list.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder{
        TextView web_name,web_email,web_password;

        public UsersViewHolder( View itemView) {
            super(itemView);
            web_name = itemView.findViewById(R.id.txtWebsite_name);
            web_email = itemView.findViewById(R.id.txtWebsite_email);
            web_password = itemView.findViewById(R.id.txtWebsite_password);
        }
    }
}

