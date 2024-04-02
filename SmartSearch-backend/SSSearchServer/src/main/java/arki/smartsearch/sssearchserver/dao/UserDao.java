package arki.smartsearch.sssearchserver.dao;

import arki.smartsearch.common.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User isUser(String username, String password) throws SQLException {
        //Connection connection =   dataSource.getConnection();//这段代码其实没有意义，JdbcTemplate已经从容器中获取了 datasource，并自动完成链接和断联的操作
        String sql = "select * from user where username = ? and password = ?";
        Object[] args = new Object[2];
        args[0] = username;
        args[1] = password;
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, args);
        System.out.println(maps);
        //关闭连接
        if (maps.size()>0) {
            User user = new User((String) maps.get(0).get("id"), (String) maps.get(0).get("username"), (String) maps.get(0).get("password"), (String) maps.get(0).get("history_id"));
            return user;
        }
        return null;
    }

    public int createUser(String username, String password) throws SQLException {
        String userid = UUID.randomUUID().toString();
        String history_id = UUID.randomUUID().toString();
        String sql = "insert into user (id, username, password, history_id) values(?, ?, ?, ?)";
        Object[] args = new Object[4];
        args[0] = userid;
        args[1] = username;
        args[2] = password;
        args[3] = history_id;
        int lines = jdbcTemplate.update(sql, args);
        return lines;
    }
}
