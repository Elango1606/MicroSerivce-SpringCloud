package io.springboot.microservice.ratingdataservice.responsemodel;

import java.util.Arrays;
import java.util.List;

public class RatingModelMain {

    public RatingModelMain() {
    }

    private List<Rating> ratings;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void initData(String userId) {
        this.setUserId(userId);
        this.setRatings(Arrays.asList(
                new Rating("100", 3),
                new Rating("200", 4)
        ));
    }
}
