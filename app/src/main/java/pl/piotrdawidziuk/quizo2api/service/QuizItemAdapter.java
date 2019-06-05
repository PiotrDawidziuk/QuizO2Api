package pl.piotrdawidziuk.quizo2api.service;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pl.piotrdawidziuk.quizo2api.R;
import pl.piotrdawidziuk.quizo2api.model.QuizListItem;

public class QuizItemAdapter extends RecyclerView.Adapter<QuizItemAdapter.ViewHolder> {

    private List<QuizListItem> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public QuizItemAdapter(Context context, List<QuizListItem> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.quiz_list_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        QuizListItem quizItem = mData.get(position);

        String id =quizItem.getId();
        String title = quizItem.getTitle();

        holder.idTextView.setText(id);
        holder.titleTextView.setText(title);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView idTextView;
        TextView titleTextView;


        ViewHolder(View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.text_view_id);
            titleTextView = itemView.findViewById(R.id.text_view_title);
            //itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View view) {
//            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
//        }
    }

    // convenience method for getting data at click position
    QuizListItem getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

