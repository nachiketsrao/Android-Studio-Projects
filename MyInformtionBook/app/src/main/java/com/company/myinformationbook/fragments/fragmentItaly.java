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

public class fragmentItaly extends Fragment {

    public static fragmentItaly newInstance()
    {
        return new fragmentItaly();
    }

    private ImageView imageViewItaly;
    private ProgressBar progressBarItaly;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_italy, container, false);

        imageViewItaly = view.findViewById(R.id.imageViewItaly);
        progressBarItaly = view.findViewById(R.id.progressBarItaly);

        Picasso.get().load("https://p.kindpng.com/picc/s/208-2084626_flag-hd-png-download.png")
                .into(imageViewItaly, new Callback() {
                    @Override
                    public void onSuccess() {

                        progressBarItaly.setVisibility(View.INVISIBLE);

                    }

                    @Override
                    public void onError(Exception e) {

                        Toast.makeText(getActivity(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        progressBarItaly.setVisibility(View.INVISIBLE);

                    }
                });

       return view;
    }
}
