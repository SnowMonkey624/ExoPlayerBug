package snowmonkeystudios.exoplayerbug.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import snowmonkeystudios.exoplayerbug.R;
import snowmonkeystudios.exoplayerbug.adapters.FeedItemAdapter;
import snowmonkeystudios.exoplayerbug.objects.FeedItem;

//Barebones dialog to display default behavior
public class StandardDialogFragment extends DialogFragment {

    private Context mContext;
    private List<FeedItem> feedItems;
    private ImageButton close;
    private RecyclerView recyclerView;
    private FeedItemAdapter adapter;

    public static StandardDialogFragment newInstance(List<FeedItem> feedItems) {

        Bundle args = new Bundle();

        args.putParcelableArrayList("feedItems", (ArrayList<FeedItem>) feedItems);

        StandardDialogFragment fragment = new StandardDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {

            this.feedItems = getArguments().getParcelableArrayList("feedItems");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_standard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContext = view.getContext();

        close = view.findViewById(R.id.dialog_standard_image_button_close);
        recyclerView = view.findViewById(R.id.dialog_standard_recycler_view);

        close.setOnClickListener(v -> dismiss());

        adapter = new FeedItemAdapter(mContext, feedItems);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }
}
