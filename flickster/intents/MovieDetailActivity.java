package com.dsatija.flickster.intents;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.dsatija.flickster.R;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by disha_000 on 10/16/2016.
 */
public class MovieDetailActivity extends AppCompatActivity {
    TextView tvTitle_detail;
    TextView tvOverview_detail;
    ImageView ivImage_detail;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        String path = null;
        String title = getIntent().getStringExtra("title");
        Double rating = getIntent().getDoubleExtra("rating", 0.0);
        Double popularity = getIntent().getDoubleExtra("popularity", 0.0);
        String overView = getIntent().getStringExtra("synopsis");
        tvTitle_detail = (TextView) this.findViewById(R.id.tvTitle_detail);
        tvOverview_detail = (TextView) this.findViewById(R.id.tvOverview_detail);
        ivImage_detail = (ImageView) this.findViewById(R.id.ivMovieImage_detail);
        ratingBar = (RatingBar) this.findViewById(R.id.ratingBar_detail);
        tvTitle_detail.setText(title);
        tvOverview_detail.setText(overView);
        ratingBar.setRating(rating.floatValue());
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            path = getIntent().getStringExtra("posterPath");
            // ...
        }
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            path = getIntent().getStringExtra("backdrop");
            // ...
        }
        // Picasso.with(getContext()).load(path).resize(150,300).
        //       into(viewHolder.ivImage);
        Picasso.with(this.getApplicationContext()).load(path).transform(new RoundedCornersTransformation(10, 10)).
                resize(200, 0).placeholder(R.drawable.thumbnail_placeholder)
                .error(R.drawable.thumbnail_placeholder).into(ivImage_detail);


    }
}
