package arki.smartsearch.sssearchserver.service;

import arki.smartsearch.common.pojo.History;
import arki.smartsearch.common.pojo.User;
import arki.smartsearch.sssearchserver.dao.HistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.TileObserver;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Service
public class HistroyService {
    @Autowired
    HistoryDao historyDao;

    public int createHistory(String keyword, String userid){
        String history_id = String.valueOf(UUID.randomUUID());
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String create_time = format.format(date);
        History history = new History(history_id, keyword, create_time, userid);
        return historyDao.createHistory(history);
    }

    public ArrayList<History> histories(String token){
        User user = JWTService.verifyJWT(token);
        if (user == null){
            return new ArrayList<>();
        }
        return historyDao.histories(user.getId());
    }

}
