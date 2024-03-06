package com.stringeex.services.index;

import com.stringeex.core.base.BaseController;
import com.stringeex.services.index.domain.IndexRes;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@Validated
@RestController("")
public class IndexController extends BaseController {

    @GetMapping("")
    public IndexRes index() {
        IndexRes res = new IndexRes();
        res.setCode(200);
        res.setMessages("Successfully");
        res.setTimestamp(new Date());
        return res;
    }

    @GetMapping("health-check")
    public IndexRes check() {
        IndexRes res = new IndexRes();
        res.setCode(200);
        res.setMessages("Successfully");
        res.setTimestamp(new Date());
        return res;
    }
}
