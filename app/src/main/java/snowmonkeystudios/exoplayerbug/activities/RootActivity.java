package snowmonkeystudios.exoplayerbug.activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import snowmonkeystudios.exoplayerbug.R;
import snowmonkeystudios.exoplayerbug.fragments.RoundedDialogFragment;
import snowmonkeystudios.exoplayerbug.fragments.StandardDialogFragment;
import snowmonkeystudios.exoplayerbug.objects.FeedItem;

/**
 * This application is built to demonstrate a bug involving ExoPlayer when used within a DialogFragment's RecyclerView.
 * As of right now, if a video is scrolled to the end of the RecyclerView to a point where it should disappear from the
 * DialogFragment's view, it continues to appear in the transparent margins at the edge of the DialogFragment. This
 * does not seem like expected behavior.
 */
public class RootActivity extends AppCompatActivity {

    private Button showStandardDialog, showRoundedDialog;
    private List<FeedItem> feedItems;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_root);

        showStandardDialog = findViewById(R.id.activity_root_button_show_standard_dialog);
        showRoundedDialog = findViewById(R.id.activity_root_button_show_rounded_dialog);

        generateFeedItems();

        showStandardDialog.setOnClickListener(v -> {

            StandardDialogFragment.newInstance(feedItems).show(getSupportFragmentManager(), "Standard Dialog");
        });

        showRoundedDialog.setOnClickListener(v -> {

            RoundedDialogFragment.newInstance(feedItems).show(getSupportFragmentManager(), "Rounded Dialog");
        });
    }

    //Populates single list of items for use in both dialog fragments.
    //Video via https://sample-videos.com/
    private void generateFeedItems() {

        feedItems = new ArrayList<>();

        for(int i = 0; i < 10; i++) {

            feedItems.add(new FeedItem(String.format(Locale.getDefault(), "Item %d", i), "https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_10mb.mp4"));
        }
    }
}
