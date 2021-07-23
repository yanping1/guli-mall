package com.yp.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yp.common.utils.PageUtils;
import com.yp.mall.member.entity.MemberEntity;
import com.yp.mall.member.exception.PhoneExistException;
import com.yp.mall.member.exception.UserNameExistException;
import com.yp.mall.member.vo.MemberLoginVo;
import com.yp.mall.member.vo.SocialUser;
import com.yp.mall.member.vo.UserRegisterVo;

import java.util.Map;

/**
 * 会员
 *
 * @author firenay
 * @email 1046762075@qq.com
 * @date 2020-05-30 00:49:16
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

	void register(UserRegisterVo userRegisterVo) throws PhoneExistException, UserNameExistException;

	void checkPhone(String phone) throws PhoneExistException;

	void checkUserName(String username) throws UserNameExistException;

	/**
	 * 普通登录
	 */
	MemberEntity login(MemberLoginVo vo);

	/**
	 * 社交登录
	 */
	MemberEntity login(SocialUser socialUser);
}

