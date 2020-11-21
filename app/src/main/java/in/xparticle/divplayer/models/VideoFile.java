package in.xparticle.divplayer.models;

import android.os.Parcel;
import android.os.Parcelable;

public class VideoFile implements Parcelable {
    private String id;
    private String path;
    private String title;
    private String fileName;
    private String size;
    private String dateAdded;
    private String duration;

    public VideoFile(String id, String path, String title, String fileName, String size,
                     String dateAdded, String duration) {
        this.id = id;
        this.path = path;
        this.title = title;
        this.fileName = fileName;
        this.size = size;
        this.dateAdded = dateAdded;
        this.duration = duration;
    }

    protected VideoFile(Parcel in) {
        id = in.readString();
        path = in.readString();
        title = in.readString();
        fileName = in.readString();
        size = in.readString();
        dateAdded = in.readString();
        duration = in.readString();
    }

    public static final Creator<VideoFile> CREATOR = new Creator<VideoFile>() {
        @Override
        public VideoFile createFromParcel(Parcel in) {
            return new VideoFile(in);
        }

        @Override
        public VideoFile[] newArray(int size) {
            return new VideoFile[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "VideoFile{" +
                "id='" + id + '\'' +
                ", path='" + path + '\'' +
                ", title='" + title + '\'' +
                ", fileName='" + fileName + '\'' +
                ", size='" + size + '\'' +
                ", dateAdded='" + dateAdded + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(path);
        dest.writeString(title);
        dest.writeString(fileName);
        dest.writeString(size);
        dest.writeString(dateAdded);
        dest.writeString(duration);
    }
}
