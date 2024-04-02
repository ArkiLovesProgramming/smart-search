package arki.smartsearch.sssearchserver.dao;


import arki.smartsearch.common.pojo.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class HistoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int createHistory(History history){
        String sql = "insert into history (history_id, user_id, keyword, create_time) " +
                "values(?, ?, ?, ?)";
        Object[] args = new Object[4];
        args[0] = history.getHistory_id();
        args[1] = history.getUser_id();
        args[2] = history.getKeyword();
        args[3] = history.getCreate_time();
        return jdbcTemplate.update(sql, args);
    }

    public ArrayList<History> histories(String userid){
        String sql = "select * from history where user_id = ? " +
                "ORDER BY STR_TO_DATE(create_time, '%Y-%m-%d %H:%i:%s') DESC";
        Object[] args = new Object[1];
        args[0] = userid;
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, args);
        ArrayList<History> list = new ArrayList<>();
        for (Map<String, Object> map : maps){
            History history = new History((String) map.get("history_id"), (String) map.get("keyword"),
                    (String) map.get("create_time"), (String) map.get("user_id"));
            list.add(history);
        }
        System.out.println(list.toString());
        return list;
    }
}
