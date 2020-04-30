package com.example.passwordmanager.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.passwordmanager.R;
import com.example.passwordmanager.fragments.AddIdcard_fragment;
import com.example.passwordmanager.fragments.Home_fragment;
import com.example.passwordmanager.fragments.WebList_Fragment;
import com.example.passwordmanager.storage.SharedPrefManager;

public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);
        navigationView.setOnNavigationItemSelectedListener(this);
        displayFragment(new Home_fragment());


    }

    public void displayFragment(Fragment fragment)
    {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.relativeContainer,fragment)
                .commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(),SharedPrefManager.getInstance(getApplicationContext()).userData().getId_user()+"",Toast.LENGTH_LONG).show();
        if(!SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()){
            Intent LoginSuccess = new Intent(getApplicationContext(),MainActivity.class);
            LoginSuccess.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(LoginSuccess);
        }
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId())
        {
            case R.id.menu_home:
                fragment = new Home_fragment();
                break;
            case R.id.menu_account:
                fragment = new AddIdcard_fragment();
                break;
            case R.id.menu_setting:
                Intent intent = new Intent(Home.this,EmailIdListActivity.class);
                startActivity(intent);
                break;
        }
        if(fragment!=null)
        {
            displayFragment(fragment);
        }
        return false;
    }
}
