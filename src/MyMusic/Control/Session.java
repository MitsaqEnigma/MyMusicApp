package MyMusic.Control;

public class Session {

    private String username;
    private String name;
    private String password;

    public Session() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLogin() {
        return getUsername() != null && getPassword() != null;
    }
}
