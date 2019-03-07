package testyLocalhost;

public class UpdateModel {

    public CommentRes RequestUpdateBody() {

        return CommentRes.builder()
                .commentModel(new CommentRes.CommentModel(
                        "update",
                        "updatee"
                ))
                .build();
    }
}
