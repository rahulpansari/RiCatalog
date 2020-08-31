package com.ricatalog.ricatalog.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ricatalog.ricatalog.R;
import com.ricatalog.ricatalog.activity.EditProfileActivity;
import com.ricatalog.ricatalog.adapter.DialogEditProfileAdapter;
import com.ricatalog.ricatalog.network.ProfileCountry;
import com.ricatalog.ricatalog.utils.DialogInterface;
import com.ricatalog.ricatalog.viewmodel.EditProfileViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogEditProfile extends DialogFragment implements DialogInterface {
    @BindView(R.id.recycler_dialog)
    RecyclerView dialog_recycler;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    EditProfileViewModel viewModel;
    List<ProfileCountry.Country> list;
    DialogEditProfileAdapter adapter;
    public DialogEditProfile() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.dialog_country,container,false);
        ButterKnife.bind(this,v);
        progressBar.setVisibility(View.VISIBLE);
        viewModel=new ViewModelProvider(getActivity()).get(EditProfileViewModel.class);
        if(viewModel.getCountryList().getValue()==null)
        viewModel.fetchCountryList();
        adapter=new DialogEditProfileAdapter(getActivity(),this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        dialog_recycler.setLayoutManager(layoutManager);
        dialog_recycler.setAdapter(adapter);
        viewModel.getCountryList().observe(this, new Observer<ProfileCountry>() {
            @Override
            public void onChanged(ProfileCountry profileCountry) {
                progressBar.setVisibility(View.GONE);
                list=profileCountry.getCountryList();
                adapter.setAdapter(list);

            }
        });
        return v;
    }

    @Override
    public void selectCountry(ProfileCountry.Country c) {
        viewModel.setCountry(c);
        dismiss();
    }
}
