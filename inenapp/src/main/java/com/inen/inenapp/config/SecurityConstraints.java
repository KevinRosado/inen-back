package com.nixvoid.memoamautas.config;

public class SecurityConstraints {
    public static final String SECRET = "MEMO_SKEY";
    public static final long EXPIRATION_TIME = 900;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    //public static final String SIGN_UP_URL = "/api/services/controller/user";
}
