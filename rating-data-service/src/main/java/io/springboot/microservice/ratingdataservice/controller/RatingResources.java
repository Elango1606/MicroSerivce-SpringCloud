package io.springboot.microservice.ratingdataservice.controller;

import io.springboot.microservice.ratingdataservice.responsemodel.Rating;
import io.springboot.microservice.ratingdataservice.responsemodel.RatingModelMain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResources {

    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating("Elango",4);
    }

    @GetMapping("users/{userId}")
    public RatingModelMain getUserRating(@PathVariable("userId") String userId){

        RatingModelMain main = new RatingModelMain();
        main.initData(userId);

        return main;
    }

}
