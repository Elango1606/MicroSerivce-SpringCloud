package io.springboot.microservice.movieinfoservice.controller;


import io.springboot.microservice.movieinfoservice.responsemodel.Movie;
import io.springboot.microservice.movieinfoservice.responsemodel.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController()
@RequestMapping("/movies")
public class MovieResources {

    @Autowired
    public RestTemplate restTemplate;

    @Value("${apiKey}")
    private String apiKey;

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId){

        MovieSummary summary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId +"?api_key=" + apiKey,MovieSummary.class);

        return new Movie(movieId,summary.getTitle(),summary.getOverview());
    }
}
