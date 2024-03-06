package com.stringeex.module;

import com.stringeex.module.query.InsertEntityPrt;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SampleRepository {
    void insert(InsertEntityPrt params);
}
