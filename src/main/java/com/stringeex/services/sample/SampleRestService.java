package com.stringeex.services.sample;

import com.stringeex.services.sample.domain.ListEntityRes;

public interface SampleRestService {
    ListEntityRes getList(Long pageNo, Integer pageSize);
}
