import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources"},
        plugin={"pretty","html:target/reports/test-report"},
        tags= "@Test",
        publish = true
)

public class APITest {
}
