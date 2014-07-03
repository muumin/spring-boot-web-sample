package example

import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

class SpringBootRunner {
    def getConfigurableApplicationContext() {
        Future<ConfigurableApplicationContext> future = Executors
                .newSingleThreadExecutor().submit(
                new Callable() {
                    @Override
                    public ConfigurableApplicationContext call() throws Exception {
                        return SpringApplication.run(Application.class)
                    }
                })
        return future.get(60, TimeUnit.SECONDS)
    }
}
