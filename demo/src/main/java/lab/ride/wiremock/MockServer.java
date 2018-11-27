package lab.ride.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author cwz
 * @date 2018/11/27
 */
public class MockServer {
    public static void main(String[] args) throws IOException {
        WireMock.configureFor(8082);
        WireMock.removeAllMappings();

        mock("/order/1", "order");

    }

    public static void mock(String api, String file) throws IOException {
        ClassPathResource resource = new ClassPathResource("mockResponse/" + file + ".txt");
        String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8"), "\n");
        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(api)).willReturn(WireMock.aResponse().withBody(content).withStatus(200)));
    }
}
