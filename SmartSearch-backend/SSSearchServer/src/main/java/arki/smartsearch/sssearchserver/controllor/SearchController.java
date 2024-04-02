package arki.smartsearch.sssearchserver.controllor;

import arki.smartsearch.sssearchserver.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import arki.smartsearch.common.contant.Constant;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(Constant.PRIFFIX)
@CrossOrigin
@Api(value = "Search API", description = "This is for search API")
public class SearchController {

    @Autowired
    SearchService searchService;

    @ApiOperation(value = "search by keyword", notes = "This api is for searching by keyword, It will decide whether to search from certain page" +
            "if you specify fromPage parameter, It will also decide whether to record current search keyword into database by examining if token is existing.")
    @GetMapping("/keyword/{keyword}")
    public Map keyword(@PathVariable("keyword") String keyword,
                       @RequestParam(required = false, defaultValue = "0") int fromPage,
                       HttpServletRequest request) throws IOException {
        String token = request.getHeader("token");
        if (fromPage != 0){
            token = "";
        }
        return searchService.searchByKeyword(keyword, fromPage, token);
    }
    
    @ApiOperation(value = "Search keyword only try to match with title of results", notes = "This is used when you are typing the keyword, and the most similar results will pop" +
            "out bellow the input")
    @GetMapping("/keyword/title/{keyword}")
    public Map keyword_title(@PathVariable("keyword") String keyword) throws IOException {
        return searchService.searchByKeyword_title(keyword);
    }


    @GetMapping("/test")
    public String test() throws IOException {
        return "[{},]";
    }

}
