package arki.smartsearch.sssearchserver.service;

import arki.smartsearch.common.pojo.User;
import arki.smartsearch.sssearchserver.utility.ESUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;


@Service
public class SearchService {
    @Autowired
    private ESUtility esUtility;

    @Autowired
    private HistroyService histroyService;

    public Map searchByKeyword(String keyword, int fromPage, String token) throws IOException {
        User user = JWTService.verifyJWT(token);
        if (user != null){
            if (histroyService.createHistory(keyword, user.getId()) == 1){
                System.out.println("成功记录一条数据！来自-" + user.getUsername());
            }
        } else {
            System.out.println("未登录用户，不记录历史记录！");
        }
        return esUtility.searchByKeyword(keyword, fromPage);
    }

    public Map searchByKeyword_title(String keyword) throws IOException {
        return esUtility.searchByKeyword_title(keyword);
    }
}
