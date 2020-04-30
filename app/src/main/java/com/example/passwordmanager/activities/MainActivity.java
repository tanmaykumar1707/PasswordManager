package com.example.passwordmanager.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.passwordmanager.R;
import com.example.passwordmanager.api.RetrofitClient;
import com.example.passwordmanager.models.LoginResponse;
import com.example.passwordmanager.storage.SharedPrefManager;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    RelativeLayout rellay1,rellay2;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
    public void run(){
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }

    };

   private EditText user,pass;
   private TextView txt;
   Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay_bottom);

        handler.postDelayed(runnable,2000);

        user = findViewById(R.id.etUserid);
        pass = findViewById(R.id.etPassword);
         findViewById(R.id.btnSignup).setOnClickListener(this);
        btn_signup = findViewById(R.id.btn_signupNow);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup_activity = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(signup_activity);
            }
        });

    } // ON CREATE Method

    @Override
    protected void onStart() {
        super.onStart();
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent LoginSuccess = new Intent(MainActivity.this,Home.class);
            LoginSuccess.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(LoginSuccess);

        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnSignup:

                checkuser();
                // progressDialog.dismiss();
                break;
        }
    }
    private void checkuser(){
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Please Wait Logging...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String userid = user.getText().toString().trim();
        String passw = pass.getText().toString().trim();



        if(userid.isEmpty()){
               progressDialog.dismiss();
                user.setError("UserId required");
                user.requestFocus();
                return ;
        }
        if(passw.isEmpty()){
            progressDialog.dismiss();
            pass.setError("Please Enter Correct Password");
            pass.requestFocus();
            return;
        }
        Call<LoginResponse> call = RetrofitClient.getInstance().getApi().checkuser(userid,passw);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse Lresponse = response.body();

                if (Lresponse != null) {
                    if(Lresponse.isStatus()) {

                        Toast.makeText(MainActivity.this, Lresponse.getMessage()+Lresponse.getId_user(), Toast.LENGTH_LONG).show();
                        SharedPrefManager.getInstance(MainActivity.this).saveUser(Lresponse);
                        Intent LoginSuccess = new Intent(MainActivity.this,Home.class);
                         progressDialog.dismiss();
                        LoginSuccess.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(LoginSuccess);

                    }
                    else {
                       progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Error occured", Toast.LENGTH_LONG).show();
                    }
                }  else {
                  progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "User Id or Password is Wrong", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("pwdxx", "onFailure: "+t.getLocalizedMessage());
                Toast.makeText(MainActivity.this,"Some Error occured!",Toast.LENGTH_LONG).show();
            }
        });



    }
}
