package com.cash.earnapp.csm.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cash.earnapp.R;

public class NewsFragment extends Fragment {

    View root_view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root_view = inflater.inflate(R.layout.fragment_history, container, false);

        return root_view;

//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
