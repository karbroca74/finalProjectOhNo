package com.karrye.meetsession;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by teacher on 2015-12-09.
 */
public class PagerFragment extends Fragment {
    public static final String EXTRA_LAYOUT_ID = "PageFragment_EXTRA_LAYOUT_ID";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutId = getArguments().getInt(EXTRA_LAYOUT_ID);
        return inflater.inflate(layoutId, container, false);
    }

}