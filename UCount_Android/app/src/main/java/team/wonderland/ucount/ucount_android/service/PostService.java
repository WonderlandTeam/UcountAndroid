package team.wonderland.ucount.ucount_android.service;

import org.androidannotations.rest.spring.annotations.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import team.wonderland.ucount.ucount_android.exception.MyResponseErrorHandler;
import team.wonderland.ucount.ucount_android.json.PostAddJson;
import team.wonderland.ucount.ucount_android.json.PostInfoJson;
import team.wonderland.ucount.ucount_android.json.PostReplyAddJson;
import team.wonderland.ucount.ucount_android.json.PostReplyJson;
import team.wonderland.ucount.ucount_android.util.RestAPI;

import java.util.List;
import java.util.Map;

/**
 * 帖子管理
 * Created by CLL on 17/8/18.
 */
@Rest(rootUrl = RestAPI.URL, converters = {FormHttpMessageConverter.class, MappingJackson2HttpMessageConverter.class}, responseErrorHandler = MyResponseErrorHandler.class)
public interface PostService {

    /**
     * 根据筛选条件获取帖子(筛选参数？)
     *
     * @return ic_post
     */
    @Get("/posts?username={username}&page={page}&size={size}&sort={sort},{direct}")
    List<PostInfoJson> getPosts(@Path String username, @Path int page, @Path int size, @Path String sort, @Path String direct);

    /**
     * 获取单个帖子信息
     *
     * @param post_id 帖子id
     * @return 帖子信息vo
     */
    @Get("/posts/{post_id}?username={username}")
    PostInfoJson getPostInfo(@Path Long post_id, @Path String username);

    /**
     * 用户分享帖子
     *
     * @param postAddJson 帖子发布信息vo
     * @return 帖子id
     */
    @Post("/posts")
    Long addPost(@Body PostAddJson postAddJson);

    /**
     * 获取用户分享所有帖子
     *
     * @param username 用户id
     * @return 帖子信息列表
     */
    @Get("/posts/release?username={username}")
    List<PostInfoJson> getPostsSharedByUser(@Path String username);

    /**
     * 用户收藏原贴
     *
     * @param post_id
     * @param username
     * @return
     */
    @Post("/posts/{post_id}/collections?username={username}")
    void collectPost(@Path Long post_id, @Path String username);

    /**
     * 用户取消收藏
     *
     * @param post_id
     * @param username
     * @return
     */
    @Delete("/posts/{post_id}/collections?username={username}")
    void deleteCollection(@Path Long post_id, @Path String username);

    /**
     * 获取用户收藏
     *
     * @param username 用户id
     * @return 帖子信息列表
     */
    @Get("/posts/collections?username={username}")
    List<PostInfoJson> getPostsCollectedByUser(@Path String username);

    /**
     * 称赞帖子
     *
     * @param username 用户id
     * @param post_id  帖子id
     */
    @Post("/posts/{post_id}/praises?username={username}")
    void praisePost(@Path Long post_id, @Path String username);

    /**
     * 取消称赞
     *
     * @param username 用户id
     * @param post_id  帖子id
     */
    //TODO: username是不是String不是Long类型
    @Delete("/posts/{post_id}/praises?username={username}")
    void cancelPraisePost(@Path Long post_id, @Path String username);

    /**
     * 用户点赞帖子回复
     *
     * @param reply_id
     * @param username
     * @return
     */
    @Post("/posts/replies/{reply_id}/praises?username={username}")
    void praisePostReply(@Path Long reply_id, @Path String username);

    /**
     * 用户取消帖子回复点赞
     *
     * @param reply_id
     * @param username
     * @return
     */
    @Delete("/posts/replies/{reply_id}/praises?username={username}")
    void cancelPraisePostReply(@Path Long reply_id, @Path String username);

    /**
     * 回复帖子
     *
     * @param post_id       帖子id
     * @param postReplyAddJson 帖子回复信息
     */
    //TODO: 这里是不是应该是PostReplyAddJson不是PostReplyJson
    @Post("/posts/{post_id}/replies")
    Long replyPost(@Path Long post_id, @Body PostReplyAddJson postReplyAddJson);

    /**
     * 获取帖子回复信息
     *
     * @param reply_id 回复id
     * @return 帖子回复信息列表
     */
    @Get("/posts/replies/{reply_id}?username={username}")
    PostReplyJson getPostReply(@Path Long reply_id, @Path String username);

    /**
     * @param post_id
     * @return
     */
    @Get("/posts/{post_id}/replies?username={username}")
    List<PostReplyJson> getPostReplies(@Path Long post_id, @Path String username);

}
