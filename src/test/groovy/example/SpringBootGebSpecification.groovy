package example

import geb.spock.GebReportingSpec
import spock.lang.AutoCleanup
import spock.lang.Shared

@Mixin(SpringBootRunner)
class SpringBootGebSpecification extends GebReportingSpec {
    @Shared
    @AutoCleanup
    def context

    void setupSpec() {
        context = getConfigurableApplicationContext()
    }
}
