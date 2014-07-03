import geb.report.ScreenshotReporter
import org.openqa.selenium.firefox.FirefoxDriver

// -Dgeb.build.reportsDir=geb-repo
if (!System.getProperty("geb.build.reportsDir")) {
    reportsDir = "build/reports/geb"
}

driver = {
    new FirefoxDriver()
}

reporter = new ScreenshotReporter() {
    @Override
    protected escapeFileName(String name) {
        // name.replaceAll("[^\\w\\s-]", "_")
        name.replaceAll('[\\\\/:\\*?\\"<>\\|]', '_')
    }
}
