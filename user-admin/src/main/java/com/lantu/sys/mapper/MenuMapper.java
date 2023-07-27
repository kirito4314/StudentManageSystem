package com.lantu.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lantu.sys.entity.Menu;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author laocai
 * @since 2023-07-24
 */
public interface MenuMapper extends BaseMapper<Menu> {
    public List<Menu> getMenuListByUserId(@Param("userId") Integer userId,
                                          @Param("pid") Integer pid);
}

