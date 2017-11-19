package team.wonderland.ucount.ucount_android.util;

/**
 * Rest API url接口常量
 * Created by huangxiao on 2017/9/7.
 */

public class RestAPI {

    public static final String PROTOCOL = "http";

    /**
     * 域名／ip地址
     * 需要修改为服务器ip
     */
    public static final String IP = "172.17.169.132";

    public static final int PORT = 8080;

    public static final String CONTEXT_PATH = "api";

    public static final String URL = PROTOCOL + "://" + IP + ":" + PORT + "/" + CONTEXT_PATH;

}
