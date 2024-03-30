package arki.smartsearch.sssearchserver.controllor;


import arki.smartsearch.common.contant.Constant;
import arki.smartsearch.common.pojo.History;
import arki.smartsearch.sssearchserver.service.HistroyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Api("The APIs is for search history")
@RestController
@RequestMapping(Constant.PRIFFIX_HISTORY)
@CrossOrigin
public class HistoryController {

    @Autowired
    private HistroyService histroyService;

    @ApiOperation(value = "Get the histories by token")
    @GetMapping("/get")
    public ArrayList<History> getHistories(HttpServletRequest request){
        String token = request.getHeader("token");
        return histroyService.histories(token);
    }
}
