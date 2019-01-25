package adapter;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import com.tasjava.yoza.tastest.GameActivity;
import com.tasjava.yoza.tastest.ItemClickListener;
import com.tasjava.yoza.tastest.R;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import data.model.Games;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.GamesViewHolder> {

    public GamesAdapter activity;
    private static final String TAG = "GamesAdapter";
    private ArrayList<Games> dataList;
    public View view;
    public ClipData.Item currentItem;
    private Context c;
    private ItemClickListener itemClickListener;

    public GamesAdapter(Context ctx, ArrayList<Games> dataList) {

        this.dataList = dataList;
        this.c = ctx;
    }

    @Override
    public GamesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row, parent, false);
        return new GamesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GamesViewHolder holder, final int position) {

//        Glide.with(mContext)
//                .asBitmap()
//                .load(gameIcon)
//                .into(holder.gameicon)
//
        final int gameId = dataList.get(position).getGameid();
        final String gameTitle = dataList.get(position).getTitle();
        final String gameIcon = dataList.get(position).getIcon();
        final String gameDescription = dataList.get(position).getDescription();
//        String gameScore = dataList.get(position).getScore();
//        String gameLanuchdate = dataList.get(position).getLaunchDate();
//        String gamePublisher = dataList.get(position).getPublisher();
//        String gameGenre = dataList.get(position).getGenre();
//        String gamePlatform = dataList.get(position).getPlatform();

//        holder.gamescore.setText(gameScore);
//        holder.gamelaunchdate.setText(gameLanuchdate);
//        holder.gamepublisher.setText(gamePublisher);
//        holder.gamegenre.setText(gameGenre);
//        holder.gameplatform.setText(gamePlatform);
        holder.gameid.setText(Integer.toString(gameId));
        holder.gametitle.setText(gameTitle);
//        holder.gamedecription.setText(gameDescription);
//        holder.gameicon.setText(gameIcon);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Intent i = new Intent(c, GameActivity.class);
                i.putExtra("title", gameTitle);
                i.putExtra("icon", gameIcon);
                i.putExtra("id", gameId);
                c.startActivity(i);
            }
        });
//




    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class GamesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView gameid;
        CardView parent_layout;
        ImageView gameicon;
        TextView gametitle, gamedecription, gamescore, gamelaunchdate, gamepublisher, gamegenre, gameplatform;
        private ItemClickListener itemClickListener;


        GamesViewHolder(View itemView) {
            super(itemView);


            gameid =  itemView.findViewById(R.id.gameid);
            gametitle =  itemView.findViewById(R.id.gametitle);
            gameicon =  itemView.findViewById(R.id.gameicon);

            gamedecription =  itemView.findViewById(R.id.gamedescription);
//            gamescore =  itemView.findViewById(R.id.gamescore);
//            gamelaunchdate =  itemView.findViewById(R.id.gamelaunchdate);
//            gamepublisher =  itemView.findViewById(R.id.gamepublisher);
//            gamegenre =  itemView.findViewById(R.id.gamegenre);
//            gameplatform =  itemView.findViewById(R.id.gameplatform);
//                parent_layout = itemView.findViewById(R.id.parent_layout);

                itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v,getLayoutPosition());
        }

        public void setItemClickListener(ItemClickListener ic)
        {
            itemClickListener = ic;

        }
    }
}