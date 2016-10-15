package com.dsatija.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsatija.flickster.R;
import com.dsatija.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by disha_000 on 10/11/2016.
 */
public class MovieArrayAdapter extends ArrayAdapter<Movie> {


    private static class ViewHolder{
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivImage;
    }
    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context,android.R.layout.simple_list_item_1,movies);
    }
    public View getView(int position, View convertView, ViewGroup parent){

        Movie movie = getItem(position);
        String path = null;
        ViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie,parent,false);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
            viewHolder.ivImage.setImageResource(0);
            convertView.setTag(viewHolder);

        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        //ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
       // ivImage.setImageResource(0);
      //  TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
       // TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);

        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());

        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            path = movie.getPosterPath();
            // ...
        }
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            path = movie.getBackdrop_posterPath();
            // ...
        }
       // Picasso.with(getContext()).load(path).resize(150,300).
         //       into(viewHolder.ivImage);

        Picasso.with(getContext()).load(path).transform(new RoundedCornersTransformation(10,10)).
                resize(175,300).into(viewHolder.ivImage);

        return convertView;

    }


}
