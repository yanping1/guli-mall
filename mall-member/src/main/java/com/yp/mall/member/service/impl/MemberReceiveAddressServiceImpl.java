package com.yp.mall.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yp.common.utils.PageUtils;
import com.yp.common.utils.Query;
import com.yp.mall.member.dao.MemberReceiveAddressDao;
import com.yp.mall.member.entity.MemberReceiveAddressEntity;
import com.yp.mall.member.service.MemberReceiveAddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("memberReceiveAddressService")
public class MemberReceiveAddressServiceImpl extends ServiceImpl<MemberReceiveAddressDao, MemberReceiveAddressEntity> implements MemberReceiveAddressService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberReceiveAddressEntity> page = this.page(
                new Query<MemberReceiveAddressEntity>().getPage(params),
                new QueryWrapper<MemberReceiveAddressEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public List<MemberReceiveAddressEntity> getAddress(Long memberId) {

		List<MemberReceiveAddressEntity> member_id = this.list(new QueryWrapper<MemberReceiveAddressEntity>().eq("member_id", memberId));
		return member_id;
	}
}