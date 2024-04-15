package arki.smartsearch.common.pojo;

public class User {
    private String id;
    private String username;
    private String password;
    private String history_id;

    public User(String id, String username, String password, String history_id) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.history_id = history_id;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHistory_id() {
        return history_id;
    }

    public void setHistory_id(String history_id) {
        this.history_id = history_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", history_id='" + history_id + '\'' +
                '}';
    }
}
