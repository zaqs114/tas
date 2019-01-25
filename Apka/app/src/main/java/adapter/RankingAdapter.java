package adapter;


import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.tasjava.yoza.tastest.R;

import java.util.ArrayList;

import data.model.Games;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.RankingViewHolder> {

    private static final String TAG = "RankingAdapter";
    private ArrayList<Games> dataList;
    public View view;


    public RankingAdapter(ArrayList<Games> dataList) {
        this.dataList = dataList;
    }

    @Override
    public RankingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_ranking, parent, false);
        return new RankingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RankingViewHolder holder, final int position) {

        final String gameScore = dataList.get(position).getScore();
        final String gameTitle = dataList.get(position).getTitle();

        holder.gametitle.setText(gameTitle);
        holder.score.setText(gameScore);


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class RankingViewHolder extends RecyclerView.ViewHolder {

        TextView gametitle, score;

        RankingViewHolder(View itemView) {
            super(itemView);


            gametitle =  itemView.findViewById(R.id.gametitle);
            score =  itemView.findViewById(R.id.gamescore);
        }
    }
}