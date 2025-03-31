package com.mredust.mredustusersystem.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mredust.mredustusersystem.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Mredust
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2025-03-31 18:28:00
 * @Entity com.mredust.mredustusersystem.model.entity.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


}
