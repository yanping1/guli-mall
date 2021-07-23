package com.yp.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yp.common.utils.PageUtils;
import com.yp.mall.ware.entity.PurchaseDetailEntity;

import java.util.List;
import java.util.Map;

/**
 * @author firenay
 * @email 1046762075@qq.com
 * @date 2020-06-06 21:09:28
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);

	List<PurchaseDetailEntity> listDetailByPurchaseId(Long id);
}

