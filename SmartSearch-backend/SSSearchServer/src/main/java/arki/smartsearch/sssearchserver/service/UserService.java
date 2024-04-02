package arki.smartsearch.sssearchserver.service;

import arki.smartsearch.common.pojo.User;
import arki.smartsearch.sssearchserver.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public int createUser(String username, String password) throws SQLException {
        return userDao.createUser(username, password);
    }

    public User isUser(String username, String password) throws SQLException {
        return userDao.isUser(username, password);
    }

}
