package activity.physical.example.com.josip.physicalactivity.pomocneKlase;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import activity.physical.example.com.josip.physicalactivity.model.SummaryActivity;
import activity.physical.example.com.josip.physicalactivity.model.SummaryBiking;
import activity.physical.example.com.josip.physicalactivity.model.SummaryRunning;
import activity.physical.example.com.josip.physicalactivity.model.WalkingStatistika;

/**
 * Created by Korisnik on 17.3.2018..
 */

public class PosaljiUkupnePodatke {
    public PosaljiUkupnePodatke() {
    }
    public void posalji(SummaryRunning running) {
        final SummaryRunning run = running;
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {


                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(new MediaType("application", "json"));
                    HttpEntity<SummaryRunning> request = new HttpEntity<SummaryRunning>(run, headers);
                    RestTemplate restTemplate = new RestTemplate();
                    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
                    converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
                    restTemplate.getMessageConverters().add(converter);
                    try {
                        ResponseEntity<SummaryRunning> response = restTemplate.exchange("http://10.0.2.2:8080/physical/run", HttpMethod.POST, request, SummaryRunning.class);
                        SummaryRunning result = response.getBody();
                        System.out.println(result.toString());
                    } catch (HttpClientErrorException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }

    public void posalji(WalkingStatistika walk) {
        final WalkingStatistika walked = walk;
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {


                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(new MediaType("application", "json"));
                    HttpEntity<WalkingStatistika> request = new HttpEntity<WalkingStatistika>(walked, headers);
                    RestTemplate restTemplate = new RestTemplate();
                    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
                    converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
                    restTemplate.getMessageConverters().add(converter);
                    try {
                        ResponseEntity<WalkingStatistika> response = restTemplate.exchange("http://10.0.2.2:8080/physical/walk", HttpMethod.POST, request, WalkingStatistika.class);
                        WalkingStatistika result = response.getBody();
                        System.out.println(result.toString());
                    } catch (HttpClientErrorException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }


    public void posalji(SummaryBiking biking) {
        final SummaryBiking summaryBiking = biking;
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {


                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(new MediaType("application", "json"));
                    HttpEntity<SummaryBiking> request = new HttpEntity<SummaryBiking>(summaryBiking, headers);
                    RestTemplate restTemplate = new RestTemplate();
                    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
                    converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
                    restTemplate.getMessageConverters().add(converter);
                    try {
                        ResponseEntity<SummaryBiking> response = restTemplate.exchange("http://10.0.2.2:8080/physical/bike", HttpMethod.POST, request, SummaryBiking.class);
                        SummaryBiking result = response.getBody();
                        System.out.println(result.toString());
                    } catch (HttpClientErrorException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }
    public void posalji(SummaryActivity summary) {
        final SummaryActivity summaryActivity = summary;
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {


                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(new MediaType("application", "json"));
                    HttpEntity<SummaryActivity> request = new HttpEntity<SummaryActivity>(summaryActivity, headers);
                    RestTemplate restTemplate = new RestTemplate();
                    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
                    converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
                    restTemplate.getMessageConverters().add(converter);
                    try {
                        ResponseEntity<SummaryActivity> response = restTemplate.exchange("http://10.0.2.2:8080/physical/sumary", HttpMethod.POST, request, SummaryActivity.class);
                        SummaryActivity result = response.getBody();
                        System.out.println(result.toString());
                    } catch (HttpClientErrorException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }
}
