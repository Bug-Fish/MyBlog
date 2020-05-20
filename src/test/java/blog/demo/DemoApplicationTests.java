package blog.demo;

import blog.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        User user = new User();
        String md5Password = DigestUtils.md5DigestAsHex("11111".getBytes());
        System.out.println(md5Password);
    }

}
