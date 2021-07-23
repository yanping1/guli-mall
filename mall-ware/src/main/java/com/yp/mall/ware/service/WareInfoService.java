package com.yp.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yp.common.utils.PageUtils;
import com.yp.mall.ware.entity.WareInfoEntity;
import com.yp.mall.ware.vo.FareVo;

import java.util.Map;

/**
 * 仓库信息
 *
 * @author firenay
 * @email 1046762075@qq.com
 * @date 2020-06-06 21:09:28
 */
public interface WareInfoService extends IService<WareInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

	/**
	 * 根据收获地址计算运费
	 */
	FareVo getFare(Long addrId);
}

