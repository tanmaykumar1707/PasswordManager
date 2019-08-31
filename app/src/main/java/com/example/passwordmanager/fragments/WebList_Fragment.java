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
import android.widget.Toast;

import com.example.passwordmanager.R;
import com.example.passwordmanager.adapters.WebisteList_adapter;
import com.example.passwordmanager.api.RetrofitClient;
import com.example.passwordmanager.models.WebsiteListResponse;
import com.example.passwordmanager.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebList_Fragment  extends Fragment {
    private RecyclerView recyclerView;
    private WebisteList_adapter adapter;
    private List<WebsiteListResponse> weblist_in_this_fragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.weblist_fragment,container,false);
        }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading Data.....");
        progressDialog.show();
        recyclerView = view.findViewById(R.id.rec_view_inWebLis_fragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        Call<List<WebsiteListResponse>> call = RetrofitClient.getInstance().getApi().website_list_api(Integer.parseInt(SharedPrefManager.getInstance(getContext()).userData().getId_user()));

        call.enqueue(new Callback<List<WebsiteListResponse>>() {
            @Override
            public void onResponse(Call<List<WebsiteListResponse>> call, Response<List<WebsiteListResponse>> response) {
                weblist_in_this_fragment =  response.body();
                adapter = new WebisteList_adapter(getActivity(), weblist_in_this_fragment);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<WebsiteListResponse>> call, Throwable t) {

            }
        });


    }
}
