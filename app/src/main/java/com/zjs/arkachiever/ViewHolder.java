package com.zjs.arkachiever;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView textViewTitle;
    TextView textViewProgress;
    ProgressBar progressBar;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageView);
        textViewTitle = itemView.findViewById(R.id.textViewTitle);
        progressBar = itemView.findViewById(R.id.progressBar);
        textViewProgress = itemView.findViewById(R.id.textViewProgress);


    }
}
