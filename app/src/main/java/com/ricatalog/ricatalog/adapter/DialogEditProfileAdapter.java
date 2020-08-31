package com.ricatalog.ricatalog.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ricatalog.ricatalog.R;
import com.ricatalog.ricatalog.network.ProfileCountry;
import com.ricatalog.ricatalog.utils.DialogInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogEditProfileAdapter extends RecyclerView.Adapter<DialogEditProfileAdapter.DialogEditProfileHolder> {
    Context context;
    private List<ProfileCountry.Country> list;
    com.ricatalog.ricatalog.utils.DialogInterface dialogInterface;
    public DialogEditProfileAdapter(Context c, DialogInterface dialogInterface) {
        super();
        context=c;
        this.dialogInterface=dialogInterface;
    }

    @NonNull
    @Override
    public DialogEditProfileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.dialog_recycler,parent,false);
        return new DialogEditProfileHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DialogEditProfileHolder holder, int position) {
                holder.radioButton.setText(list.get(position).getCountry());
                holder.radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogInterface.selectCountry(list.get(position));
                    }
                });
    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }
public void setAdapter(List<ProfileCountry.Country> countryList)
{
    list=countryList;
    notifyDataSetChanged();
}
    public class DialogEditProfileHolder extends RecyclerView.ViewHolder
    {@BindView(R.id.radioButton)
        RadioButton radioButton;
        public DialogEditProfileHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
