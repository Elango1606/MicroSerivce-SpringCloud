package io.springboot.microservice.moviecatalogservice.responsemodel;

import java.util.List;

public class RatingModelMain {

    public RatingModelMain() {
    }

    public RatingModelMain(List<Rating> ratings) {
        this.ratings = ratings;
    }

    private List<Rating> ratings ;

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
