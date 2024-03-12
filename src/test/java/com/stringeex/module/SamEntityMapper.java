package com.stringeex.module;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SamEntityMapper {
    @Select("SELECT * FROM SAM_ENTITY WHERE id = #{id}")
    SamEntity find(@Param("id") Long id);
}
