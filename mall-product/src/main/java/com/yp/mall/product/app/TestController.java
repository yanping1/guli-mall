package com.yp.mall.product.app;

import com.yp.common.utils.R;
import com.yp.mall.product.feign.CouponFeignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: guli-mall
 * @ClassName: TestController
 * @Description: TODO(一句话描述该类的功能)
 * @Author: Administrator
 * @Date: 2021/7/19 16:11
 */
@RestController
@RequestMapping("test")
@Api(tags = "测试类")
public class TestController {

    @Autowired
    private CouponFeignService feignService;


    @GetMapping("/member/list")
    @ApiOperation("测试")
    public R memberCoupons() {
        R r = feignService.memberCoupons();
        return R.ok().put("coupons", r.get("coupons"));
    }

}
