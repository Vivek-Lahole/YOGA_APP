package org.tensorflow.lite.examples.posenet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class yoga_poses_adapter extends RecyclerView.Adapter<yoga_poses_adapter.yoga_poses_adaptere_viewHolder>{

    private List<yoga_pose>  yoga_pose_list;
    private Context context;
    private recyclerViewClickListner recyclerViewClickListner;

    public yoga_poses_adapter(List<yoga_pose> yoga_pose_list,recyclerViewClickListner recyclerViewClickListner){
        this.yoga_pose_list=yoga_pose_list;
        this.recyclerViewClickListner=recyclerViewClickListner;

    }

    @NonNull
    @NotNull
    @Override
    public yoga_poses_adapter.yoga_poses_adaptere_viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View itemView =LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_layout,parent,false);
        return new yoga_poses_adapter.yoga_poses_adaptere_viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull yoga_poses_adaptere_viewHolder holder, int position) {
        holder.aasan_image.setImageResource(yoga_pose_list.get(position).getAasan_Image_Id());
        holder.aasan_name.setText(yoga_pose_list.get(position).getAasan_name());
    }

    @Override
    public int getItemCount() {
        return yoga_pose_list.size();
    }

    public  interface recyclerViewClickListner{
        void onclick(View view,int position );
    }


    public class yoga_poses_adaptere_viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView aasan_image;
        TextView aasan_name;

        public  yoga_poses_adaptere_viewHolder(View view){
            super(view);

            aasan_image= view.findViewById(R.id.image_aasan);
            aasan_name=view.findViewById(R.id.aasan_name);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view){
            recyclerViewClickListner.onclick( view,getAdapterPosition());
        }

    }
}
