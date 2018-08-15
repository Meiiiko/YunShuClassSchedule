package top.itning.yunshuclassschedule.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;
import top.itning.yunshuclassschedule.R;
import top.itning.yunshuclassschedule.entity.ClassSchedule;
import top.itning.yunshuclassschedule.http.CheckClassScheduleVersion;
import top.itning.yunshuclassschedule.util.ClassScheduleUtils;
import top.itning.yunshuclassschedule.util.HttpUtils;


/**
 * 本周
 *
 * @author itning
 */
public class ThisWeekFragment extends Fragment {
    private static final String TAG = "ThisWeekFragment";

    private View view;

    static class ViewHolder {
        @BindView(R.id.schedule_gridlayout)
        GridLayout scheduleGridlayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Call<String> checkVersion = HttpUtils.getRetrofit().create(CheckClassScheduleVersion.class).checkVersion();
        checkVersion.enqueue(new retrofit2.Callback<String>() {

            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {

            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.fragment_this_week, container, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        ClassSchedule classSchedule = new ClassSchedule();
        classSchedule.setId("2016010103");
        classSchedule.setClassArray(new String[][]{
                {"计算机网络技术@B313@山镇会", "软件工程@B211@六心理", "概率论与数理统计@A102@赵微然", "WEB程序设计@B218@于洪", "", "", ""},
                {"轮滑@篮球场1@孙熏陶", "", "马克思主义基本原理概论@A401@孙建伟", "软件工程@B211@六心理", "概率论与数理统计@A102@赵微然", "", ""},
                {"四六级英语@A201@李鑫", "形式与政策@A401@余冬梅", "四六级英语@A201@李鑫", "数据库原理与应用@B216@高璐", "WEB程序设计@B218@于洪", "", ""},
                {"", "", "数据库原理与应用@B216@高璐", "马克思主义基本原理概论@A401@孙建伟", "计算机网络技术@B313@山镇会", "", ""},
                {"", "", "", "", "", "", ""},
        });
        ClassScheduleUtils.loadingView(classSchedule.getClassArray(), holder.scheduleGridlayout, Objects.requireNonNull(getContext()), Objects.requireNonNull(getActivity()));
        return view;
    }
}