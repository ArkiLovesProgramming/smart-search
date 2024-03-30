package arki.smartsearch.sssearchserver.controllor;


import arki.smartsearch.common.contant.Constant;
import arki.smartsearch.sssearchserver.service.NlpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Api(value = "APIs regarding the NPL")
@RestController
@RequestMapping(Constant.PRIFFIX_NLP)
@CrossOrigin
public class NlpController {

    @Autowired
    NlpService nlpService;

    @ApiOperation(value = "Used to detect the sentiment of document content", notes = "This will give you" +
            "a possibility that the target keyword can classified to positive, negative, neutral or mixed")
    @GetMapping("/detect/{text}")
    public HashMap detect(@PathVariable String text){
        return nlpService.detectSentiment("en", text);
    }

}
