package com.example.passwordmanager.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.example.passwordmanager.R;
import com.example.passwordmanager.adapters.EmailList_adapter;
import com.example.passwordmanager.adapters.WebisteList_adapter;
import com.example.passwordmanager.api.RetrofitClient;
import com.example.passwordmanager.models.Email_idListResponse;
import com.example.passwordmanager.models.WebsiteListResponse;
import com.example.passwordmanager.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailIdListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EmailList_adapter adapter;
    private List<Email_idListResponse> Emaild_id_response;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_email_id_list);


        recyclerView = findViewById(R.id.rec_view_inEmail_activity);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         final ProgressDialog progressDialog = new  ProgressDialog(this);
        progressDialog.setMessage("Loading Data.....");
        progressDialog.show();

        Call<List<Email_idListResponse>> call = RetrofitClient.getInstance().getApi().get_email_list_api(Integer.parseInt(SharedPrefManager.getInstance(EmailIdListActivity.this).userData().getId_user()));

        call.enqueue(new Callback<List<Email_idListResponse>>() {
            @Override
            public void onResponse(Call<List<Email_idListResponse>> call, Response<List<Email_idListResponse>> response) {
                showIt(response.body());
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Email_idListResponse>> call, Throwable t) {

            }


        });


    }//onCreate closing

    private void showIt(List<Email_idListResponse> response){

        adapter = new EmailList_adapter(getApplicationContext(),response);
        recyclerView.setAdapter(adapter);
    }
}
