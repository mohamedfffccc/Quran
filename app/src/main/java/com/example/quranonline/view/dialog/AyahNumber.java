package com.example.quranonline.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.quranonline.R;
import com.example.quranonline.view.fragment.tafseer.TafseerFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.quranonline.data.local.HelperMethod.ReplaceFragment;

public class AyahNumber extends DialogFragment {
    public String surah_name;
    public int surah_num;
    @BindView(R.id.surahnametv)
    TextView surahnametv;
    @BindView(R.id.ed_fraom)
    EditText edFraom;
    @BindView(R.id.ed_to)
    EditText edTo;
    @BindView(R.id.acceptbtn)
    Button acceptbtn;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.num_dialog, container, false);
        ButterKnife.bind(this, view);
        surahnametv.append(surah_name);
        return view;
    }

    @OnClick(R.id.acceptbtn)
    public void onViewClicked() {
        getDialog().dismiss();
        TafseerFragment  fragment = new TafseerFragment();
        fragment.surah_num=surah_num;
        fragment.aya_from=Integer.parseInt(edFraom.getText().toString());
        fragment.aya_to=Integer.parseInt(edTo.getText().toString());
        ReplaceFragment(getActivity().getSupportFragmentManager(),fragment,R.id.main , null , "medo");

    }
}
