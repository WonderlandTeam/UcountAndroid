package team.wonderland.ucount.ucount_android.service;

import org.androidannotations.rest.spring.annotations.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import team.wonderland.ucount.ucount_android.json.PostInfoJson;
import team.wonderland.ucount.ucount_android.json.PostReplyJson;
import team.wonderland.ucount.ucount_android.json.PostShareJson;

import java.util.List;
import java.util.Map;

/**
 * 帖子管理
 * Created by CLL on 17/8/18.
 */
@Rest(rootUrl = "localhost:8080/api/",converters = {MappingJackson2HttpMessageConverter.class},interceptors = {BasicAuthInterceptor.class})
public interface PostService {

    /**
     * 根据筛选条件获取帖子(筛选参数？)
     * @return              帖子
     */
    //TODO
    @Get("/posts?username={username}")
    public List<PostInfoJson> getPosts(@Path String username);

    /**
     * 获取单个帖子信息
     * @param post_id      帖子id
     * @return              帖子信息vo
     */
    @Get("/posts/{post_id}?username={username}")
    public Map<String, Object> getPostInfo(@Path Long post_id,@Path String username);

    /**
     * 用户分享帖子
     * @param postShareJson   帖子发布信息vo
     * @return              帖子id
     */
    @Post("/posts")
    public Map<String, Object> addPost(@Body PostShareJson postShareJson);

    /**
     * 获取用户分享所有帖子
     * @param username        用户id
     * @return              帖子信息列表
     */
    @Get("/posts/release?username={username}")
    public List<PostInfoJson> getPostsSharedByUser(@Path String username);

    /**
     * 用户收藏原贴
     * @param post_id
     * @param username
     * @return
     */
    @Post("/posts/{post_id}/collections?username={username}")
    public Map<String, Object> collectPost(@Path Long post_id,@Path String username);

    /**
     * 用户取消收藏
     * @param post_id
     * @param username
     * @return
     */
    @Delete("/posts/{post_id}/collections?username={username}")
    public Map<String, Object> deleteCollection(@Path Long post_id,@Path String username);

    /**
     * 获取用户收藏
     * @param username        用户id
     * @return              帖子信息列表
     */
    @Get("/posts/collections?username={username}")
    public List<PostInfoJson> getPostsCollectedByUser(@Path String username);

    /**
     * 称赞帖子
     * @param username       用户id
     * @param post_id       帖子id
     */
    @Post("/posts/{post_id}/praises?username={username}")
    public Map<String, Object> praisePost(@Path Long post_id,@Path String username);

    /**
     * 取消称赞
     * @param username      用户id
     * @param post_id        帖子id
     */
    @Delete("/posts/{post_id}/praises?username={username}")
    public Map<String, Object> cancelPraisePost(@Path Long post_id,@Path Long username);

    /**
     * 用户点赞帖子回复
     * @param reply_id
     * @param username
     * @return
     */
    @Post("/posts/replies/{reply_id}/praises?username={username}")
    public Map<String, Object> praisePostReply(@Path Long reply_id,@Path String username);

    /**
     * 用户取消帖子回复点赞
     * @param reply_id
     * @param username
     * @return
     */
    @Delete("/posts/replies/{reply_id}/praises?username={username}")
    public Map<String, Object> cancelPraisePostReply(@Path Long reply_id,@Path String username);

    /**
     * 回复帖子
     * @param post_id        帖子id
     * @param postReplyJson   帖子回复信息
     */
    @Post("/posts/{post_id}/replies")
    public Map<String, Object> replyPost(@Path Long post_id,@Body PostReplyJson postReplyJson);

    /**
     * 获取帖子回复信息
     * @param reply_id       回复id
     * @return              帖子回复信息列表
     */
    @Get("/posts/replies/{reply_id}")
    public Map<String,Object> getPostReply(@Path Long reply_id);

    /**
     *
     * @param post_id
     * @return
     */
    @Get("/posts/{post_id}/replies")
    public Map<String, Object> getPostReplies(@Path Long post_id);

}
