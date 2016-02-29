package com.karrye.meetsession.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.karrye.meetsession.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2016-02-26.
 */
public class SearchAdapter extends ArrayAdapter<SearchResultItem> {
    private List<SearchResultItem> list;
    private Activity myActivity;

    public SearchAdapter(Activity myActivity_, List<SearchResultItem> list_){
        super(myActivity_,-1,list_);
        this.myActivity = myActivity_;
        this.list = list_;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        SearchResultItem item = list.get(position);

        if(convertView == null){
            LayoutInflater inflater = myActivity.getLayoutInflater();
            view = inflater.inflate(R.layout.listview_quick_profile,null);
        }else{
            view = convertView;
        }

        TextView txtName = (TextView)view.findViewById(R.id.txtName);
        TextView txtAge = (TextView)view.findViewById(R.id.txtAge);

        txtName.setText(item.getName());
        txtAge.setText("Age: " + item.getAge());

        return view;
    }
}
