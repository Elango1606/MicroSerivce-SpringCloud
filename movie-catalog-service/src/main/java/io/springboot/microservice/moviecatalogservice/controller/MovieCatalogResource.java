package io.springboot.microservice.moviecatalogservice.controller;

import io.springboot.microservice.moviecatalogservice.responsemodel.Catalogs;
import io.springboot.microservice.moviecatalogservice.responsemodel.RatingModelMain;
import io.springboot.microservice.moviecatalogservice.services.Movies;
import io.springboot.microservice.moviecatalogservice.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClient;

    @Autowired
    private RatingService rating;

    @Autowired
    private Movies movie;

    @GetMapping("/{userId}")
    public List<Catalogs> getCatalog(@PathVariable("userId") String userId){

        // Must get Result from rating service by passing user Id
        // For now we are hard Coding it
        RatingModelMain ratings = rating.getRatingModelMain(userId);

        // returning the value from rating for movies
        return ratings.getRatings().stream().map(rating -> movie.getMovie(rating)).collect(Collectors.toList());
    }

}

//            Movie movie =  webClient.build()
//                    .get()
//                    .uri("http://localhost:8082/movies/" + rating1.getMovieId())
//                    .retrieve()
//                    .bodyToMono(Movie.class)
//                    .block();
