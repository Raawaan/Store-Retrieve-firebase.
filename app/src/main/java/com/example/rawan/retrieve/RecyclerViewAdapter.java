package com.example.rawan.retrieve;
import android.app.LauncherActivity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by rawan on 6/4/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private List<UserData> listItems;
    private Context context;
    public RecyclerViewAdapter(List<UserData> listItems) {
        this.listItems = listItems;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutHolderView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.home_content,
                parent,
                false);
        return new ViewHolder(layoutHolderView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setContent(listItems.get(position));
    }
    @Override
    public int getItemCount() {
        return listItems.size();    }

public class ViewHolder extends RecyclerView.ViewHolder{
    public TextView nameView;
    public TextView emailView;
    public ImageView iimageView;


    public ViewHolder(View itemView) {
        super(itemView);
        nameView = (TextView)itemView.findViewById(R.id.nameTV);
        emailView = (TextView)itemView.findViewById(R.id.emailTV);
        iimageView = (ImageView)itemView.findViewById(R.id.imageViewc);
    }
    public void setContent(UserData l) {
        nameView.setText(l.getName());
        emailView.setText(l.getEmail());
        Picasso.get().load(l.getImage()).fit().centerCrop().into(iimageView);
    }

   }
}