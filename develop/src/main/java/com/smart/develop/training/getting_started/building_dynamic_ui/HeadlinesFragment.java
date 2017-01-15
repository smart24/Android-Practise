package com.smart.develop.training.getting_started.building_dynamic_ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.smart.develop.R;

/**
 * FileName: HeadlinesFragment
 *
 * Des: Training
 *
 *      --Getting Started
 *
 *      --Building Dynamic UI with Fragment
 *
 *      --标题栏
 *
 * Time: 2017/1/6 上午12:05
 */
public class HeadlinesFragment extends Fragment implements AdapterView.OnItemClickListener{

    private OnFragmentInteractionListener mListener;
    private ListView mLV;
    private ArrayAdapter<String> mAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_headlines, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view){
        mLV = (ListView) view.findViewById(R.id.headlines_fragment_lv);
        mLV.setOnItemClickListener(this);
    }

    private void initData(){
        String []dataResource = getActivity().getResources().getStringArray(R.array.headlines);
        mAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,dataResource);
        mLV.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mListener.onFragmentInteraction(position);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Des: 数据传递接口
     *
     * Time: 2017/1/6 上午12:30
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(int position);
    }
}
