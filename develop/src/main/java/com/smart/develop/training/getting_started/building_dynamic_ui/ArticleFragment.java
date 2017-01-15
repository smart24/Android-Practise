package com.smart.develop.training.getting_started.building_dynamic_ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smart.develop.R;

/**
 * FileName: ArticleFragment
 *
 * Des: Training
 *
 *      --Getting Started
 *
 *      --Building Dynamic UI with Fragment
 *
 *      --详情
 *
 * Time: 2017/1/6 上午12:08
 */
public class ArticleFragment extends Fragment {

    public static final String ARTICAL_POSITION = "ARTICAL_POSITION";
    private String [] mDataResource = null;
    private int mPosition;
    private TextView mArticle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(ARTICAL_POSITION,0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view){
        mArticle = (TextView) view.findViewById(R.id.article_fragment_tv);
    }

    private void initData(){
        mDataResource = getActivity().getResources().getStringArray(R.array.article);
        mArticle.setText(mDataResource[mPosition]);
    }

    public void updateArticleView(int position){
        mArticle.setText(mDataResource[position]);
    }
}
