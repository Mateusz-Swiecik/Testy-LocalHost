package testyLocalhost;

import feign.HeaderMap;
import feign.Param;
import feign.RequestLine;

import java.util.Map;
public interface Interface {
    @RequestLine("POST /")
    CommentRes postComment(@HeaderMap Map<String, Object> headerMap, CommentRq commentRes);
    @RequestLine("DELETE /{id}")
    CommentRes deleteComment (@HeaderMap Map<String, Object> headerMap, @Param("id")String id) ;
    @RequestLine("PUT /{id}")
    CommentRes updateComment(@HeaderMap Map<String, Object> headerMap, CommentRes commentRes, @Param("id")String id);
    @RequestLine("GET /{id}")
    CommentRes getCommentById(@HeaderMap Map<String, Object> headerMap, @Param("id")String id);

}
