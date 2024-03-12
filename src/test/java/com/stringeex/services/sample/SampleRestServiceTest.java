package com.stringeex.services.sample;

import com.stringeex.services.sample.domain.Entity;
import com.stringeex.services.sample.domain.ListEntityRes;
import com.stringeex.services.sample.impl.SampleRestRestServiceImpl;
import com.stringeex.services.sample.query.SelectEntityPrt;
import com.stringeex.services.sample.query.SelectEntityRss;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SampleRestServiceTest {
    private final Log LOGGER = LogFactory.getLog(getClass());

    @Mock
    SampleRestRepository sampleRestRepository;

    @InjectMocks
    private SampleRestRestServiceImpl sampleRestService;

    @BeforeEach
    void setUp() {
        this.LOGGER.info("[SETUP]: Execute somethings");
    }

    @AfterEach
    void tearDown() {
        this.LOGGER.info("[TEAR_DOWN]: Execute somethings");
    }

    @Test
    void getList_empty() {
        when(sampleRestRepository.count(Mockito.any(SelectEntityPrt.class))).thenReturn(0L);
        when(sampleRestRepository.list(Mockito.any(SelectEntityPrt.class))).thenReturn(new ArrayList<>());

        ListEntityRes listEntityRes = sampleRestService.getList(1L, 10);

        Assertions.assertNotNull(listEntityRes);
        Assertions.assertNotNull(listEntityRes.getList());
        Assertions.assertNotNull(listEntityRes.getPagination());
        Assertions.assertEquals(0, listEntityRes.getList().size());
        Assertions.assertEquals(0, listEntityRes.getPagination().getTotalItems());
        Assertions.assertEquals(1, listEntityRes.getPagination().getTotalPages());
        Assertions.assertEquals(1, listEntityRes.getPagination().getPageNo());
        Assertions.assertEquals(10, listEntityRes.getPagination().getPageSize());
    }

    @Test
    void getList_with_three_pages() {
        List<SelectEntityRss> listSelectEntityRss = new ArrayList<>();

        for (int i = 0; i < 10; i ++) {
            listSelectEntityRss.add(SelectEntityRss.builder()
                    .id(Long.parseLong(i + 1 + ""))
                    .name(String.format("Entity #%d", i+ 1))
                    .description(String.format("Entity #%d", i+ 1))
                    .build());
        }

        when(sampleRestRepository.count(Mockito.any(SelectEntityPrt.class))).thenReturn(32L);
        when(sampleRestRepository.list(Mockito.any(SelectEntityPrt.class))).thenReturn(listSelectEntityRss);

        ListEntityRes listEntityRes = sampleRestService.getList(2L, 10);

        Assertions.assertNotNull(listEntityRes);
        Assertions.assertNotNull(listEntityRes.getList());
        Assertions.assertNotNull(listEntityRes.getPagination());
        Assertions.assertEquals(10, listEntityRes.getList().size());
        Assertions.assertEquals(32L, listEntityRes.getPagination().getTotalItems());
        Assertions.assertEquals(4L, listEntityRes.getPagination().getTotalPages());
        Assertions.assertEquals(2L, listEntityRes.getPagination().getPageNo());
        Assertions.assertEquals(10, listEntityRes.getPagination().getPageSize());

        for (Entity item: listEntityRes.getList()) {
            Assertions.assertNotNull(item.getId());
            Assertions.assertNotNull(item.getName());
            Assertions.assertNotNull(item.getDescription());
            System.out.printf("%0,3d - %s %s%n", item.getId(), item.getName(), item.getDescription());
        }
    }
}