package com.stringeex.core.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class BaseController {

    protected final Log LOGGER = LogFactory.getLog(getClass());
}
