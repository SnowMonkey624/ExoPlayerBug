package snowmonkeystudios.exoplayerbug.fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import snowmonkeystudios.exoplayerbug.R;
import snowmonkeystudios.exoplayerbug.adapters.FeedItemAdapter;
import snowmonkeystudios.exoplayerbug.objects.FeedItem;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

//Somewhat modified dialog similar to what I've seen at work.
public class RoundedDialogFragment extends DialogFragment {

    private Context mContext;
    private List<FeedItem> feedItems;
    private ImageButton close;
    private RecyclerView recyclerView;
    private FeedItemAdapter adapter;

    public static RoundedDialogFragment newInstance(List<FeedItem> feedItems) {

        Bundle args = new Bundle();

        args.putParcelableArrayList("feedItems", (ArrayList<FeedItem>) feedItems);

        RoundedDialogFragment fragment = new RoundedDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();

        if(dialog != null && dialog.getWindow() != null){

            dialog.getWindow().setLayout(MATCH_PARENT, MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(mContext, android.R.color.transparent)));
        }
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
        return inflater.inflate(R.layout.dialog_rounded, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog =  super.onCreateDialog(savedInstanceState);

        if(dialog.getWindow() != null) {

            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }

        return dialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContext = view.getContext();

        close = view.findViewById(R.id.dialog_rounded_image_button_close);
        recyclerView = view.findViewById(R.id.dialog_rounded_recycler_view);

        close.setOnClickListener(v -> dismiss());

        adapter = new FeedItemAdapter(mContext, feedItems);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }
}
