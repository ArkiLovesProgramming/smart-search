package arki.smartsearch.sssearchserver.controllor;

import arki.smartsearch.common.contant.Constant;
import arki.smartsearch.common.pojo.User;
import arki.smartsearch.sssearchserver.service.JWTService;
import arki.smartsearch.sssearchserver.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;

@RestController
@RequestMapping(Constant.PRIFFIX_USER)
@CrossOrigin
@Api(value = "This is APIs regarding to authorization and authentication")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "Create a new user")
    @PostMapping("/create")
    public int createUser(@RequestBody HashMap<String, String> map) throws SQLException {
        return userService.createUser(map.get("username"), map.get("password"));
    }

    @ApiOperation(value = "login", notes = "a token will be returned to caller")
    @PostMapping("/login")
    public HashMap login(@RequestBody HashMap<String, String> rmap) throws SQLException {
        String username = rmap.get("username");
        String password = rmap.get("password");
        HashMap map = new HashMap<>();
        User user = userService.isUser(username, password);
        if(user != null){
            String token = JWTService.getJWT(user);
            map.put("token", token);
            map.put("username", user.getUsername());
            return map;
        }
        map.put("msg", "incorrect username or password");
        map.put("errorcode", "1");
        return map;
    }

    @ApiOperation(value = "This is formally for registration of new user", notes = "repeated account will be" +
            "checked before creating the account")
    @PostMapping("/signup")
    public HashMap signup(@RequestBody HashMap<String, String> rmap) throws SQLException {
        String username = rmap.get("username");
        String password = rmap.get("password");
        HashMap map = new HashMap<>();
        User user = userService.isUser(username, password);
        if(user == null){
            if (userService.createUser(username, password) == 1){
                map.put("msg", "sign successfully");
                map.put("code", "0");
            }
            return map;
        }
        map.put("msg", "account exists!");
        map.put("code", "1");
        return map;
    }
}
