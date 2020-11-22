package in.xparticle.divplayer.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import in.xparticle.divplayer.R;
import in.xparticle.divplayer.VideoActivity;
import in.xparticle.divplayer.models.VideoFile;

public class VideoFolderAdapter extends RecyclerView.Adapter<VideoFolderAdapter.MyViewHolder> {

    private final Context mContext;
    private List<VideoFile> mArrayList;
    String myFolderName;

    public VideoFolderAdapter(Context mContext,String myFolderName) {
        this.mContext = mContext;
        this.myFolderName = myFolderName;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.video_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mVideoName.setText(mArrayList.get(position).getTitle());

        holder.mVideoDuration.setText(getVideoDuration(position));

        Glide.with(mContext).load(mArrayList.get(position).getPath()).into(holder.mThumbnail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VideoActivity.class);
//                intent.putExtra("file",mArrayList.get(position));
                intent.putExtra("position",String.valueOf(position));
                intent.putExtra("folderName",myFolderName);
                mContext.startActivity(intent);
            }
        });
    }

    private String getVideoDuration(int position){
        long duration = Long.parseLong(mArrayList.get(position).getDuration());
        String res,min,h,sec;
        duration = duration /1000;
        int min1,h1,sec1;


        min1 = (int) duration / 60;
        sec1 = (int) duration % 60;


        if(min1>=60){
            h1 = min1 / 60;
            min1 = min1 % 60;
            h = convertIntoTwoDecimal(h1);
            min = convertIntoTwoDecimal(min1);
            sec = convertIntoTwoDecimal(sec1);
            res = h + ":" + min + ":" + sec;
        }
        else {
            min = convertIntoTwoDecimal(min1);
            sec = convertIntoTwoDecimal(sec1);
            res = min + ":" + sec;
        }

        return  res;
    }

    private String convertIntoTwoDecimal(int num){
        String res;
        if(num<10){
            res = 0 +""+ num;
        }
        else{
            res = String.valueOf(num);
        }
        return  res;
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView mThumbnail;

        TextView mVideoDuration;
        TextView mVideoName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mThumbnail = itemView.findViewById(R.id.thumbnail);
            mVideoDuration = itemView.findViewById(R.id.video_duration);
            mVideoName = itemView.findViewById(R.id.video_name);

        }
    }
    public void setVideos(List<VideoFile> Videos){
        mArrayList = Videos;
        notifyDataSetChanged();
    }

}
