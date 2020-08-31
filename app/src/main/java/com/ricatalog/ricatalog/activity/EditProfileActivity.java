package com.ricatalog.ricatalog.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.snackbar.Snackbar;
import com.ricatalog.ricatalog.R;
import com.ricatalog.ricatalog.fragment.DialogEditProfile;
import com.ricatalog.ricatalog.network.EditProfile;
import com.ricatalog.ricatalog.network.ProfileCountry;
import com.ricatalog.ricatalog.network.ProfileUpdate;
import com.ricatalog.ricatalog.utils.Utils;
import com.ricatalog.ricatalog.viewmodel.EditProfileViewModel;
import com.ricatalog.ricatalog.viewmodel.MainActivityViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditProfileActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout shimmerFrameLayout;
    @BindView(R.id.editNameprofile)
    EditText editname;
    @BindView(R.id.editEmailprofile)
    EditText editemail;
    @BindView(R.id.editmobileprofile)
    EditText editmobile;
    @BindView(R.id.editpasswordprofile)
    EditText editpassword;
    @BindView(R.id.editcompanynameprofile)
    EditText editcompname;
    @BindView(R.id.editgstprofile)
    EditText editgst;
    @BindView(R.id.editaddressprofile)
    EditText editaddress;
    @BindView(R.id.editpincodeprofile)
    EditText editpincode;
    @BindView(R.id.editcityprofile)
    EditText editcity;
    @BindView(R.id.editstateprofile)
    EditText editstate;
    @BindView(R.id.editcountryprofile)
    TextView editcountry;
    @BindView(R.id.linearedit)
    LinearLayout linearLayoutedit;
    ImageView doneaction;
    ProgressBar doneprogress;
    EditProfileViewModel viewModel;
    List<EditProfile.ProfileDetails> editProfileList;
    ProfileCountry.Country c;
    DialogEditProfile editProfiles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        toolbar.setTitle("Profile");
        setSupportActionBar(toolbar);
        shimmerFrameLayout.startShimmer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.whitecolor));
        viewModel=new ViewModelProvider(this).get(EditProfileViewModel.class);
        String id= Utils.getShraredPreferences(EditProfileActivity.this).getString("id",null);
        viewModel.fetchProfileList(id);
        viewModel.getProfileList().observe(this, new Observer<EditProfile>() {
            @Override
            public void onChanged(EditProfile editProfile) {
                if (editProfile == null)
                    Snackbar.make(findViewById(android.R.id.content), "Network Error", Snackbar.LENGTH_LONG);
                else {
                    viewModel.fetchCountryList();
                    linearLayoutedit.setVisibility(View.VISIBLE);
                    editProfileList = editProfile.getData();
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.hideShimmer();
                    editname.setText(editProfileList.get(0).getFname().trim());
                    editemail.setText(editProfileList.get(0).getEmail().trim());
                    editmobile.setText(editProfileList.get(0).getO_phone().trim());
                    editpassword.setText(editProfileList.get(0).getPassword().trim());
                    editcompname.setText(editProfileList.get(0).getCompany_name().trim());
                    editgst.setText(editProfileList.get(0).getGst_no().trim());
                    editaddress.setText(editProfileList.get(0).getAddress().trim());
                    editpincode.setText(editProfileList.get(0).getCode().trim());
                    editcity.setText(editProfileList.get(0).getCity().trim());
                    editstate.setText(editProfileList.get(0).getState().trim());

                }
            }
        });
        viewModel.getCountryList().observe(this, new Observer<ProfileCountry>() {
            @Override
            public void onChanged(ProfileCountry profileCountry) {
               for(int i=0;i<profileCountry.getCountryList().size();i++)
               {
                   if(profileCountry.getCountryList().get(i).getId().equals(editProfileList.get(0).getCountry().trim()))
                   {editcountry.setText(profileCountry.getCountryList().get(i).getCountry().trim());
                       return ;
                   }
               }

            }
        });
viewModel.getCountryMutableLiveData().observe(this, new Observer<ProfileCountry.Country>() {
    @Override
    public void onChanged(ProfileCountry.Country country) {
        c=country;
        editcountry.setText(c.getCountry());


    }
});
        viewModel.getUpdateProfile().observe(this, new Observer<ProfileUpdate>() {
            @Override
            public void onChanged(ProfileUpdate profileUpdate) {
                if(profileUpdate!=null)
                {
                    if(profileUpdate.getSuccess().equals("1"))
                    {
                        SharedPreferences.Editor editor=Utils.getShraredPreferencesEditor(EditProfileActivity.this);
                        editor.putString("name",editname.getText().toString());
                        editor.putString("phoneno",editmobile.getText().toString());
                        editor.apply();
                        doneprogress.setVisibility(View.GONE);
                        doneaction.setVisibility(View.VISIBLE);
                        Utils.showSuccesfulToast(EditProfileActivity.this,EditProfileActivity.this,"Succesfully Updated");
                    }
                    else
                    {
                        doneprogress.setVisibility(View.GONE);
                        doneaction.setVisibility(View.VISIBLE);
                        Utils.showErrorToast(EditProfileActivity.this,EditProfileActivity.this,"Something Went Wrong !");

                    }
                }
                else
                {
                    doneprogress.setVisibility(View.GONE);
                    doneaction.setVisibility(View.VISIBLE);
                    Utils.showErrorToast(EditProfileActivity.this,EditProfileActivity.this,"Something Went Wrong !");

                }
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuprofile, menu);
        final MenuItem menuItem = menu.findItem(R.id.action_done);
        View actionView = menuItem.getActionView();
        doneaction = (ImageView) actionView.findViewById(R.id.doneaction);
        doneprogress=(ProgressBar)actionView.findViewById(R.id.signupprogress);
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    doneprogress.setVisibility(View.VISIBLE);
                    doneaction.setVisibility(View.GONE);
                    Map<String,String > map=new HashMap<>();
                    map.put("id",Utils.getShraredPreferences(EditProfileActivity.this).getString("id",null));
                   if( c!=null)
                       map.put("country",c.getId());
                 else
                map.put("country","114");
                map.put("password",editpassword.getText().toString());
                map.put("mobileno","");
                map.put("state",editstate.getText().toString());
                map.put("city",editcity.getText().toString());
                map.put("code",editpincode.getText().toString());
                map.put("address",editaddress.getText().toString());
                map.put("companyname",editcompname.getText().toString());
                map.put("username",editProfileList.get(0).getUsername());
                map.put("mailid",editemail.getText().toString());
                map.put("mobileno",editmobile.getText().toString());
                map.put("name",editname.getText().toString());
                map.put("gst_no",editgst.getText().toString());
viewModel.setProfileData(map);
            }
        });

        return true;
    }
    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
    @OnClick(R.id.editcountryprofile)
    public void click(View v)
    {
       editProfiles=new DialogEditProfile();
        editProfiles.show(getSupportFragmentManager(),"fragment");
    }
}