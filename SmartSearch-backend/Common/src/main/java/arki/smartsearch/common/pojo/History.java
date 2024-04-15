package arki.smartsearch.common.pojo;

public class History {
    private String history_id;
    private String keyword;
    private String create_time;
    private String user_id;

    public History(String history_id, String keyword, String create_time, String user_id) {
        this.history_id = history_id;
        this.keyword = keyword;
        this.create_time = create_time;
        this.user_id = user_id;
    }

    public History() {
    }

    public String getHistory_id() {
        return history_id;
    }

    public void setHistory_id(String history_id) {
        this.history_id = history_id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "History{" +
                "history_id='" + history_id + '\'' +
                ", keyword='" + keyword + '\'' +
                ", create_time='" + create_time + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
