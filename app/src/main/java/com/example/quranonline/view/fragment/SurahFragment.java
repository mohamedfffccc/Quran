package com.example.quranonline.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.quranonline.view.fragment.ayah.AyahFragment;
import com.example.quranonline.R;
import com.example.quranonline.adapter.SurahAdapter;
import com.example.quranonline.data.local.SurahClass;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.quranonline.data.local.HelperMethod.ReplaceFragment;
import static com.example.quranonline.data.local.SharedPreferencesManger.LoadData;


public class SurahFragment extends BaseFragment {
    public String server_name;
    public String server_num;
    SurahAdapter adapter;
    ArrayList<SurahClass> data;
    @BindView(R.id.surahlv)
    ListView surahlv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpActivity();
        View root = inflater.inflate(R.layout.fragment_surah, container, false);
        ButterKnife.bind(this, root);
        addData();
        adapter = new SurahAdapter(data, getActivity());
        surahlv.setAdapter(adapter);
        surahlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (LoadData(getActivity(), "action").equals("listen")) {
                    PlayerFragment f = new PlayerFragment();
                    f.surah_num=data.get(position).server;
                    f.server_num=server_num;
                    f.server_name=server_name;
                    ReplaceFragment(getActivity().getSupportFragmentManager(),f ,R.id.main , null , "medo");
                }
                else if (LoadData(getActivity(), "action").equals("read")) {
                    AyahFragment fragment = new AyahFragment();
                    fragment.surahNumber=Integer.parseInt(data.get(position).server);

                    ReplaceFragment(getActivity().getSupportFragmentManager(),fragment ,R.id.main , null , "medo");
                }

            }
        });

        // Inflate the layout for this fragment
        return root;
    }

    public void addData() {
        data = new ArrayList<>();
        data.add(new SurahClass("001", " الفاتحة"));
        data.add(new SurahClass("002", "البقرة"));
        data.add(new SurahClass("003", "ال عمران "));
        data.add(new SurahClass("004", "النساء"));
        data.add(new SurahClass("005", " المائدة"));
        data.add(new SurahClass("006", " الانعام"));
        data.add(new SurahClass("007", " الأعراف"));
        data.add(new SurahClass("008", " الأنفال"));
        data.add(new SurahClass("009", " التوبة "));
        data.add(new SurahClass("010", " يونس"));
        data.add(new SurahClass("011", " هود"));
        data.add(new SurahClass("012", " يوسف"));
        data.add(new SurahClass("013", " الرعد"));
        data.add(new SurahClass("014", " إبراهيم"));
        data.add(new SurahClass("015", " الحجر"));
        data.add(new SurahClass("016", " النحل"));
        data.add(new SurahClass("017", " الإسراء"));
        data.add(new SurahClass("018", " الكهف"));
        data.add(new SurahClass("019", " مريم"));
        data.add(new SurahClass("020", " طه"));
        data.add(new SurahClass("021", " الأنبياء"));
        data.add(new SurahClass("022", " الحج"));
        data.add(new SurahClass("023", " المؤمنون"));
        data.add(new SurahClass("024", " النّور"));
        data.add(new SurahClass("025", "  الفرقان "));
        data.add(new SurahClass("026", "  الشعراء "));
        data.add(new SurahClass("027", " النّمل"));
        data.add(new SurahClass("028", " القصص"));
        data.add(new SurahClass("029", " العنكبوت"));
        data.add(new SurahClass("030", " الرّوم"));
        data.add(new SurahClass("031", " لقمان"));
        data.add(new SurahClass("032", " السجدة"));
        data.add(new SurahClass("033", " الأحزاب"));
        data.add(new SurahClass("034", " سبأ"));
        data.add(new SurahClass("035", " فاطر"));
        data.add(new SurahClass("036", " يس"));
        data.add(new SurahClass("037", " الصافات"));
        data.add(new SurahClass("038", " ص"));
        data.add(new SurahClass("039", " الزمر"));
        data.add(new SurahClass("040", " غافر"));
        data.add(new SurahClass("041", " فصّلت"));
        data.add(new SurahClass("042", " الشورى"));
        data.add(new SurahClass("043", " الزخرف"));
        data.add(new SurahClass("044", " الدّخان"));
        data.add(new SurahClass("045", " الجاثية"));
        data.add(new SurahClass("046", " الأحقاف"));
        data.add(new SurahClass("047", " محمد"));
        data.add(new SurahClass("048", " الفتح"));
        data.add(new SurahClass("049", " الحجرات"));
        data.add(new SurahClass("050", " ق"));
        data.add(new SurahClass("051", " الذاريات"));
        data.add(new SurahClass("052", " الطور"));
        data.add(new SurahClass("053", " النجم"));
        data.add(new SurahClass("054", " القمر"));
        data.add(new SurahClass("055", " الرحمن"));
        data.add(new SurahClass("056", " الواقعة"));
        data.add(new SurahClass("057", " الحديد"));
        data.add(new SurahClass("058", " المجادلة"));
        data.add(new SurahClass("059", " الحشر"));
        data.add(new SurahClass("060", " الممتحنة"));
        data.add(new SurahClass("061", " الصف"));
        data.add(new SurahClass("062", " الجمعة"));
        data.add(new SurahClass("063", " المنافقون"));
        data.add(new SurahClass("064", " التغابن"));
        data.add(new SurahClass("065", " الطلاق"));
        data.add(new SurahClass("066", " التحريم"));
        data.add(new SurahClass("067", " الملك"));
        data.add(new SurahClass("068", " القلم"));
        data.add(new SurahClass("069", " الحاقة"));
        data.add(new SurahClass("070", " المعارج"));
        data.add(new SurahClass("071", " نوح"));
        data.add(new SurahClass("072", " الجن"));
        data.add(new SurahClass("073", " المزّمّل"));
        data.add(new SurahClass("074", " المدّثر"));
        data.add(new SurahClass("075", " القيامة"));
        data.add(new SurahClass("076", " الإنسان"));
        data.add(new SurahClass("077", " المرسلات"));
        data.add(new SurahClass("078", " النبأ"));
        data.add(new SurahClass("079", " النازعات"));
        data.add(new SurahClass("080", " عبس"));
        data.add(new SurahClass("081", " التكوير"));
        data.add(new SurahClass("082", " الإنفطار"));
        data.add(new SurahClass("083", " المطفّفين"));
        data.add(new SurahClass("084", " الإنشقاق"));
        data.add(new SurahClass("085", " البروج"));
        data.add(new SurahClass("086", " الطارق"));
        data.add(new SurahClass("087", " الأعلى"));
        data.add(new SurahClass("088", " الغاشية"));
        data.add(new SurahClass("089", " الفجر"));
        data.add(new SurahClass("090", " البلد"));
        data.add(new SurahClass("091", " الشمس"));
        data.add(new SurahClass("092", " الليل"));
        data.add(new SurahClass("093", " الضحى"));
        data.add(new SurahClass("094", " الشرح"));
        data.add(new SurahClass("095", " التين"));
        data.add(new SurahClass("096", " العلق"));
        data.add(new SurahClass("097", " القدر"));
        data.add(new SurahClass("098", " البينة"));
        data.add(new SurahClass("099", " الزلزلة"));
        data.add(new SurahClass("100", " العاديات"));
        data.add(new SurahClass("101", " القارعة"));
        data.add(new SurahClass("102", " التكاثر"));
        data.add(new SurahClass("103", " العصر"));
        data.add(new SurahClass("104", " الهمزة"));
        data.add(new SurahClass("105", " الفيل"));
        data.add(new SurahClass("106", " قريش"));
        data.add(new SurahClass("107", " الماعون"));
        data.add(new SurahClass("108", " الكوثر"));
        data.add(new SurahClass("109", " الكافرون"));
        data.add(new SurahClass("110", " النصر"));
        data.add(new SurahClass("111", " المسد"));
        data.add(new SurahClass("112", " الإخلاص"));
        data.add(new SurahClass("113", " الفلق"));
        data.add(new SurahClass("114", " النّاس"));
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}
