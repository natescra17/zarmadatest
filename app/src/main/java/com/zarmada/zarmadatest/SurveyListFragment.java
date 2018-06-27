package com.zarmada.zarmadatest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SurveyListFragment extends Fragment {

    private ListView listView;
    private SurveyAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_survey_layout, container, false);

        listView = view.findViewById(R.id.surveysList);

        getSurveys();

        return view;

    }

    private void getSurveys() {
        ServiceConnectionAdministrator administrator = new ServiceConnectionAdministrator();
        administrator.getSurveys(new ServiceConnectionAdministrator.ResponseListener() {
            @Override
            public void throwResponse(Object obj) {

                List<Survey> surveys = (List<Survey>) obj;
                adapter = new SurveyAdapter(surveys);
                listView.setAdapter(adapter);
            }

            @Override
            public void throwError(Object obj) {
                Toast.makeText(getActivity(), getString(R.string.error_message_result), Toast.LENGTH_LONG).show();
            }
        });
    }

    class SurveyAdapter extends BaseAdapter {

        private List<Survey> surveys;

        public SurveyAdapter(List<Survey> surveys) {
            this.surveys = surveys;
        }

        @Override
        public int getCount() {
            return surveys.size();
        }

        @Override
        public Object getItem(int i) {
            return surveys.get(i);
        }

        @Override
        public long getItemId(int i) {
            return surveys.get(i).getId();
        }

        @SuppressLint("StringFormatMatches")
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            View itemView = view;

            if (itemView == null) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                itemView = inflater.inflate(R.layout.survey_item, viewGroup, false);
            }

            TextView tvQuantity, tvExpertise, tvCulture, tvAverage;

            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvExpertise = itemView.findViewById(R.id.tvExpertise);
            tvCulture = itemView.findViewById(R.id.tvCulture);
            tvAverage = itemView.findViewById(R.id.tvAverage);

            Survey surv = (Survey) this.getItem(i);

            tvQuantity.setText(getString(R.string.survey_list_quantity_label, surv.getQuantity()));
            tvExpertise.setText(getString(R.string.survey_list_expertise_label, surv.getExpertise()));
            tvCulture.setText(getString(R.string.survey_list_culture_label, surv.getCulture()));
            tvAverage.setText(getString(R.string.survey_list_average_label, surv.getAverage()));

            return itemView;
        }
    }
}
