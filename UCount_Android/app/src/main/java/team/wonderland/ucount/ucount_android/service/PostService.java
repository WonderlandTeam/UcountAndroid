package team.wonderland.ucount.ucount_android.service;

import org.androidannotations.rest.spring.annotations.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import team.wonderland.ucount.ucount_android.json.PostInfoJson;
import team.wonderland.ucount.ucount_android.json.PostReplyJson;
import team.wonderland.ucount.ucount_android.json.PostShareJson;

import java.util.List;

/**
 * 帖子管理
 * Created by CLL on 17/8/18.
 */
@Rest(rootUrl = "",converters = {MappingJackson2HttpMessageConverter.class},interceptors = {BasicAuthInterceptor.class})
public interface PostService {

    /**
     * 根据筛选条件获取帖子(筛选参数？)
     * @return              帖子
     */
    @Get("posts")
    public List<PostInfoJson> getPosts();

    /**
     * 获取帖子信息
     * @param postId        帖子id
     * @return              帖子信息vo
     */
    @Get("posts/{post_id}")
    public PostInfoJson getPostInfo(@Path Long postId);

    /**
     * 用户分享帖子
     * @param postShareJson   帖子发布信息vo
     * @return              帖子id
     */
    @Post("posts")
    public Long addPost(@Body PostShareJson postShareJson);

    /**
     * 获取用户分享所有帖子
     * @param userId        用户id
     * @return              帖子信息列表
     */
    @Get("posts/release")
    public List<PostInfoJson> getPostsSharedByUser(@Path Long userId);

    /**
     * 获取用户收藏
     * @param userId        用户id
     * @return              帖子信息列表
     */
    @Get("posts/collections")
    public List<PostInfoJson> getPostsCollectedByUser(@Path Long userId);

    /**
     * 称赞帖子
     * @param userId        用户id
     * @param postId        帖子id
     */
    @Post("/posts/{post_id}/praises")
    public void praisePost(@Path Long userId,@Path Long postId);

    /**
     * 取消称赞
     * @param userId        用户id
     * @param postId        帖子id
     */
    @Delete("/posts/{post_id}/praises")
    public void cancelPraisePost(@Path Long userId, @Path Long postId);

    /**
     * 回复帖子
     * @param postId        帖子id
     * @param postReplyJson   帖子回复信息
     */
    @Post("/posts/{post_id}/replies")
    public void replyPost(@Path Long postId,@Body PostReplyJson postReplyJson);

    /**
     * 获取帖子回复信息
     * @param postId        帖子id
     * @return              帖子回复信息列表
     */
    @Get("/posts/{post_id}/replies")
    public List<PostReplyJson> getPostReplies(@Path Long postId);
}
