package com.smart.develop.training.getting_started.building_dynamic_ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.smart.develop.R;
/**
 * FileName: DynamicUIActivity
 *
 * Des: Training
 *
 *      --Getting Started
 *
 *      --Building Dynamic UI with Fragment
 *
 * Time: 2017/1/6 上午12:05
 */
public class DynamicUIActivity extends AppCompatActivity implements HeadlinesFragment.OnFragmentInteractionListener{

    private boolean isScreenLarge = false;
    private FragmentManager mManager;
    private ArticleFragment mArticleFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_ui);
        initData();
        initView();
    }

    private void initData(){
        mManager = getSupportFragmentManager();
    }

    private void initView(){
        if (findViewById(R.id.dynamic_ui_container) == null) {
            isScreenLarge = true;
            mArticleFrag = (ArticleFragment)mManager.findFragmentById(R.id.article_fragment);
            mArticleFrag.updateArticleView(0);
        }else{
            HeadlinesFragment firstFragment = new HeadlinesFragment();
            mManager.beginTransaction().add(R.id.dynamic_ui_container, firstFragment).commit();
            isScreenLarge = false;
        }
    }

    @Override
    public void onFragmentInteraction(int position) {
        if(isScreenLarge){
            mArticleFrag.updateArticleView(position);
        }else{
            ArticleFragment newFragment = new ArticleFragment();
            Bundle args = new Bundle();
            args.putInt(ArticleFragment.ARTICAL_POSITION, position);
            newFragment.setArguments(args);

            FragmentTransaction transaction = mManager.beginTransaction();
            transaction.replace(R.id.dynamic_ui_container, newFragment);
            transaction.addToBackStack(null);

            transaction.commit();
        }
    }
}
