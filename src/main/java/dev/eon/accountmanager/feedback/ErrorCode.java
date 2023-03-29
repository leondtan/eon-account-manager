package dev.eon.accountmanager.feedback;

public enum ErrorCode {

    //region #--HTTP
    REQUEST_DATA_INCORRECT("Data Format is Incorrect"),
    API_NOT_EXIST("API Path not Exist"),
    METHOD_INCORRECT("HTTP Method for API is Incorrect"),
    SERVER_ERROR("Server Error"),
    UNKNOWN_ERROR("Error Unknown"),
    //endregion #--HTTP

    //region #--USER
    USER_NOT_FOUND("User Not Found"),
    USER_EXISTED("User already Registered"),
    //endregion #--USER

    //region #--AUTHENTICATION
    AUTH_INCORRECT("Bearer Token Provided is Invalid"),
    AUTH_NOT_EXIST("Required Bearer Token not Provided"),
    AUTH_USER_NOT_ALLOWED("Logged User has no Appropriate Role"),
    //endregion #--AUTHENTICATION

    //region #--VALIDATION
    REQUEST_BODY_VALIDATION("Some Request Body Inputs are Incorrect"),
    FORM_DATA_VALIDATION("Some Form Data Inputs are Incorrect"),
    REQUEST_BODY_NOT_EXIST("Required Request Body not Provided"),
    //endregion #--VALIDATIO

    GENERAL("An Error Appeared"),
    ITEM_NOT_EXIST("Requested Item not Exist")
    ;
    private final String description;

    ErrorCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
