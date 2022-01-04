package com.company.myinformationbook.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.company.myinformationbook.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class fragmentFrance extends Fragment {

    public static fragmentFrance newInstance()
    {
        return new fragmentFrance();
    }

    private ImageView imageViewFrance;
    private ProgressBar progressBarFrance;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_france, container, false);

       imageViewFrance = view.findViewById(R.id.imageViewFrance);
       progressBarFrance = view.findViewById(R.id.progressBarFrance);

        Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/thumb/6/62/Flag_of_France.png/800px-Flag_of_France.png")
                .into(imageViewFrance, new Callback() {
                    @Override
                    public void onSuccess() {

                        progressBarFrance.setVisibility(View.INVISIBLE);

                    }

                    @Override
                    public void onError(Exception e) {

                        Toast.makeText(getActivity(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        progressBarFrance.setVisibility(View.INVISIBLE);

                    }
                });

       return view;
    }
}
