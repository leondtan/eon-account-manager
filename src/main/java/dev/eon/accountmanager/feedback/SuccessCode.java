package dev.eon.accountmanager.feedback;

public enum SuccessCode {
    //region #--AUTHENTICATION
    USER_CREATED("User Created Successfully");
    //endregion #--AUTHENTICATION

    private String description;

    SuccessCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
