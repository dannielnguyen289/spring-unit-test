package com.stringeex.services.sample;

import com.stringeex.services.sample.query.SelectEntityPrt;
import com.stringeex.services.sample.query.SelectEntityRss;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest
class SampleRestRepositoryTest {
    private final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    SampleRestRepository sampleRestRepository;
    @BeforeEach
    void setUp() {
//        this.LOGGER.info("[SETUP]: Execute script: /scripts/sample/empty_data_SAM_ENTITY.sql");
    }

    @AfterEach
    void tearDown() {
//        this.LOGGER.info("[TEAR_DOWN]: Execute script: /scripts/sample/empty_data_SAM_ENTITY.sql");
    }

    @Test
    @Sql(scripts = {"/scripts/sample/empty_data_SAM_ENTITY.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void count_empty_result() {
        SelectEntityPrt params = new SelectEntityPrt();
        Long count = sampleRestRepository.count(params);

        Assertions.assertEquals(0L, count);
    }

    @Test
    @Sql(scripts = {"/scripts/sample/empty_data_SAM_ENTITY.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void list_empty_result() {
        SelectEntityPrt params = new SelectEntityPrt();
        List<SelectEntityRss> results = sampleRestRepository.list(params);

        Assertions.assertEquals(0, results.size());
    }

    @Test
    @Sql(scripts = {"/scripts/sample/insert_data_SAM_ENTITY.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"/scripts/sample/empty_data_SAM_ENTITY.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void count_with_data() {
        SelectEntityPrt params = new SelectEntityPrt();
        Long count = sampleRestRepository.count(params);

        Assertions.assertEquals(176L, count);
    }

    @Test
    @Sql(scripts = {"/scripts/sample/insert_data_SAM_ENTITY.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"/scripts/sample/empty_data_SAM_ENTITY.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void list_with_data() {
        SelectEntityPrt params = new SelectEntityPrt();
        params.setOffset(10);
        params.setLimit(10);
        List<SelectEntityRss> results = sampleRestRepository.list(params);

        Assertions.assertEquals(10, results.size());

        for (SelectEntityRss item: results) {
            Assertions.assertNotNull(item.getId());
            Assertions.assertNotNull(item.getName());
            Assertions.assertNotNull(item.getDescription());
            System.out.println(String.format("%0,3d - %s %s", item.getId(), item.getName(), item.getDescription()));
        }
    }
}