package arki.smartsearch.sssearchserver.service;


import arki.smartsearch.sssearchserver.utility.AWSUtility;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class NlpService {
    public HashMap<String, Float> detectSentiment(String langCode, String text){
        HashMap map = AWSUtility.detectSentiments(langCode, text);
        return map;
    }
}
