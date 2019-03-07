package testyLocalhost;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommentRes {

    @JsonUnwrapped
    private CommentModel commentModel;
    @JsonProperty("id")
    String id;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommentModel {
        @JsonProperty("comment")
        String resourceName;

        @JsonProperty("date")
        String resourceNamed;

    }
}
