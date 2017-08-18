package com.os.services.impl;

import com.os.bean.vos.OrderVO;
import com.os.modelview.OrderPage;
import com.os.services.IMetaDataFacadeService;
import com.os.bean.vos.HangupTabelInfoVO;
import com.os.bean.vos.OrderItemVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jian zhu on 05/27/2017.
 */
@Component("MetaDataFacadeService")
public class MetaDataFacadeServiceImpl implements IMetaDataFacadeService {
    @Override
    public List<HangupTabelInfoVO> queryHangupTableInfo() {
        List<HangupTabelInfoVO> results = new ArrayList<HangupTabelInfoVO>();

        return results;
    }

    @Override
    public List<OrderItemVO> queryItemsByPayno(String day, String payno) {
        return null;
    }

    @Override
    public boolean hasSellOffWarningInfo() {
        return false;
    }
}
