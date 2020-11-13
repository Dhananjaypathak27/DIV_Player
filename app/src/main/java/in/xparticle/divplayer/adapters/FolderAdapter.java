package in.xparticle.divplayer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.xparticle.divplayer.R;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.MyViewHolder> {

    private final Context mContext;
    private final ArrayList<String> mArrayList;

    public FolderAdapter(Context mContext, ArrayList<String> mArrayList) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;
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
    }

    @Override
    public int getItemCount() {
        if(mArrayList.size() > 0) {
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
}
