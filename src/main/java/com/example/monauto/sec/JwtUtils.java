package com.example.monauto.sec;

public class JwtUtils {
    public static final String SECRET = "mySecret";
    public static final String CONFIRM_EMAIL_SECRET = "email_confirm_secret";
    public static final String AUTH_HEADER = "Authorization";
    public static final String BEARER_HEADER = "Bearer ";
    public static final long EXPIRE_ACCESS_TOKEN = 60*60*1000;
    public static final long EXPIRE_REFRESH_TOKEN = 5*24*60*60*1000;
    public static final String ADMIN_PASSWORD = "abcd";
    public static final String ADMIN_EMAIL= "admin.email@email.com";

}
