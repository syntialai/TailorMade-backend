package com.future.tailormade.constants;

public class ApiPath {

    public static final String API_PATH = "/api";

    /**
     * Users API Path
     */
    public static final String USER = API_PATH + "/user";
    public static final String USERS = API_PATH + "/users";
    public static final String USERS_ID = USERS + "/{id}";
    public static final String USER_SIGN_IN = USER + "/_sign-in";
    public static final String USER_SIGN_OUT = USER + "/_sign-out";
    public static final String USER_SIGN_UP = USER + "/_sign-up";
    public static final String USER_REFRESH_TOKEN = USER + "/_refresh-token";
    public static final String USERS_ID_ACTIVATE_TAILOR = USERS_ID + "/_activate-tailor";
    public static final String USERS_ID_UPDATE_BASIC_INFO = USERS_ID + "/_update-basic-info";
    public static final String USERS_ID_UPDATE_MORE_INFO = USERS_ID + "/_update-more-info";

    /**
     * Designs API Path
     */
    public static final String DESIGNS = API_PATH + "/designs";
    public static final String DESIGNS_ID = DESIGNS + "/{id}";

    /**
     * Search API Path
     */
    public static final String SEARCH = API_PATH + "/search";
    public static final String SEARCH_DESIGN = SEARCH + "/design";
    public static final String SEARCH_TAILOR = SEARCH + "/tailor";

    /**
     * Tailors API Path
     */
    public static final String DASHBOARD_TAILORS = API_PATH + "/dashboard/tailors";
    public static final String TAILORS = API_PATH + "/tailors";
    public static final String TAILORS_ID = TAILORS + "/{tailorId}";
    public static final String TAILORS_ID_DESIGNS = TAILORS_ID + "/designs";
    public static final String TAILORS_ID_DESIGNS_ID = TAILORS_ID_DESIGNS + "/{id}";

    /**
     * Wishlist API Path
     */
    public static final String USERS_ID_WISHLISTS = USERS + "/{userId}/wishlists";
    public static final String USERS_ID_WISHLISTS_ID = USERS_ID_WISHLISTS + "/{id}";
    public static final String USERS_ID_WISHLISTS_ID_CHECKOUT = USERS_ID_WISHLISTS_ID + "/_checkout";
    public static final String USERS_ID_WISHLISTS_ID_EDIT_QUANTITY = USERS_ID_WISHLISTS_ID + "/_edit-quantity";

    /**
     * Order API Path
     */
    public static final String USERS_ID_ORDERS = USERS + "/{userId}/orders";
    public static final String USERS_ID_ORDERS_ID = USERS_ID_ORDERS + "/{id}";
    public static final String TAILORS_ID_ORDERS = TAILORS_ID + "/orders";
    public static final String TAILORS_ID_ORDERS_ID = TAILORS_ID_ORDERS + "/{id}";
    public static final String TAILORS_ID_ORDERS_ID_ACCEPT = TAILORS_ID_ORDERS_ID + "/_accept";
    public static final String TAILORS_ID_ORDERS_ID_REJECT = TAILORS_ID_ORDERS_ID + "/_reject";

    /**
     * Image Path
     */
    public static final String UPLOADS_PATH = "./uploads/";
    public static final String UPLOADS_FOLDER_PATH = "/uploads/";
    public static final String IMAGE_FILE_PATH_FILE_NAME = "/image/{filePath}/{fileName}";
}
