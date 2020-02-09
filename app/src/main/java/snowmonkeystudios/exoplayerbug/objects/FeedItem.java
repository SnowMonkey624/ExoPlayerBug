package snowmonkeystudios.exoplayerbug.objects;

import android.os.Parcel;
import android.os.Parcelable;

//Simple object containing a title and a video url for use in the dialog fragments.
public class FeedItem implements Parcelable {

    private String title, videoUrl;

    public FeedItem(String title, String videoUrl) {
        this.title = title;
        this.videoUrl = videoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.videoUrl);
    }

    protected FeedItem(Parcel in) {
        this.title = in.readString();
        this.videoUrl = in.readString();
    }

    public static final Parcelable.Creator<FeedItem> CREATOR = new Parcelable.Creator<FeedItem>() {
        @Override
        public FeedItem createFromParcel(Parcel source) {
            return new FeedItem(source);
        }

        @Override
        public FeedItem[] newArray(int size) {
            return new FeedItem[size];
        }
    };
}
