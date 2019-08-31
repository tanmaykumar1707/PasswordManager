package com.example.passwordmanager.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.passwordmanager.R;
import com.example.passwordmanager.models.Email_idListResponse;
import com.example.passwordmanager.models.WebsiteListResponse;

import java.util.List;

public class EmailList_adapter extends RecyclerView.Adapter <EmailList_adapter.EmailListViewHolder>{

    private Context mCtxt;
    private List<Email_idListResponse> email_list;

    public EmailList_adapter(Context mCtxt, List<Email_idListResponse> email_list) {
        this.mCtxt = mCtxt;
        this.email_list = email_list;
    }

    @NonNull
    @Override
    public EmailListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtxt).inflate(R.layout.recyclercardview_emailist,parent,false);
        return new EmailListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmailListViewHolder emailListViewHolder, int i) {
        Email_idListResponse EmailList = email_list.get(i);
        emailListViewHolder.txtDomain.setText(EmailList.getDomain());
        emailListViewHolder.txtEmail.setText(EmailList.getEmail_id());
        emailListViewHolder.txtPassword.setText(EmailList.getPassword());

    }

    @Override
    public int getItemCount() {
        return email_list.size();
    }

    class EmailListViewHolder extends RecyclerView.ViewHolder{
        TextView txtDomain,txtEmail,txtPassword;



      public EmailListViewHolder(@NonNull View itemView) {
          super(itemView);
          txtDomain = itemView.findViewById(R.id.txtDomain);
          txtEmail = itemView.findViewById(R.id.txtEmailid);
          txtPassword = itemView.findViewById(R.id.txtEmailPassword);
      }
  }
}
