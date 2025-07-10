package pers.darren.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.darren.entity.Role;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author Darren Luo
 * @since 2025-07-10 12:21:18
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
