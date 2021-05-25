package model;

public class UserData {

    private String userId;
    private String email;
    private String userName;
    private String lastName;
    private String avatar;

    public UserData(String userId, String email, String userName, String lastName, String avatar) {
        this.userId = userId;
        this.email = email;
        this.userName = userName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
