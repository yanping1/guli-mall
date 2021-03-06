package com.yp.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yp.common.utils.PageUtils;
import com.yp.common.utils.Query;
import com.yp.mall.product.dao.SkuSaleAttrValueDao;
import com.yp.mall.product.entity.SkuSaleAttrValueEntity;
import com.yp.mall.product.service.SkuSaleAttrValueService;
import com.yp.mall.product.vo.ItemSaleAttrVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("skuSaleAttrValueService")
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<SkuSaleAttrValueDao, SkuSaleAttrValueEntity> implements SkuSaleAttrValueService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuSaleAttrValueEntity> page = this.page(
                new Query<SkuSaleAttrValueEntity>().getPage(params),
                new QueryWrapper<SkuSaleAttrValueEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public List<ItemSaleAttrVo> getSaleAttrsBuSpuId(Long spuId) {

		SkuSaleAttrValueDao dao = this.baseMapper;
		return dao.getSaleAttrsBuSpuId(spuId);
	}

	@Override
	public List<String> getSkuSaleAttrValuesAsStringList(Long skuId) {

		SkuSaleAttrValueDao dao = this.baseMapper;
		return dao.getSkuSaleAttrValuesAsStringList(skuId);
	}
}