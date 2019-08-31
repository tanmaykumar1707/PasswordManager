package com.example.passwordmanager.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.passwordmanager.R;
import com.example.passwordmanager.models.LoginResponse;
import com.example.passwordmanager.storage.SharedPrefManager;


public class Home_fragment  extends Fragment {

        private TextView tv_show_user;
        private Button btnAddIdcard,btnWebList,btnEmailList,btnAddEmail1,btnAddWebsite;


        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_home, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            tv_show_user = view.findViewById(R.id.tv_user_show);
            LoginResponse userdata = SharedPrefManager.getInstance(getActivity()).userData();
            tv_show_user.setText("Welcom"+userdata.getId_user());

            btnAddIdcard = view.findViewById(R.id.btnAddId);
            btnWebList = view.findViewById(R.id.btnWebList);
            btnEmailList = view.findViewById(R.id.btnEmailList);
            btnAddEmail1 =view.findViewById(R.id.btnAddEmail_homeFrageXml);
            btnAddWebsite = view.findViewById(R.id.btn_addWeb_homefragment);

            btnAddIdcard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fr  = getFragmentManager().beginTransaction();
                fr.replace(R.id.relativeContainer,new AddIdcard_fragment());
                fr.commit();
                }
            });

            btnWebList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fr  = getFragmentManager().beginTransaction();
                            fr.replace(R.id.relativeContainer,new WebList_Fragment()).commit();
                }
            });

            btnEmailList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fr  = getFragmentManager().beginTransaction();
                    fr.replace(R.id.relativeContainer,new EmailList_fragment()).commit();
                }
            });

            btnAddEmail1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fr  = getFragmentManager().beginTransaction();
                    fr.replace(R.id.relativeContainer,new AddEmail_fragment()).commit();
                }
            });
            btnAddWebsite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fr  = getFragmentManager().beginTransaction();
                    fr.replace(R.id.relativeContainer,new AddWebsite_fragment()).commit();
                }
            });
        }

    }


