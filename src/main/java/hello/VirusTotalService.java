package hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class VirusTotalService
{
    @Value("${virusTotalUri}")
    private  String uri;

    @Value("${apiKey}")
    private  String apiKey;

    public  FullFileReport getVirusTotalFileReport(String fileHash)
    {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri + "file/report")
                .queryParam("apikey", apiKey)
                .queryParam("resource", fileHash);

        FullFileReport fileReport = restTemplate.getForObject(builder.toUriString(), FullFileReport.class);

        return fileReport;
    }

    public  FullFileReport getVirusTotalUrlReport(String url)
    {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri + "url/report")
                .queryParam("apikey", apiKey)
                .queryParam("resource", url);

        FullFileReport urlReport = restTemplate.getForObject(builder.toUriString(), FullFileReport.class);

        return urlReport;
    }

    public  String sumbitVirusTotalUrlForScan(VirusTotalUrl url)
    {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri + "url/scan")
                .queryParam("apikey", apiKey)
                .queryParam("url", url.getUrl());

        String result = restTemplate.postForObject(builder.toUriString(), null, String.class);
        return result;
    }

}
