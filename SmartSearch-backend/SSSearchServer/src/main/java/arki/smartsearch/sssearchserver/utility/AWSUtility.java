package arki.smartsearch.sssearchserver.utility;


import com.amazonaws.secretsmanager.caching.SecretCache;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.comprehend.ComprehendClient;
import software.amazon.awssdk.services.comprehend.model.ComprehendException;
import software.amazon.awssdk.services.comprehend.model.DetectSentimentRequest;
import software.amazon.awssdk.services.comprehend.model.DetectSentimentResponse;

import java.util.HashMap;

public class AWSUtility {

    private static final SecretCache cache  = new SecretCache();

    private static final String SecretId = "arn:aws:secretsmanager:ca-central-1:866941515364:secret:rds!db-e7f5ac35-5021-45df-a9c4-7caf575429b7-Xq2NKD";
    public static void main(String[] args) {
        System.out.println(getSecret());
    }

    public static HashMap detectSentiments(String langCode, String text) {
        Region region = Region.US_EAST_1;
        ComprehendClient comClient = ComprehendClient.builder()
                .region(region)
                .build();
        System.out.println("Calling DetectSentiment");
        DetectSentimentResponse result =  detect(comClient, text, langCode);
        HashMap<String, Float> map = new HashMap<>();
        map.put("positive", result.sentimentScore().positive());
        map.put("negative", result.sentimentScore().negative());
        map.put("neutral", result.sentimentScore().neutral());
        map.put("mixed", result.sentimentScore().mixed());
        comClient.close();
        return map;
    }

    public static DetectSentimentResponse detect(ComprehendClient comClient, String text, String langCode) {
        DetectSentimentResponse detectSentimentResult = null;
        try {
            DetectSentimentRequest detectSentimentRequest = DetectSentimentRequest.builder()
                    .text(text)
                    .languageCode(langCode)
                    .build();

            detectSentimentResult = comClient.detectSentiment(detectSentimentRequest);
            System.out.println("The Neutral value is " + detectSentimentResult.sentimentScore().neutral());
        } catch (ComprehendException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        return detectSentimentResult;
    }

    public static String getSecret(){
        final String secret  = cache.getSecretString(SecretId);
        return secret;
    }


}
