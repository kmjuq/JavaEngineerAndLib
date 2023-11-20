package extra.hutool;

import cn.hutool.crypto.digest.DigestUtil;
import org.junit.jupiter.api.Test;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2019-09-20 18:49
 */
public class SecretKeyUtil {

    @Test
    public void demo1() {
        System.out.println("用户名:szyfpt");
        System.out.println("密码明文:4TGU7f1H99a8vVYtbjLAT7eYUpc47q13");
        System.out.println("密码密文:" + DigestUtil.md5Hex("4TGU7f1H99a8vVYtbjLAT7eYUpc47q13"));
    }

}
