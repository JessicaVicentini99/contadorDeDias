package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.myapplication.R;
import com.example.myapplication.constant.PresencaConstants;
import com.example.myapplication.data.SecurityPreferences;


public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkParticipate = findViewById(R.id.check_participate);
        this.mViewHolder.checkParticipate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.check_participate){
            if(this.mViewHolder.checkParticipate.isChecked()){
                this.mSecurityPreferences.storeString(PresencaConstants.PRESENCE_KEY, PresencaConstants.CONFIRMATION_YES);
            } else {
                this.mSecurityPreferences.storeString(PresencaConstants.PRESENCE_KEY, PresencaConstants.CONFIRMATION_NO);
            }
        }
    }

    private static class ViewHolder {
        CheckBox checkParticipate;
    }
    private void loadDataFromActivity() {
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            String presence = extras.getString(PresencaConstants.PRESENCE_KEY);
            boolean isIsset = (presence != null && presence.equals((PresencaConstants.CONFIRMATION_YES)));
            this.mViewHolder.checkParticipate.setChecked(isIsset);
        }
    }
    protected void onResume() {
        super.onResume();
        this.loadDataFromActivity();
    }
}
