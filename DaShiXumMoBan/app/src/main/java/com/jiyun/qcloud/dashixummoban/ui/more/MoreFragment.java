package com.jiyun.qcloud.dashixummoban.ui.more;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.ui.more.adapter.RadioAdapter;
import com.jiyun.qcloud.dashixummoban.ui.more.bean.Radio;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends BaseFragment implements MoreContrat.View {

    @BindView(R.id.lista)
    ListView lista;
    Unbinder unbinder;
    private List<String> arraytu = new ArrayList<>();
    ArrayList<Radio.DataBean.TrailersBean> arrayList = new ArrayList<>();
    RollPagerView roll;
    private RadioAdapter radioAdapter;
    private MoreContrat.Presenter presenter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_more;
    }

    @Override
    protected void initData() {
        presenter.start();
    }

    @Override
    protected void initView(View view) {

        radioAdapter = new RadioAdapter(getActivity(), arrayList);
        lista.setAdapter(radioAdapter);
    }
    //切换页面暂停视频
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void dimissProgress() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(MoreContrat.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showMoviedata(Radio movieBean) {
        List<Radio.DataBean.TrailersBean> trailers = movieBean.getData().getTrailers();
        for (int i = 0; i < trailers.size(); i++) {
            arrayList.addAll(trailers);
            String coverImg = trailers.get(i).getCoverImg();
            if(arraytu.size()<5){
               arraytu.add(coverImg);
           }
        }
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.ben_te, null);
        roll = (RollPagerView) inflate.findViewById(R.id.roll);
        roll.setPlayDelay(2000);//*播放间隔
        roll.setAnimationDurtion(500);//透明度d
        roll.setAdapter(new MyRollAdapter());
        lista.addHeaderView(inflate);
        radioAdapter.notifyDataSetChanged();
    }

    public class MyRollAdapter extends StaticPagerAdapter {
        // private String[] nameq={namem,name3};
        @Override
        public View getView(ViewGroup container, int position) {
            ImageView imageView = new ImageView(container.getContext());
            Glide.with(getContext()).load(arraytu.get(position)).into(imageView);
//            Glide.withth(Main3Activity.this).load(nameq[2]).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            return imageView;


        }

        @Override
        public int getCount() {
            return arraytu.size();
        }
    }

    @Override
    public void setBundle(Bundle bundle) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
