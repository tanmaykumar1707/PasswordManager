package com.example.passwordmanager.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.passwordmanager.R;
import com.example.passwordmanager.api.RetrofitClient;
import com.example.passwordmanager.models.EmailidAdd_response;
import com.example.passwordmanager.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEmail_fragment extends Fragment {

    private EmailidAdd_response email_response;
    private EditText etDomain,etEmail,etPassword;
    private Button btnAdEmail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_addemail,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etDomain = view.findViewById(R.id.txtEmaildomain);
        etEmail = view.findViewById(R.id.etEmailId);
        etPassword= view.findViewById(R.id.etEmailPassword);
        btnAdEmail = view.findViewById(R.id.btnAddEmail);
        btnAdEmail.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String domain = etDomain.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Adding Email Id...");
                progressDialog.show();
                int id = Integer.parseInt(SharedPrefManager.getInstance(getContext()).userData().getId_user());
                Call<EmailidAdd_response> call = RetrofitClient.getInstance()
                                                .getApi().addEmail_api(id,domain,email,password);

                call.enqueue(new Callback<EmailidAdd_response>() {
                    @Override
                    public void onResponse(Call<EmailidAdd_response> call, Response<EmailidAdd_response> response) {
                        email_response = response.body();
                        if(email_response.isStatus())
                        {
                            Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                            FragmentTransaction fr  = getFragmentManager().beginTransaction();
                            fr.replace(R.id.relativeContainer,new EmailList_fragment()).commit();
                        } else
                        {
                            Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<EmailidAdd_response> call, Throwable t) {

                    }
                });
            }
        });



    }
}
