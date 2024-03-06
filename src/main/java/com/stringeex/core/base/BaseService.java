package com.stringeex.core.base;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BaseService {
    protected final Log LOGGER = LogFactory.getLog(getClass());
}
