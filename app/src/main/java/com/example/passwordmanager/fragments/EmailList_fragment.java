package com.example.passwordmanager.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.passwordmanager.R;
import com.example.passwordmanager.adapters.EmailList_adapter;
import com.example.passwordmanager.api.RetrofitClient;
import com.example.passwordmanager.models.Email_idListResponse;
import com.example.passwordmanager.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailList_fragment extends Fragment {
    private RecyclerView recyclerView;
    private EmailList_adapter emailList_adapter;
    private List<Email_idListResponse> email_response_list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_email_id_list,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading all Email...");
        progressDialog.show();
        recyclerView = view.findViewById(R.id.rec_view_inEmail_activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<List<Email_idListResponse>> call = RetrofitClient.getInstance().getApi()
                                                .get_email_list_api(Integer.parseInt(SharedPrefManager.getInstance(getContext()).userData().getId_user()));
        call.enqueue(new Callback<List<Email_idListResponse>>() {
            @Override
            public void onResponse(Call<List<Email_idListResponse>> call, Response<List<Email_idListResponse>> response) {
                email_response_list= response.body();
                emailList_adapter = new EmailList_adapter(getActivity(),email_response_list);
                recyclerView.setAdapter(emailList_adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Email_idListResponse>> call, Throwable t) {

            }
        });
    }
}
