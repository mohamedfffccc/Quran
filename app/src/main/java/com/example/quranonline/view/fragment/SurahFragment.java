package com.example.quranonline.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.fragment.app.FragmentManager;

import com.example.quranonline.R;
import com.example.quranonline.adapter.SurahAdapter;
import com.example.quranonline.data.local.SurahClass;
import com.example.quranonline.view.dialog.AyahNumber;
import com.example.quranonline.view.fragment.ayah.AyahFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
                    f.surah_num = data.get(position).server;
                    f.server_num = server_num;
                    f.server_name = server_name;
                    f.surah_name = data.get(position).name;
                    ReplaceFragment(getActivity().getSupportFragmentManager(), f, R.id.main, null, "medo");
                } else if (LoadData(getActivity(), "action").equals("read")) {
                    AyahFragment fragment = new AyahFragment();
                    fragment.surahNumber = Integer.parseInt(data.get(position).server);

                    ReplaceFragment(getActivity().getSupportFragmentManager(), fragment, R.id.main, null, "medo");
                } else if (LoadData(getActivity(), "action").equals("tafseer")) {
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    AyahNumber ayahNumber = new AyahNumber();
                    ayahNumber.surah_name = data.get(position).name;
                    ayahNumber.surah_num = Integer.parseInt(data.get(position).server);
                    ayahNumber.show(manager, "show");
//


                }
            }
        });



        // Inflate the layout for this fragment
        return root;
    }

    public void addData() {
        data = new ArrayList<>();
        data.add(new SurahClass("001", " الفاتحة", "مكية"));
        data.add(new SurahClass("002", "البقرة", "مدنية"));
        data.add(new SurahClass("003", "ال عمران ", "مدنية"));
        data.add(new SurahClass("004", "النساء", "مدنية"));
        data.add(new SurahClass("005", " المائدة", "مدنية"));
        data.add(new SurahClass("006", " الانعام", "مكية"));
        data.add(new SurahClass("007", " الأعراف", "مكية"));
        data.add(new SurahClass("008", " الأنفال", "مدنية"));
        data.add(new SurahClass("009", " التوبة ", "مدنية"));
        data.add(new SurahClass("010", " يونس", "مكية"));
        data.add(new SurahClass("011", " هود", "مكية"));
        data.add(new SurahClass("012", " يوسف", "مكية"));
        data.add(new SurahClass("013", " الرعد", "مدنية"));
        data.add(new SurahClass("014", " إبراهيم", "مكية"));
        data.add(new SurahClass("015", " الحجر", "مكية"));
        data.add(new SurahClass("016", " النحل", "مكية"));
        data.add(new SurahClass("017", " الإسراء", "مكية"));
        data.add(new SurahClass("018", " الكهف", "مكية"));
        data.add(new SurahClass("019", " مريم", "مكية"));
        data.add(new SurahClass("020", " طه", "مكية"));
        data.add(new SurahClass("021", " الأنبياء", "مكية"));
        data.add(new SurahClass("022", " الحج", "مدنية"));
        data.add(new SurahClass("023", " المؤمنون", "مكية"));
        data.add(new SurahClass("024", " النّور", "مدنية"));
        data.add(new SurahClass("025", "  الفرقان ", "مكية"));
        data.add(new SurahClass("026", "  الشعراء ", "مكية"));
        data.add(new SurahClass("027", " النّمل", "مكية"));
        data.add(new SurahClass("028", " القصص", "مكية"));
        data.add(new SurahClass("029", " العنكبوت", "مكية"));
        data.add(new SurahClass("030", " الرّوم", "مكية"));
        data.add(new SurahClass("031", " لقمان", "مكية"));
        data.add(new SurahClass("032", " السجدة", "مكية"));
        data.add(new SurahClass("033", " الأحزاب", "مدنية"));
        data.add(new SurahClass("034", " سبأ", "مكية"));
        data.add(new SurahClass("035", " فاطر", "مكية"));
        data.add(new SurahClass("036", " يس", "مكية"));
        data.add(new SurahClass("037", " الصافات", "مكية"));
        data.add(new SurahClass("038", " ص", "مكية"));
        data.add(new SurahClass("039", " الزمر", "مكية"));
        data.add(new SurahClass("040", " غافر", "مكية"));
        data.add(new SurahClass("041", " فصّلت", "مكية"));
        data.add(new SurahClass("042", " الشورى", "مكية"));
        data.add(new SurahClass("043", " الزخرف", "مكية"));
        data.add(new SurahClass("044", " الدّخان", "مكية"));
        data.add(new SurahClass("045", " الجاثية", "مكية"));
        data.add(new SurahClass("046", " الأحقاف", "مكية"));
        data.add(new SurahClass("047", " محمد", "مدنية"));
        data.add(new SurahClass("048", " الفتح", "مدنية"));
        data.add(new SurahClass("049", " الحجرات", "مدنية"));
        data.add(new SurahClass("050", " ق", "مكية"));
        data.add(new SurahClass("051", " الذاريات", "مكية"));
        data.add(new SurahClass("052", " الطور", "مكية"));
        data.add(new SurahClass("053", " النجم", "مكية"));
        data.add(new SurahClass("054", " القمر", "مكية"));
        data.add(new SurahClass("055", " الرحمن", "مدنية"));
        data.add(new SurahClass("056", " الواقعة", "مكية"));
        data.add(new SurahClass("057", " الحديد", "مدنية"));
        data.add(new SurahClass("058", " المجادلة", "مدنية"));
        data.add(new SurahClass("059", " الحشر", "مدنية"));
        data.add(new SurahClass("060", " الممتحنة", "مدنية"));
        data.add(new SurahClass("061", " الصف", "مدنية"));
        data.add(new SurahClass("062", " الجمعة", "مدنية"));
        data.add(new SurahClass("063", " المنافقون", "مدنية"));
        data.add(new SurahClass("064", " التغابن", "مدنية"));
        data.add(new SurahClass("065", " الطلاق", "مدنية"));
        data.add(new SurahClass("066", " التحريم", "مدنية"));
        data.add(new SurahClass("067", " الملك", "مكية"));
        data.add(new SurahClass("068", " القلم", "مكية"));
        data.add(new SurahClass("069", " الحاقة", "مكية"));
        data.add(new SurahClass("070", " المعارج", "مكية"));
        data.add(new SurahClass("071", " نوح", "مكية"));
        data.add(new SurahClass("072", " الجن", "مكية"));
        data.add(new SurahClass("073", " المزّمّل", "مكية"));
        data.add(new SurahClass("074", " المدّثر", "مكية"));
        data.add(new SurahClass("075", " القيامة", "مكية"));
        data.add(new SurahClass("076", " الإنسان", "مدنية"));
        data.add(new SurahClass("077", " المرسلات", "مكية"));
        data.add(new SurahClass("078", " النبأ", "مكية"));
        data.add(new SurahClass("079", " النازعات", "مكية"));
        data.add(new SurahClass("080", " عبس", "مكية"));
        data.add(new SurahClass("081", " التكوير", "مكية"));
        data.add(new SurahClass("082", " الإنفطار", "مكية"));
        data.add(new SurahClass("083", " المطفّفين", "مكية"));
        data.add(new SurahClass("084", " الإنشقاق", "مكية"));
        data.add(new SurahClass("085", " البروج", "مكية"));
        data.add(new SurahClass("086", " الطارق", "مكية"));
        data.add(new SurahClass("087", " الأعلى", "مكية"));
        data.add(new SurahClass("088", " الغاشية", "مكية"));
        data.add(new SurahClass("089", " الفجر", "مكية"));
        data.add(new SurahClass("090", " البلد", "مكية"));
        data.add(new SurahClass("091", " الشمس", "مكية"));
        data.add(new SurahClass("092", " الليل", "مكية"));
        data.add(new SurahClass("093", " الضحى", "مكية"));
        data.add(new SurahClass("094", " الشرح", "مكية"));
        data.add(new SurahClass("095", " التين", "مكية"));
        data.add(new SurahClass("096", " العلق", "مكية"));
        data.add(new SurahClass("097", " القدر", "مكية"));
        data.add(new SurahClass("098", " البينة", "مدنية"));
        data.add(new SurahClass("099", " الزلزلة", "مدنية"));
        data.add(new SurahClass("100", " العاديات", "مكية"));
        data.add(new SurahClass("101", " القارعة", "مكية"));
        data.add(new SurahClass("102", " التكاثر", "مكية"));
        data.add(new SurahClass("103", " العصر", "مكية"));
        data.add(new SurahClass("104", " الهمزة", "مكية"));
        data.add(new SurahClass("105", " الفيل", "مكية"));
        data.add(new SurahClass("106", " قريش", "مكية"));
        data.add(new SurahClass("107", " الماعون", "مكية"));
        data.add(new SurahClass("108", " الكوثر", "مكية"));
        data.add(new SurahClass("109", " الكافرون", "مكية"));
        data.add(new SurahClass("110", " النصر", "مدنية"));
        data.add(new SurahClass("111", " المسد", "مكية"));
        data.add(new SurahClass("112", " الإخلاص", "مكية"));
        data.add(new SurahClass("113", " الفلق", "مكية"));
        data.add(new SurahClass("114", " النّاس", "مكية"));
    }

    @Override
    public void onBack() {

        super.onBack();
    }


}
