package com.os.services.impl;

import com.os.services.IMetaDataFacadeService;
import com.os.vos.HangupTabelInfoVO;
import com.os.vos.OrderItemVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jian zhu on 05/27/2017.
 */
@Component("MetaDataFacadeService")
public class MetaDataFacadeService implements IMetaDataFacadeService {
    @Override
    public List<HangupTabelInfoVO> queryHangupTableInfo() {
        return null;
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
