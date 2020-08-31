package com.ricatalog.ricatalog.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.ricatalog.ricatalog.R;
import com.ricatalog.ricatalog.network.Registeration;
import com.ricatalog.ricatalog.utils.Utils;
import com.ricatalog.ricatalog.viewmodel.LoginViewModel;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupFragment extends BottomSheetDialogFragment {
    @BindView(R.id.editname)
    EditText editname;
    @BindView(R.id.editemail)
    EditText editemail;
    @BindView(R.id.editphone)
    EditText editphone;
    @BindView(R.id.signupbttn)
    Button signupbutton;
    @BindView(R.id.signupsignin)
    TextView signin;
    @BindView(R.id.signupprogress)
    ProgressBar signupprogress;
    @BindView(R.id.editcompanyname)
     EditText companyname;
    @BindView(R.id.editplace)
            EditText place;
    LoginViewModel viewModel;
    public static SignupFragment getInstance()
    {
        return new SignupFragment();
    }
    public SignupFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.fragment_bottom_signup,container,false);
        ButterKnife.bind(this,v);
       return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel=new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        viewModel.getGetRegisterationDetails().observe(getActivity(), new Observer<Registeration>() {
            @Override
            public void onChanged(Registeration registeration) {
                signupprogress.setVisibility(View.GONE);
                signupbutton.setVisibility(View.VISIBLE);
                if(registeration==null)
                {

                }
                else if(registeration!=null)
                {
                    if(registeration.getCode().equals("1"))
                    Utils.showSuccesfulToast(getContext(),getActivity(),"Request Sent For Admin Approval");
                    else if(registeration.getCode().equals("-1"))
                        Utils.showErrorToast(getContext(),getActivity(),"Something Went Wrong");
                    else
                        Utils.showSuccesfulToast(getContext(),getActivity(),"User Already Exist !");
                }
            }
        });
    }

    @OnClick(R.id.signupsignin)
    public void moveLogin(View v)
    {
        viewModel.setFragment(LoginFragment.getInstance());
    }

    @OnClick(R.id.signupbttn)
    public void signUp(View v)
    {   if(validateUser()) {
        signupprogress.setVisibility(View.VISIBLE);
        signupbutton.setVisibility(View.INVISIBLE);
        Map<String,String> map=new HashMap<>();
        map.put("name",editname.getText().toString().trim());
        map.put("email",editemail.getText().toString().trim());
        map.put("phone",editphone.getText().toString().trim());
        map.put("place",place.getText().toString().trim());
        map.put("company",companyname.getText().toString().trim());

        viewModel.registerUser(map);
    }
    }

    public boolean validateUser()
    {
        if(editname.getText().toString()==null||editname.getText().toString().isEmpty())
        {
            editname.setError("Name Cant be Empty");
            return false;
        }
        if(editemail.getText().toString()==null||editemail.getText().toString().isEmpty())
        {
            editname.setError("Email Cant be Empty");
            return false;
        }
        if(editphone.getText().toString()==null||editphone.getText().toString().isEmpty()||editphone.getText().toString().length()<10)
        {
            editphone.setError("Phone No Cant be Empty");
            return false;
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.setRegisterationDetails();
    }
}
