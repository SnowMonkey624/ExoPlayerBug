package snowmonkeystudios.exoplayerbug.adapters.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ui.PlayerView;

import snowmonkeystudios.exoplayerbug.R;

public class FeedItemViewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private PlayerView playerView;

    public FeedItemViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.card_feed_item_title);
        playerView = itemView.findViewById(R.id.card_feed_item_player_view);
    }

    public PlayerView getPlayerView() {
        return playerView;
    }

    public TextView getTitle() {
        return title;
    }
}
