package com.ricatalog.ricatalog.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.ricatalog.ricatalog.R;
import com.ricatalog.ricatalog.activity.MainActivity;
import com.ricatalog.ricatalog.network.Login;
import com.ricatalog.ricatalog.network.Registeration;
import com.ricatalog.ricatalog.utils.Utils;
import com.ricatalog.ricatalog.viewmodel.LoginViewModel;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class

LoginFragment extends BottomSheetDialogFragment {
    @BindView(R.id.editmobilelogin)
    EditText editphone;
    @BindView(R.id.editpasswordlogin)
    EditText editpassword;
    @BindView(R.id.loginprogress)
    ProgressBar progressBar;
    @BindView(R.id.loginbttn)
    Button loginbttn;

    LoginViewModel viewModel;
    public LoginFragment() {
        super();
    }

    public static LoginFragment getInstance()
    {
        return  new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel=new ViewModelProvider(getActivity()).get(LoginViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bottom_login,container,false);
        ButterKnife.bind(this,view);

        return view;
    }


    @OnClick(R.id.loginbttn)
    public void login(View v)
    {
        if(validateUser())
        {
            progressBar.setVisibility(View.VISIBLE);
            loginbttn.setVisibility(View.INVISIBLE);
            Map<String,String> map=new HashMap<>();
            map.put("mobile",editphone.getText().toString().trim());
            map.put("password",editpassword.getText().toString().trim());
            viewModel.loginUser(map);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel=new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        viewModel.getLoginDetails().observe(getActivity(), new Observer<Login>() {
            @Override
            public void onChanged(Login login) {
                progressBar.setVisibility(View.GONE);
                loginbttn.setVisibility(View.VISIBLE);
                if(login==null)
                {

                }
                else if(login!=null)
                {
                    if(login.getCode()==null) {
                        SharedPreferences.Editor editor=Utils.getShraredPreferencesEditor(getContext());
                        editor.putString("name",login.getData().get(0).getFname());
                        editor.putString("phoneno",login.getData().get(0).getPhoneno());
                        editor.putString("id",login.getData().get(0).getId());
                        editor.apply();
                        Intent i = new Intent(getContext(), MainActivity.class);
                        getActivity().finish();
                        startActivity(i);

                        //Utils.showSuccesfulToast(getContext(), getActivity(), "Success");
                    }
                    else if(login.getCode().equals("2"))
                        Utils.showErrorToast(getContext(),getActivity(),"Invalid Credentials !");
                    else if(login.getCode().equals("-1"))
                        Utils.showErrorToast(getContext(),getActivity(),"Something Went Wrong");

                }
            }
        });
    }

    @OnClick(R.id.loginsignup)
    public void moveSignup(View v)
    {
        viewModel.setFragment(SignupFragment.getInstance());
    }

    public boolean validateUser()
    {
        if(editphone.getText().toString()==null||editphone.getText().toString().isEmpty()||editphone.getText().toString().length()<10)
        {
            editphone.setError("Check Phone No");
            return false;
        }
        if(editpassword.getText().toString()==null||editpassword.getText().toString().isEmpty())
        {
            editpassword.setError("Password Can't be Empty");
            return false;
        }

        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.setLoginDetails();
    }
}
