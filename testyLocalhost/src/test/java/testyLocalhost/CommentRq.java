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
public class CommentRq {

    @JsonUnwrapped
    private CommentModel commentModel;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class CommentModel {
        @JsonProperty("comment")
        String resourceName;

        @JsonProperty("date")
        String resourceNamed;

    }
}

