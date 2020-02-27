package com.example.quranonline.view.fragment;

import androidx.fragment.app.Fragment;

import com.example.quranonline.view.activity.BaseActivity;

public class BaseFragment extends Fragment
{
    BaseActivity baseActivity;
    public void setUpActivity()
    {
        baseActivity = (BaseActivity) getActivity();
        baseActivity.baseFragment=this;
    }
    public void onBack()
    {
        baseActivity.superBackPressed();
    }
}
