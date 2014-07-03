package example

import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

@Mixin(SpringBootRunner)
class SpringBootSpecification extends Specification {
    @Shared
    @AutoCleanup
    def context

    void setupSpec() {
        context = getConfigurableApplicationContext()
    }
}
