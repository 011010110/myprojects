package org.jiang.core.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.jiang.core.bean.User;

@Mapper
public interface UserMapper {

    @Insert("insert into sys_user(id,user_name,password) value(#{id},#{userName},#{password})")
    public void save(User user);
}
