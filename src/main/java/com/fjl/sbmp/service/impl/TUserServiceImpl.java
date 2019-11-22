package com.fjl.sbmp.service.impl;

import com.fjl.sbmp.model.TUser;
import com.fjl.sbmp.dao.TUserMapper;
import com.fjl.sbmp.service.ITUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 范井龙
 * @since 2019-11-22
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

}
