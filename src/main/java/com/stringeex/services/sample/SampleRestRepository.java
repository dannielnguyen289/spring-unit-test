package com.stringeex.services.sample;

import com.stringeex.services.sample.query.SelectEntityPrt;
import com.stringeex.services.sample.query.SelectEntityRss;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SampleRestRepository {
    Long count(SelectEntityPrt params);

    List<SelectEntityRss> list(SelectEntityPrt params);
}
