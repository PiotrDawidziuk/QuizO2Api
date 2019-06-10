package pl.piotrdawidziuk.quizo2api.service;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import pl.piotrdawidziuk.quizo2api.R;
import pl.piotrdawidziuk.quizo2api.model.Answer;

public class AnswersAdapter  extends RecyclerView.Adapter<AnswersAdapter.ViewHolder> {

    private List<Answer> mData;
    private LayoutInflater mInflater;
    private AnswersAdapter.ItemClickListener mClickListener;

    // data is passed into the constructor
    public AnswersAdapter(Context context, List<Answer> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public AnswersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.answers_list, parent, false);
        return new AnswersAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(AnswersAdapter.ViewHolder holder, int position) {

        Answer answer = mData.get(position);

        String text = answer.getText();
        String url = answer.getImage().getUrl();
        //MainPhoto mainPhoto = answer.getMainPhoto();

        holder.textView.setText(text);

       // holder.titleTextView.setText(title);
        Glide.with(holder.imageView.getContext())
                .load(url)
                .apply(new RequestOptions()
                        .override(ResizeImage.getWidth(),ResizeImage.getHeight()))
                .fitCenter()
                .into(holder.imageView);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;


        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.answer_text_view);
            imageView = itemView.findViewById(R.id.answer_image);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mClickListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mClickListener.onItemClick(view,position);
                        }
                    }

                }
            });
        }


    }

    // convenience method for getting data at click position
    Answer getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(AnswersAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}