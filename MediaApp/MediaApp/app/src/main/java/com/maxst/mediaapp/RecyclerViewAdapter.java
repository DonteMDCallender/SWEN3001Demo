package com.maxst.mediaapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    private List<Images> mData;

    public RecyclerViewAdapter(Context mContext, List<Images> mData){
        this.mContext = mContext;
        this.mData = mData;
}

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.activity_main, parent, false);
        return new RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder,final int position) {
        holder.title.setText(mData.get(position).getName());
        holder.img.setImageResource(mData.get(position).getThubmnail());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, MainActivity.class);

                intent.putExtra("Name", mData.get(position).getName());
                intent.putExtra("Thumbnail", mData.get(position).getThubmnail());

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView img;
        CardView cardView;
        Context context;
        List<Images> imgs;

        public MyViewHolder(View itemView){
            super(itemView);

            title= (TextView) itemView.findViewById(R.id.textview);
            img = (ImageView) itemView.findViewById(R.id.imageview);
            cardView= (CardView) itemView.findViewById(R.id.card_view);
            itemView.setOnClickListener(this);
            this.context = context;
            this.imgs = imgs;
        }

        @Override
        public void onClick(View v) {

        }
    }

}


