package in.xparticle.divplayer.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import in.xparticle.divplayer.R;
import in.xparticle.divplayer.VideoFolderActivity;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.MyViewHolder> {

    private final Context mContext;
    private List<String> mArrayList;

    public FolderAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.folder_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.folderName.setText(mArrayList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VideoFolderActivity.class);
                intent.putExtra("folderName",mArrayList.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mArrayList!= null) {
            return mArrayList.size();
        }
        return 0;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView folderName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            folderName = itemView.findViewById(R.id.folder_name);
        }
    }
    public void setFolder(List<String> folder){
        mArrayList = folder;
        notifyDataSetChanged();
    }

}
