package com.dsatija.flickster.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dsatija.flickster.R;
import com.dsatija.flickster.adapters.MovieArrayAdapter;
import com.dsatija.flickster.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

// In this case, the fragment displays simple text based on the page
public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    ArrayList<Movie> movies;
    MovieArrayAdapter movieAdapter;
    ListView lvItems;
    private SwipeRefreshLayout swipeContainer;


    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        lvItems = (ListView) view.findViewById(R.id.lvMovies);
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchTimelineAsync(0);
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        movies = new ArrayList<>();
        movieAdapter = new MovieArrayAdapter(this.getContext(),movies);
        lvItems.setAdapter(movieAdapter);
        String url;
        switch (mPage){
            case 0:
                url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18" +
                    "f5cb106bfe4cc1f83ad8ed";
                break;
            case 1:
                url = "https://api.themoviedb.org/3/movie/popular?api_key=a07e22bc18" +
                        "f5cb106bfe4cc1f83ad8ed";
                break;
            case 2:
                url = "https://api.themoviedb.org/3/movie/upcoming?api_key=a07e22bc18" +
                        "f5cb106bfe4cc1f83ad8ed";
                break;
            default:
                url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18" +
                        "f5cb106bfe4cc1f83ad8ed";

        }

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // super.onSuccess(statusCode, headers, response);
                JSONArray movieJsonResults = null;
                try {
                    movieJsonResults = response.getJSONArray("results");
                    //movies = Movie.fromJsonArray(movieJsonResults);
                    movies.addAll(Movie.fromJsonArray(movieJsonResults));
                    movieAdapter.notifyDataSetChanged();
                    Log.d("DEBUG",movies.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
        return view;
    }

    private void fetchTimelineAsync(int i) {
        movies = new ArrayList<>();
        movieAdapter = new MovieArrayAdapter(this.getContext(),movies);
        lvItems.setAdapter(movieAdapter);
        String url;
        switch (mPage){
            case 0:
                url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18" +
                        "f5cb106bfe4cc1f83ad8ed";
                break;
            case 1:
                url = "https://api.themoviedb.org/3/movie/popular?api_key=a07e22bc18" +
                        "f5cb106bfe4cc1f83ad8ed";
                break;
            case 2:
                url = "https://api.themoviedb.org/3/movie/upcoming?api_key=a07e22bc18" +
                        "f5cb106bfe4cc1f83ad8ed";
                break;
            default:
                url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18" +
                        "f5cb106bfe4cc1f83ad8ed";

        }

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // super.onSuccess(statusCode, headers, response);
                JSONArray movieJsonResults = null;
                try {
                    movieJsonResults = response.getJSONArray("results");
                    //movies = Movie.fromJsonArray(movieJsonResults);
                    movies.addAll(Movie.fromJsonArray(movieJsonResults));
                    movieAdapter.notifyDataSetChanged();
                    Log.d("DEBUG",movies.toString());
                    swipeContainer.setRefreshing(false);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}