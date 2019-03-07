package testyLocalhost;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.junit.Assert;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Stepdefs {

    private static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Stepdefs.class);
    Date now = new Date();
    String id = null;

    @Given("i check if comment exist")
    public void checkPost() throws IOException {
        final URL url = new URL("http://localhost:3000/comments/" + id);
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        int responseCode = huc.getResponseCode();
        if (responseCode == 200) {
            System.out.println("comment exist");
        } else {
            System.out.println("comment doesn't exist");
        }
    }

    Interface client = Feign.builder()
            .client(new OkHttpClient())
            .encoder(new JacksonEncoder())
            .decoder(new JacksonDecoder())
            .logger(new Slf4jLogger(Interface.class))
            .logLevel(Logger.Level.FULL)
            .target(Interface.class, "http://localhost:3000/comments/");

    @When("i try to add a comment")
    public void postComment() {
        Map<String, Object> headermap = new HashMap<>();
        headermap.put("Content-Type", "application/json");
        CommentBody requestComment = new CommentBody();
        CommentRes commentRes = client.postComment(headermap, requestComment.RequestCommentBody());
        id = commentRes.getId();
        LOGGER.info("Created: " + DateFormat.getInstance().format(now));
    }

    @When("i again check if comment exist")
    public void checkCommentAgain() throws IOException {
        final URL url = new URL(("http://localhost:3000/comments/" + id));
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        int responseCode = huc.getResponseCode();
        if (responseCode == 200) {
            System.out.println("comment exist");
        } else {
            Assert.fail("comment doesn't exist");
        }
    }

    @When("^i try to update comment")
    public void updatePost() {
        Map<String, Object> headermap = new HashMap<>();
        headermap.put("Content-Type", "application/json");
        UpdateModel updateModel = new UpdateModel();
        client.updateComment(headermap, updateModel.RequestUpdateBody(), id);
        LOGGER.info("Updated: " + DateFormat.getInstance().format(now));
    }

    @When("when i try to delete comment")
    public void deleteComment() throws InterruptedException {
        Map<String, Object> headermap = new HashMap<>();
        headermap.put("Content-Type", "application/json");
        Thread.sleep(4000);
        client.deleteComment(headermap, id);
    }

    @When("i check if comment was deleted")
    public void checkIfPostWasDeleted() throws IOException {
        final URL url = new URL(("http://localhost:3000/comments/" + id));
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        int responseCode = huc.getResponseCode();
        if (responseCode == 404) {
            System.out.println("deleted");
        } else {
            Assert.fail("not deleted");
        }
    }
}