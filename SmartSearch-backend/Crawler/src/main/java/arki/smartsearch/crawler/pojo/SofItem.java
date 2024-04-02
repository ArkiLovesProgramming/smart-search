package arki.smartsearch.crawler.pojo;

import java.util.ArrayList;
import java.util.Arrays;

public class SofItem {
    private String questionId;
    private String title;
    private String questionDesc;
    private ArrayList<String> answers;
    private String date;
    private String favicon;
    private String hostname;

    public String getQuestionId() {
        return questionId;
    }

    public String getTitle() {
        return title;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public String getDate() {
        return date;
    }

    public String getFavicon() {
        return favicon;
    }

    public String getHostname() {
        return hostname;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFavicon(String favicon) {
        this.favicon = favicon;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Override
    public String toString() {
        return "SofItem{" +
                "questionId='" + questionId + '\'' +
                ", title='" + title + '\'' +
                ", questionDesc='" + questionDesc + '\'' +
                ", answers=" + answers +
                ", date='" + date + '\'' +
                ", favicon='" + favicon + '\'' +
                ", hostname='" + hostname + '\'' +
                '}';
    }

    public SofItem(String questionId, String title, String questionDesc, ArrayList<String> answers, String date, String favicon, String hostname) {
        this.questionId = questionId;
        this.title = title;
        this.questionDesc = questionDesc;
        this.answers = answers;
        this.date = date;
        this.favicon = favicon;
        this.hostname = hostname;
    }

    public SofItem() {
        this.setAnswers(new ArrayList<>());
    }
}
