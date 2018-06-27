package com.zarmada.zarmadatest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class SurveyFragment extends Fragment {

    private SeekBar sbQuantity;
    private SeekBar sbExpertise;
    private SeekBar sbCulture;
    private EditText txtComment;
    private Button btnSubmit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_survey_layout, container, false);

        this.sbQuantity = view.findViewById(R.id.sbarQuantity);
        this.sbExpertise = view.findViewById(R.id.sbarExpertise);
        this.sbCulture = view.findViewById(R.id.sbarCulture);
        this.txtComment = view.findViewById(R.id.txtComments);

        this.btnSubmit = view.findViewById(R.id.btnSend);
        this.btnSubmit.setOnClickListener(this.btnSendOnClickListener);

        this.setSeekBarMaxValues();

        return view;
    }

    private void setSeekBarMaxValues(){

        this.sbQuantity.setMax(4);
        this.sbExpertise.setMax(4);
        this.sbCulture.setMax(4);

    }


    private View.OnClickListener btnSendOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Survey survey = new Survey();

            survey.setQuantity(sbQuantity.getProgress()+1);
            survey.setCulture(sbCulture.getProgress()+1);
            survey.setExpertise(sbExpertise.getProgress()+1);
            survey.setComments(txtComment.getText().toString());

            ServiceConnectionAdministrator administrator = new ServiceConnectionAdministrator();
            administrator.addSurvey(survey, listener);
        }
    };

    ServiceConnectionAdministrator.ResponseListener listener = new ServiceConnectionAdministrator.ResponseListener() {
        @Override
        public void throwResponse(Object obj) {
            boolean result = (boolean) obj;
            Toast.makeText(getActivity(), (result ? getString(R.string.submit_message_result) : getString(R.string.error_message_result)), Toast.LENGTH_LONG).show();
        }

        @Override
        public void throwError(Object obj) {
            Toast.makeText(getActivity(), getString(R.string.error_message_result), Toast.LENGTH_LONG).show();
        }
    };
}
