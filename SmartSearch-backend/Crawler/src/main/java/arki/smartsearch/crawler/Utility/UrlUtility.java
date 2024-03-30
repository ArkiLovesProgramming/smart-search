package arki.smartsearch.crawler.Utility;

import arki.smartsearch.common.pojo.SofItem;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import javax.swing.plaf.PanelUI;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;


@Component
public class UrlUtility {
    static String BASE_URL = "https://stackoverflow.com/";
    static String GETIDS_URL = BASE_URL + "questions?tab=Active&pagesize=50&page=";
    static String GETDETAIL_URL = BASE_URL + "questions/";

    static String cookie = "prov=f9c95946-f66a-aa91-5a67-8f247bbdbb26; OptanonAlertBoxClosed=2023-09-22T15:36:18.236Z; _so_tgt=43c6857a-588b-487c-8410-04e95ad939b8; usr=p=%5b160%7c%3bRecentActivity%3b%5d%5b10%7c50%5d; notice-cvps=1%3B1706885818162; _gid=GA1.2.209063001.1710782342; g_state={\"i_p\":1710790578760,\"i_l\":1}; notice-signup=0:0:0:1; notice-ssb=4%3B1710792786953; cf_clearance=tK1pDrd.BJhdAPf6yYlTKyYkkgWrbDvuCdAhyT.SNUA-1710797694-1.0.1.1-rM8f6qsJkcsaW7abdu3BKBtYxammu9aqTHzuymK1wCSYBs7jC5S7G_kXuNZyWiV7UoS6uIas5EE3beQ.mjJ6kQ; __cf_bm=FIeEOvY5l9GacuvM51ctwaoujWx3619_GFrJcLGrj_A-1710797733-1.0.1.1-NP3qFxF3laTQh852yECqeZOHg5Mewo5K2kl8ZpJSnRJ6NcpPxPr42dNi5SFJF76t0A6WIsEKGW4XW4akmlpBog; _ga_WCZ03SZFCQ=GS1.1.1710797694.74.1.1710798232.60.0.0; _ga=GA1.2.987991740.1695396965; __gads=ID=ab41a410fef5717e:T=1695403693:RT=1710798233:S=ALNI_MadE5c6nTdo4I9Aq5CyKSkQ_hiDMQ; __gpi=UID=00000d948754afca:T=1695403693:RT=1710798233:S=ALNI_Mbuf4raf5lpVQyE1hleKs1yPFXpeQ; __eoi=ID=1514d5ab96b3ae06:T=1707924738:RT=1710798233:S=AA-AfjYyaRzD5agoaMkYWA5hcUWe; OptanonConsent=isGpcEnabled=0&datestamp=Mon+Mar+18+2024+19%3A13%3A55+GMT-0230+(Newfoundland+Daylight+Time)&version=202312.1.0&isIABGlobal=false&hosts=&consentId=059bf193-32b4-4913-a65f-2273210d10f5&interactionCount=1&landingPath=NotLandingPage&groups=C0001%3A1%2CC0003%3A1%2CC0004%3A1%2CC0002%3A1&browserGpcFlag=0&geolocation=CA%3BNL&AwaitingReconsent=false";

    static String agent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";

    public static ArrayList<String> spiderQuestionIds(int page){
        Document document;
        ArrayList ids = new ArrayList<>();
        try {
            document = Jsoup.connect(GETIDS_URL + page)
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                    .header("Cookie", cookie)
                    .userAgent(agent)
                    .timeout(8000).ignoreContentType(true)
                    .get();
            Elements links = document.select(".s-post-summary--content-title > a");
            //Feature needed tbe done: We should filter these we have added
            for (Element link : links){
//                System.out.print(link.attr("href").split("/")[2] + "|");
                ids.add(link.attr("href").split("/")[2]);
//                System.out.println(link.text());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ids;
    }

    public static SofItem spiderQuestionId(String id){
        Document document;
        SofItem sofItem = new SofItem();
        try {
            document = Jsoup.connect(GETDETAIL_URL + id)
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                    .header("Cookie", cookie)
                    .userAgent(agent)
                    .timeout(8000).ignoreContentType(true)
                    .get();
            Element header_link = document.select("#question-header a").first();
            sofItem.setTitle(header_link.text());
            Elements contents = document.select(".s-prose.js-post-body");
            contents.select("pre, code, a, .s-table-container").remove();
            sofItem.setQuestionDesc(contents.get(0).text());
            for (int i = 1; i < contents.size(); i++){
                sofItem.getAnswers().add(contents.get(i).text());
            }
            String favicon = document.select("link[href*=\"favicon\"]").first().attr("href");
            String url = document.select("meta[property=\"og:url\"]").first().attr("content");
            String date = document.select("time[itemprop=\"dateCreated\"]").first().attr("datetime");
            sofItem.setFavicon(favicon);
            sofItem.setDate(date);
            sofItem.setHostname(url);
            sofItem.setFavicon(favicon);
            sofItem.setQuestionId(id);
        } catch (java.net.SocketTimeoutException e){
            return null;
        } catch (HttpStatusException e){
            if (e.getStatusCode() == 404){
                System.out.println(e.getUrl() + "--------" + 404);
                return null;
            }
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
        return sofItem;
    }

    public static ArrayList<SofItem> generateSofItems_proxy() throws InterruptedException {
        ArrayList<SofItem> SofItems = new ArrayList<>();
        for(int i = 151; i <= 200; i++){
            ArrayList<SofItem> SofItems1 = UrlUtility.generateSofItems(i);
            SofItems.addAll(SofItems1);
            Thread.sleep(3000);
        }
        return SofItems;
    }

    public static ArrayList<SofItem> generateSofItems(int page) throws InterruptedException {
        ArrayList<String> ids = UrlUtility.spiderQuestionIds(page);
//        ArrayList<String> ids = UrlUtility.spiderQuestionIds(2);
//        ArrayList<String> ids3 = UrlUtility.spiderQuestionIds(5);
//        ArrayList<String> ids4 = UrlUtility.spiderQuestionIds(6);
//        ids.addAll(ids2);
//        ids.addAll(ids3);
//        ids.addAll(ids4);
        ArrayList<SofItem> sofItems = new ArrayList<>();
        for (String id : ids){
            SofItem sofItem = UrlUtility.spiderQuestionId(id);
//            Thread.sleep(500);
            if (sofItem != null){
                sofItems.add(sofItem);
            }
        }
        return sofItems;
    }

    public static void main(String[] args) throws InterruptedException {
//        ArrayList<SofItem> sofItems = generateSofItems();
//        for (int i = 0; i < sofItems.size(); i++){
//            System.out.println(sofItems.get(i).getTitle());
//        }
        System.out.println(UrlUtility.spiderQuestionIds(1));
    }
}
