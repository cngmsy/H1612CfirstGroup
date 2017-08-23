package com.jiyun.qcloud.dashixummoban.ui.home1.shoppingcar;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiyun.qcloud.dashixummoban.base.BaseBean;
import com.jiyun.qcloud.dashixummoban.entity.Data;
import com.jiyun.qcloud.dashixummoban.entity.Head;
import com.jiyun.qcloud.dashixummoban.entity.ShopEntity;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.IGoodsModelImpl;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.IHomeModel;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/22.
 */

public class GoodsPersenter implements GoodsContract.Persenter {
    private GoodsContract.View goodView;
    private IHomeModel homeModel;
    private List<ShopEntity.ListBean> list;
    private List<Data> dataList = new ArrayList<>();
    private List<Head> headList = new ArrayList<>();

    public GoodsPersenter(GoodsContract.View goodView) {
        this.goodView = goodView;
        goodView.setPresenter(this);
        this.homeModel = new IGoodsModelImpl();
    }

    @Override
    public void start() {
        goodView.showProgress();
        homeModel.loadHomeList(new NetWorkCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                goodView.showData(baseBean);
                Gson gson = new Gson();
                String data = baseBean.getData();
                Type type = new TypeToken<List<ShopEntity>>() {
                }.getType();
                List<ShopEntity> shopEntityList = gson.fromJson(data, type);
                for (int i = 0; i < shopEntityList.size(); i++) {
                    list = shopEntityList.get(i).getList();
                    Head head = new Head();
                    head.setInfo(shopEntityList.get(i).getName());
                    headList.add(head);
                }
                for (int j = 0; j < headList.size(); j++) {
                    Head headOne = headList.get(j);
                    for (int k = 0; k < shopEntityList.get(j).getList().size(); k++) {
                        Data dataEntity = new Data();
                        dataEntity.setHeadId(j);
                        dataEntity.setHeadIndex(j);
                        ShopEntity.ListBean bean = shopEntityList.get(j).getList().get(k);
                        dataEntity.setTitle(bean.getName());
                        dataEntity.setContent(bean.getForm());
                        dataEntity.setNewPrice(bean.getNewPrice());
                        dataEntity.setOldPrice(bean.getOldPrice());
                        dataEntity.setSells("月销售" + bean.getMonthSaleNum() + "份");
                        dataEntity.setPic(bean.getIcon());
                        /**
                         * 外层每循环一次，内层开始循环到第一条的时候，为右边的头标签设置一个下标值
                         * 防止其重复，吧右侧普通条目的集合长度作为下标值
                         * 如 第一次循环集合长度为0，右侧第一个下标值=0
                         * 第二次循环集合长度为10，右侧第二个下标值 = 10
                         * 一次类推
                         * 这样左侧就可以通过右侧头标签的下标值进行联动操作
                         */
                        if (k == 0) {
                            //对应的组元素中第一条数据的下标
                            headOne.setGroupFirstIndex(dataList.size());
                        }
                        dataList.add(dataEntity);
                    }
                }
                goodView.getList(headList,dataList);
                goodView.dimissProgress();
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                goodView.showMessage(errorMsg);
                goodView.dimissProgress();
            }

            @Override
            public void onFail(String netOff) {

            }
        });
    }

    public void getListAll(List<Head> headList,List<Data> dataList){

    }

}
