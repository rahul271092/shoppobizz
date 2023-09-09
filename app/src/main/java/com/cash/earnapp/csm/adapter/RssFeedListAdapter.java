package com.cash.earnapp.csm.adapter;

import static com.cash.earnapp.helper.Constatnt.WHEEL_URL;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cash.earnapp.R;
import com.cash.earnapp.csm.fragment.NewsFragment;
import com.cash.earnapp.csm.model.RssFeedModel;
import com.cash.earnapp.csm.topsheet.Dialog_Reward;

import java.util.List;

public class RssFeedListAdapter extends RecyclerView.Adapter<RssFeedListAdapter.FeedModelViewHolder> {

    private List<RssFeedModel> mRssFeedModels;
    private Context context;
    String Tag;
    public RssFeedListAdapter(List<RssFeedModel> mRssFeedModels,Context context)
    {
        Log.e(Tag,"Rss Feed collection"+mRssFeedModels.size());
        this.mRssFeedModels=mRssFeedModels;
        this.context=context;
    }

    @NonNull
    @Override
    public FeedModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_main_news, parent, false);
        FeedModelViewHolder holder = new FeedModelViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeedModelViewHolder holder, int position) {
        RssFeedModel rssFeedModel=mRssFeedModels.get(position);
        holder.fTitle.setText(rssFeedModel.title);

        String description = rssFeedModel.getDescription();
        if (description.length() > 300) {
            description = description.substring(0, 300);
        }
        holder.fDescription.setText(HtmlCompat.fromHtml(description, 0));


        Glide.with(context).load(rssFeedModel.getImageUrl())
                .apply(new RequestOptions().placeholder(R.drawable.loading))
                .into(holder.fImageView);


        holder.fNewsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
                NewsFragment newFragment = new NewsFragment();
                Bundle args = new Bundle();
                args.putString("title",rssFeedModel.getTitle());
                args.putString("image", rssFeedModel.getImageUrl());
                args.putString("description", rssFeedModel.getDescription());
                newFragment.setArguments(args);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mRssFeedModels.size();
    }


    public class FeedModelViewHolder extends RecyclerView.ViewHolder{

        TextView fTitle;
        TextView fDescription;
        ImageView fImageView;

        CardView fNewsCardView;

        public FeedModelViewHolder(@NonNull View itemView) {
            super(itemView);
            fTitle=itemView.findViewById(R.id.fragment_main_news_titleTextview);
            fImageView=itemView.findViewById(R.id.fragment_main_news_imageView);
            fDescription=itemView.findViewById(R.id.fragment_main_news_description_titleTextview);
            fNewsCardView=itemView.findViewById(R.id.fragment_main_news_list_cardview);
        }
    }
}
