package com.dreamtouch.marks.recycler;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dreamtouch.marks.R;
import com.dreamtouch.marks.models.Module;

public class ViewHolder extends RecyclerView.ViewHolder {


    public ViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bindData(Module module) {
        TextView name = itemView.findViewById(R.id.moduleName);
        TextView note = itemView.findViewById(R.id.moduleNote);

        name.setText(module.getName());
        note.setText(String.valueOf(module.getMarks()));
    }
}
