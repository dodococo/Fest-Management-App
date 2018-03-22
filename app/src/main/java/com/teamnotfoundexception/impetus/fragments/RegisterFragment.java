package com.teamnotfoundexception.impetus.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.teamnotfoundexception.impetus.Databases.EventItem;
import com.teamnotfoundexception.impetus.Databases.FirebaseHelper;
import com.teamnotfoundexception.impetus.Databases.StatusManager;
import com.teamnotfoundexception.impetus.R;
import com.teamnotfoundexception.impetus.adapters.EventsAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class RegisterFragment extends Fragment implements View.OnClickListener {

    private static final String  TAG = "REGISTER FRAGMENT";

    EditText mTeamMembers,mCollege,mPhone,mTeam;
    Button btn;
    EventItem eventItem;
    TextView mEventNameRegister;
    LinearLayout mNotRegistered;
    RelativeLayout mRegisteredAlready;
    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_register, container, false);

        Bundle bundle = getArguments();
        if (bundle == null) {
            Toast.makeText(getContext(), "Something is wrong with your phone!", Toast.LENGTH_SHORT).show();
            return view;
        }
        mNotRegistered =  view.findViewById(R.id.notRegistered);
        mRegisteredAlready = view.findViewById(R.id.registeredAlready);

        mTeam = (EditText) view.findViewById(R.id.regTeamName);
        eventItem = (EventItem) bundle.getSerializable("dope");
        mTeamMembers = (EditText) view.findViewById(R.id.regTeamMem);
        mCollege = (EditText) view.findViewById(R.id.regCollege);
        mPhone = (EditText) view.findViewById(R.id.regPhone);
        btn = (Button) view.findViewById(R.id.regRegister);
        mEventNameRegister = (TextView) view.findViewById(R.id.eventNameRegister);
        mEventNameRegister.setText(eventItem.getName());
        if(eventItem.isRegistered == 1) {
            mNotRegistered.setVisibility(View.GONE);
            mRegisteredAlready.setVisibility(View.VISIBLE);
            btn.setEnabled(false);
            btn.setText("You have registered already");
            mTeam.setEnabled(false);
            mTeamMembers.setEnabled(false);
            mCollege.setEnabled(false);
            mPhone.setEnabled(false);
            return view;
        }else{
            mNotRegistered.setVisibility(View.VISIBLE);
            mRegisteredAlready.setVisibility(View.GONE);
        }
        btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        btn.setEnabled(false);


        if(mTeam.getText().toString().isEmpty() ||
           mCollege.getText().toString().isEmpty() ||
           mCollege.getText().toString().isEmpty() )  {
            Toast.makeText(getActivity().getApplicationContext(), "fill up everything", Toast.LENGTH_SHORT).show();
            btn.setEnabled(true);
            return ;
        }



        String teamName = mTeam.getText().toString();

        String teamMembers= mTeamMembers.getText().toString();

        String collegeName = mCollege.getText().toString();

        FirebaseHelper.Participant participant = new FirebaseHelper.Participant(teamName, collegeName, teamMembers);


        StatusManager.get(getActivity().getApplicationContext()).addToRegistered(eventItem, participant);

        btn.setText("REGISTERED SUCCESSFULLY");
        btn.setEnabled(false);
        mTeam.setEnabled(false);
        mTeamMembers.setEnabled(false);
        mCollege.setEnabled(false);
        mPhone.setEnabled(false);

        // StatusManager.get(getContext()).setupNotification(eventItem);

        //EventsFragment.notifyMe();

        return ;
    }



}
