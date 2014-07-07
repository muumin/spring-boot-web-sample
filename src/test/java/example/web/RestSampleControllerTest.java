package example.web;

import example.Application;
import example.domain.User;
import example.service.UserService;
import net.arnx.jsonic.JSON;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
public class RestSampleControllerTest {
    @Resource
    private WebApplicationContext webApplicationContext;

    @Value("${local.server.port}")
    private int port;

    List<User> userList;

    @Before
    public void before() {
        userList = new ArrayList<>();
        User user1 = new User();
        user1.setName("tarou");
        user1.setAge(18);
        userList.add(user1);

        User user2 = new User();
        user2.setName("hanako");
        user2.setAge(21);
        userList.add(user2);
    }

    @Test
    public void モックテスト() throws Exception {
        RestSampleController controller = new RestSampleController();

        UserService userService = mock(UserService.class);

        // userServiceにモックを設定
        Whitebox.setInternalState(controller, "userService", userService);

        // モックのgetUser()が呼ばれたらリストを返す
        when(userService.getUser()).thenReturn(userList);

        List<User> ret = controller.home();
        for (int i = 0; i < ret.size(); i++) {
            assertThat(ret.get(i).getName(), is(userList.get(i).getName()));
            assertThat(ret.get(i).getAge(), is(userList.get(i).getAge()));
        }

        // getUser()が一回呼ばれている事を確認
        verify(userService, times(1)).getUser();
    }

    @Test
    public void インスタンス取得テスト() throws Exception {
        RestSampleController bean = webApplicationContext.getBean("restSampleController", RestSampleController.class);

        List<User> ret = bean.home();
        for (int i = 0; i < ret.size(); i++) {
            assertThat(ret.get(i).getName(), is(userList.get(i).getName()));
            assertThat(ret.get(i).getAge(), is(userList.get(i).getAge()));
        }
    }

    @Test
    public void HTTPアクセステスト() throws Exception {
        ResponseEntity<String> entity = new TestRestTemplate().getForEntity(String.format("http://localhost:%d/rest", port), String.class);
        assertThat(entity.getStatusCode(), is(HttpStatus.OK));

        User[] ret = JSON.decode(entity.getBody(), User[].class);
        for (int i = 0; i < ret.length; i++) {
            assertThat(ret[i].getName(), is(userList.get(i).getName()));
            assertThat(ret[i].getAge(), is(userList.get(i).getAge()));
        }

        // 結果を文字列で比較
        // ただし結果に時間など可変な値が入るとこれだと厳しいと思う
        assertThat(entity.getBody(), is("[{\"name\":\"tarou\",\"age\":18},{\"name\":\"hanako\",\"age\":21}]"));
    }
}