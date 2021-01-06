package com.future.tailormade.constants;

public class ApiPath {

    public static final String API_PATH = "/api";

    /**
     * Users API Path
     */
    public static final String USERS = API_PATH + "/users";
    public static final String USERS_ID = API_PATH + "/users/{id}";
    public static final String USERS_SIGN_IN = USERS + "/_sign-in";
    public static final String USERS_SIGN_OUT = USERS + "/_sign-out";
    public static final String USERS_SIGN_UP = USERS + "/_sign-up";
    public static final String USERS_REFRESH_TOKEN = USERS + "/_refresh-token";
    public static final String USERS_ACTIVATE_TAILOR = USERS_ID + "/_activate-tailor";
    public static final String USERS_UPDATE_BASIC_INFO = USERS_ID + "/_update-basic-info";
    public static final String USERS_UPDATE_MORE_INFO = USERS_ID + "/_update-more-info";
}
