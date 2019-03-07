package testyLocalhost;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.text.DateFormat;
import java.util.Date;
public class CommentBody {

    Date now = new Date();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public CommentRq RequestCommentBody() {
        return CommentRq.builder()
                .commentModel(new CommentRq.CommentModel(
                        "komentarz",
                        (DateFormat.getInstance().format(now))

                ))
                .build();
    }
}
