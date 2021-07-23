package com.yp.mall.member.feign;

import com.yp.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Title: CouponFeignService</p>
 * Description：
 * date：2020/5/30 13:35
 */
@FeignClient("mall-coupon")
public interface CouponFeignService {

	@RequestMapping("/coupon/coupon/member/list")
	R memberCoupons();
}
