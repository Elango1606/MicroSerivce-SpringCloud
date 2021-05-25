package io.springboot.microservice.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.springboot.microservice.moviecatalogservice.responsemodel.Rating;
import io.springboot.microservice.moviecatalogservice.responsemodel.RatingModelMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class RatingService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getRatingFallBack")
    public RatingModelMain getRatingModelMain(String userId) {
        return restTemplate.getForObject("http://rating-data-service/ratingsdata/users/" + userId, RatingModelMain.class);
    }


    private RatingModelMain getRatingFallBack(String userId){
        RatingModelMain model = new RatingModelMain();
        model.setRatings(Arrays.asList(
                new Rating("Default FallBack" , 0)
        ));
        return model;
    }
}
