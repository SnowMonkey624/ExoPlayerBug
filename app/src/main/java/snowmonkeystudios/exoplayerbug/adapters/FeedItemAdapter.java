package snowmonkeystudios.exoplayerbug.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

import snowmonkeystudios.exoplayerbug.R;
import snowmonkeystudios.exoplayerbug.adapters.viewholders.FeedItemViewHolder;
import snowmonkeystudios.exoplayerbug.objects.FeedItem;

public class FeedItemAdapter extends RecyclerView.Adapter<FeedItemViewHolder> {

    private List<FeedItem> feedItems;
    private SimpleExoPlayer player;
    private Context mContext;

    public FeedItemAdapter(Context mContext, List<FeedItem> feedItems) {

        this.mContext = mContext;
        this.feedItems = feedItems;
    }

    @NonNull
    @Override
    public FeedItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FeedItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_feed_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FeedItemViewHolder holder, int position) {

        FeedItem currentItem = feedItems.get(holder.getAdapterPosition());

        holder.getTitle().setText(currentItem.getTitle());

        //Standard string url to uri parsing and player setup.
        Uri uri = Uri.parse(currentItem.getVideoUrl());

        player = new SimpleExoPlayer.Builder(mContext).setTrackSelector(new DefaultTrackSelector(mContext)).build();
        player.setPlayWhenReady(true);

        MediaSource mediaSource = new ProgressiveMediaSource.Factory(new DefaultDataSourceFactory(mContext, Util.getUserAgent(mContext, "snowmonkeystudios.exoplayerbug"))).createMediaSource(uri);

        player.prepare(mediaSource, true, true);
        holder.getPlayerView().setPlayer(player);
        holder.getPlayerView().setControllerAutoShow(false);

        //As to not blow your eardrums out from several videos playing at once.
        player.setVolume(0);
    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }
}
