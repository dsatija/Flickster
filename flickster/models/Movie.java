package com.dsatija.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by disha_000 on 10/11/2016.
 */
public class Movie {
    String posterPath;
    String backdrop_posterPath;
    String originalTitle;
    String overview;
    Double popularity;

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    Double rating;


    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.backdrop_posterPath = jsonObject.getString("backdrop_path");
        this.originalTitle = jsonObject.getString("title");
        this.overview = jsonObject.getString("overview");
        this.rating=jsonObject.getDouble("vote_average")/2;
        this.popularity=jsonObject.getDouble("popularity");
    }

    public static ArrayList<Movie> fromJsonArray(JSONArray array){
        ArrayList<Movie> results = new ArrayList<>();
        for (int x = 0;x < array.length();x++){
            try {
                results.add(new Movie(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }

    public String getBackdrop_posterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",backdrop_posterPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double pl) {
        this.popularity=pl;
    }
}
