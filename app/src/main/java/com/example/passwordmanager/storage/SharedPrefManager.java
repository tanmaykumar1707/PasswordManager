package com.example.passwordmanager.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.passwordmanager.models.LoginResponse;

public class SharedPrefManager {
    private static final  String SHARED_PREF_NAME="my_shared_preff";
    private static SharedPrefManager mInstance;
    private Context mctx;

    private SharedPrefManager(Context mctx){
        this.mctx = mctx;
    }

    public static synchronized SharedPrefManager getInstance(Context mctx){
        if(mInstance ==null){
            mInstance=new SharedPrefManager(mctx);

        }
        return  mInstance;
    }

    public void  saveUser(LoginResponse data)
    {
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor = sharedPreferences.edit();
        editor.putString("id_user", data.getId_user());
        editor.putBoolean("status",data.isStatus());
        editor.putString("Message",data.getMessage());

        editor.commit();
    }
    public boolean isLoggedIn()
    {
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return  sharedPreferences.getBoolean("status",false);
        // return sharedPreferences.getString("id_user","-1")!="-1";
    }
    public LoginResponse userData()
    {
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new LoginResponse(
                sharedPreferences.getString("id_user","-1"),
                sharedPreferences.getBoolean("status",true),
                sharedPreferences.getString("Message",null)
        );

    }
    public void clear(){
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor = sharedPreferences.edit();
        clear();
        editor.apply();
    }
}
