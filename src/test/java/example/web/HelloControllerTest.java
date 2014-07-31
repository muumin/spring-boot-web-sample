package example.web;

import example.Application;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
public class HelloControllerTest {
    @Rule
    public TestName tn = new TestName();

    @Resource
    private WebApplicationContext webApplicationContext;

    @Value("${local.server.port}")
    private int port;

    private WebDriver driver;
    private Path captureDirectory = Paths.get("build/reports/selenium/");
    private int num = 0;

    @Before
    public void before() throws IOException{
        driver = new FirefoxDriver();
        Files.createDirectories(captureDirectory);
    }

    @After
    public void after() {
        driver.quit();
    }

    @Test
    public void モックテスト() throws Exception {
        HelloController controller = new HelloController();
        Model model = Mockito.mock(Model.class);

        assertThat(controller.home("yamato", model), is("hello"));
        // 引数のModelのaddAttribute()がyamatoで一回呼ばれている事を確認
        verify(model, times(1)).addAttribute("name", "yamato");
    }

    @Test
    public void インスタンス取得テスト() throws Exception {
        HelloController bean = webApplicationContext.getBean("helloController", HelloController.class);
        Model model = Mockito.mock(Model.class);

        assertThat(bean.home("yamato", model), is("hello"));
        verify(model, times(1)).addAttribute("name", "yamato");
    }

    @Test
    public void 画面テスト() throws Exception {
        String evidenceName = this.getClass().getName() + "_" + tn.getMethodName();
        String[][] param = new String[][]{
                // inputWord, expectWord
                {"groovy", "Hello, groovy!"},
                {"google", "Hello, google!"},
                {"山田", "Hello, 山田!"}
        };

        for (String[] str : param) {
            operation(evidenceName, str[0], str[1]);
        }
    }

    private void operation(String evidenceName, String inputWord, String expectWord) throws Exception {
        driver.get(String.format("http://localhost:%d", port));
        driver.findElement(By.id("name-text")).sendKeys(inputWord);
        report(evidenceName);

        assertThat(driver.findElement(By.id("name-text")).getAttribute("value"), is(inputWord));

        driver.findElement(By.id("name-btn")).click();
        assertThat(driver.findElement(By.id("name-text")).getAttribute("value"), is(""));
        assertThat(driver.findElement(By.tagName("h1")).getText(), is(expectWord));
        report(evidenceName);
    }

    private void report(String fileName) throws Exception {
        Path capture = captureDirectory.resolve(fileName + "_" + String.format("%1$03d", ++num) + ".png");
        Files.write(capture, ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
    }
}
