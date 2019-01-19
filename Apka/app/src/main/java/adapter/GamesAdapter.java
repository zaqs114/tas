package adapter;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tasjava.yoza.tastest.GameActivity;
import com.tasjava.yoza.tastest.R;

import java.util.ArrayList;

import data.model.Games;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.GamesViewHolder> {

    public GamesAdapter activity;
    private static final String TAG = "GamesAdapter";
    private ArrayList<Games> dataList;
    public View view;
    public ClipData.Item currentItem;
    private Context mContext;


    public GamesAdapter(ArrayList<Games> dataList) {
        this.dataList = dataList;
    }

    @Override
    public GamesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row, parent, false);
        return new GamesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GamesViewHolder holder, final int position) {

//        int gameId = dataList.get(position).getGameid();
        final String gameTitle = dataList.get(position).getTitle();
        String gameIcon = dataList.get(position).getIcon();
//        String gameDescription = dataList.get(position).getDescription();
//        String gameScore = dataList.get(position).getScore();
//        String gameLanuchdate = dataList.get(position).getLaunchDate();
//        String gamePublisher = dataList.get(position).getPublisher();
//        String gameGenre = dataList.get(position).getGenre();
//        String gamePlatform = dataList.get(position).getPlatform();


//        holder.gameid.setText(Integer.toString(gameId));
        holder.gametitle.setText(gameTitle);
        holder.gameicon.setText(gameIcon);

        holder.gametitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GameActivity.class);
                intent.putExtra(gameTitle, dataList.get(position).getTitle());
                mContext.startActivity(intent);
            }
        });
//        holder.gamedecription.setText(gameDescription);
//        holder.gamescore.setText(gameScore);
//        holder.gamelaunchdate.setText(gameLanuchdate);
//        holder.gamepublisher.setText(gamePublisher);
//        holder.gamegenre.setText(gameGenre);
//        holder.gameplatform.setText(gamePlatform);

//        holder.gameid.setText("test"+Integer.toString(position));
//        holder.gametitle.setText("test"+Integer.toString(position));
//        holder.gameicon.setText("test"+Integer.toString(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class GamesViewHolder extends RecyclerView.ViewHolder {

//        TextView gameid;
        TextView gametitle, gameicon, gamedecription, gamescore, gamelaunchdate, gamepublisher, gamegenre, gameplatform;

        GamesViewHolder(View itemView) {
            super(itemView);

//            gameid =  itemView.findViewById(R.id.gameid);
            gametitle =  itemView.findViewById(R.id.gametitle);
            gameicon =  itemView.findViewById(R.id.gameicon);
//            itemView.setOnClickListener();
//            gamedecription =  itemView.findViewById(R.id.gamedescription);
//            gamescore =  itemView.findViewById(R.id.gamescore);
//            gamelaunchdate =  itemView.findViewById(R.id.gamelaunchdate);
//            gamepublisher =  itemView.findViewById(R.id.gamepublisher);
//            gamegenre =  itemView.findViewById(R.id.gamegenre);
//            gameplatform =  itemView.findViewById(R.id.gameplatform);

        }
    }
}