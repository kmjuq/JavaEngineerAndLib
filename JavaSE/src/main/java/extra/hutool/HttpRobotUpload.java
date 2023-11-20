package extra.hutool;

import cn.hutool.http.HttpUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2021/1/19 14:33
 */
public class HttpRobotUpload {

    public static void main(String[] args) throws IOException {
        final String access_token = "1e32km1bx82XsQ31XJFNc4uWh6qH4ShxrczT0hOkrL74FCLRQv";
        final String sysNum = "3000000";

        final String clientResBody = HttpUtil.createGet("http://v4.faqrobot.net/servlet/apichat/v4")
                .form("access_token", access_token)
                .form("sourceId", 2)
                .form("clientId", "apikmj")
                .form("s", "ss")
                .form("sysNum", sysNum)
                .form("userId", "kmj")
                .form("original", true).execute().body();

        final File file = new File("/Users/kmj/WorkSpace/git/study_/qrcode.png");

        System.out.println(HttpUtil.createPost("http://v4.faqrobot.net/servlet/apichat/v4")
                .form("s", "uf")
                .form("access_token", access_token)
                .form("clientId", "apikmj")
                .form("sysNum", sysNum)
                .form("file", file).execute().body());
    }

    @Test
    public void demo1() {
        String appId = "3MNI3xyt0QrHcMWI4H";
        String secret = "qaDu7N8ZMz8F5BEA45C5";
        final String tokenResBody = HttpUtil.createGet("http://v4.faqrobot.net/token/getToken")
                .form("appId", appId)
                .form("secret", secret).execute().body();
        System.out.println(tokenResBody);
    }

}
