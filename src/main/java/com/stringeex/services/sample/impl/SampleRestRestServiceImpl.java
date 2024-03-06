package com.stringeex.services.sample.impl;

import com.stringeex.core.base.BaseService;
import com.stringeex.core.pagination.Paginator;
import com.stringeex.services.sample.SampleRestRepository;
import com.stringeex.services.sample.SampleRestService;
import com.stringeex.services.sample.domain.Entity;
import com.stringeex.services.sample.domain.ListEntityRes;
import com.stringeex.services.sample.query.SelectEntityPrt;
import com.stringeex.services.sample.query.SelectEntityRss;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SampleRestRestServiceImpl extends BaseService implements SampleRestService {

    @Autowired
    SampleRestRepository sampleRestRepository;

    @Override
    public ListEntityRes getList(Long pageNo, Integer pageSize) {

        // Declare result
        ListEntityRes listEntityRes =  new ListEntityRes();

        // Pagination
        Paginator paginator = new Paginator(pageNo, pageSize);

        // Select param
        SelectEntityPrt params = new SelectEntityPrt();
        params.setLimit(paginator.getLimit());
        params.setOffset(paginator.getOffset());

        // Count total records
        Long count = sampleRestRepository.count(params);
        paginator.setTotalItems(count);

        if (count <= 0) {
            listEntityRes.setPagination(paginator.toPagination());
            return listEntityRes;
        }

        List<SelectEntityRss> listResultSet = sampleRestRepository.list(params);

        List<Entity> listEntity = new ArrayList<>();
        for(SelectEntityRss item: listResultSet) {
            Entity entity = new Entity();
            BeanUtils.copyProperties(item, entity);
            listEntity.add(entity);
        }

        listEntityRes.setList(listEntity);
        listEntityRes.setPagination(paginator.toPagination());

        return listEntityRes;
    }
}
