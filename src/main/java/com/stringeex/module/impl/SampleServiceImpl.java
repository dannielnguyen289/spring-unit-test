package com.stringeex.module.impl;

import com.stringeex.core.base.BaseService;
import com.stringeex.module.SampleRepository;
import com.stringeex.module.SampleService;
import com.stringeex.module.query.InsertEntityPrt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SampleServiceImpl extends BaseService implements SampleService {

    @Autowired
    SampleRepository sampleRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void generate(Long timestamp) {

        InsertEntityPrt param = new InsertEntityPrt();
        param.setName(String.format("#%d Entity", timestamp));
        param.setDescription(String.format("Generated entity by job timestamp: %d", timestamp));

        sampleRepository.insert(param);
    }
}
