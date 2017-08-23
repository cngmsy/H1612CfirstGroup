package com.jiyun.qcloud.dashixummoban.ui.home1.shoppingcar;

import com.jiyun.qcloud.dashixummoban.base.BaseBean;
import com.jiyun.qcloud.dashixummoban.base.IBasePresenter;
import com.jiyun.qcloud.dashixummoban.base.IBaseView;
import com.jiyun.qcloud.dashixummoban.entity.Data;
import com.jiyun.qcloud.dashixummoban.entity.Head;
import com.jiyun.qcloud.dashixummoban.ui.home1.HomeContract;

import java.util.List;

/**
 * Created by admin on 2017/8/22.
 */

public class GoodsContract {
    public interface View extends IBaseView<Persenter>{
        void showData(BaseBean baseBean);
        void getList(List<Head> headList, List<Data> dataList);
    }

    public interface Persenter extends IBasePresenter{

    }
}
