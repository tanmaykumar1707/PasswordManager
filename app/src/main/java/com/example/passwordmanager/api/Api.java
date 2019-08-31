package com.example.passwordmanager.api;

import com.example.passwordmanager.models.Email_idListResponse;
import com.example.passwordmanager.models.EmailidAdd_response;
import com.example.passwordmanager.models.LoginResponse;
import com.example.passwordmanager.models.WebsiteListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {


    @FormUrlEncoded
    @POST("Login_api/login")
    Call<LoginResponse> checkuser(
            @Field("userid") String userid,
            @Field("password") String password
    );

       @FormUrlEncoded
    @POST("Website_api/showAllwebsite")
    Call<List<WebsiteListResponse>> website_list_api(
            @Field("id_user") int id_user
    );

       @FormUrlEncoded
    @POST("Email_api/showAllEmail")
    Call<List<Email_idListResponse>> get_email_list_api(
            @Field("id_user") int id_user
       );

       @FormUrlEncoded
    @POST("Email_api/email_insert")
    Call<EmailidAdd_response>  addEmail_api(
               @Field("id_user") int id,
               @Field("txtEmployeeName") String etDomain,
               @Field("txtAddress") String etEmail,
               @Field("password") String etPassword
       );
}
