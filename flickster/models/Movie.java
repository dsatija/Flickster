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
    String originalTitle;
    String overview;


    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("title");
        this.overview = jsonObject.getString("overview");
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

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }
}
