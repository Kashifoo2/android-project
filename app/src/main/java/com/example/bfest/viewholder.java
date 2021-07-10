package com.example.bfest;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bfest.Interface.ItemclickListener;


public class viewholder extends RecyclerView.ViewHolder implements OnClickListener {


     public TextView leader, stallname, members,stallid,menu,semester, fieldstudy;
     private ImageView imageView;
     public ItemclickListener listener;


    public viewholder(@NonNull View itemView) {
        super(itemView);


        leader=itemView.findViewById(R.id.leaderedittext);
        members=itemView.findViewById(R.id.groupmemberedittext);
        stallid=itemView.findViewById(R.id.stallidedittext);
        stallname=itemView.findViewById(R.id.stallnameedittext);
        menu=itemView.findViewById(R.id.menuitemedittext);
        semester=itemView.findViewById(R.id.semesteredittext);
        fieldstudy=itemView.findViewById(R.id.fieldofstudyeditext);
        imageView=(itemView.findViewById(R.id.imageview));

    }


    public void setItemclickListener(ItemclickListener listener)
    {

            this.listener = listener;
    }

    @Override
    public void onClick(View view) {
       listener.onclick(view,getAdapterPosition(),false);

    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}









