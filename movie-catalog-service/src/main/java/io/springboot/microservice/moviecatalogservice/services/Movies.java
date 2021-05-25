package io.springboot.microservice.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.springboot.microservice.moviecatalogservice.responsemodel.Catalogs;
import io.springboot.microservice.moviecatalogservice.responsemodel.Movie;
import io.springboot.microservice.moviecatalogservice.responsemodel.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Movies {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getMovieFallBack",
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000"),
                @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value = "5"),
                @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value = "50"),
                @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
            })
    public Catalogs getMovie(Rating rating) {
        Movie movie = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/" + rating.getMovieId(), Movie.class);
        return new Catalogs(movie.getName(), movie.getDescription(), rating.getRating());
    }

    public Catalogs getMovieFallBack(Rating rating){
        return new Catalogs("No movies" , "" , rating.getRating());
    }
}
