package com.stringeex.module;

import com.stringeex.module.query.InsertEntityPrt;
import com.stringeex.services.sample.domain.Entity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@MybatisTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SampleRepositoryTest {

    private final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    SampleRepository sampleRepository;

    @Autowired
    SamEntityMapper samEntityMapper;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Insert new entity")
    @Sql(scripts = {"/scripts/sample/empty_data_SAM_ENTITY.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"/scripts/sample/empty_data_SAM_ENTITY.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void insert() {
        InsertEntityPrt params = InsertEntityPrt.builder().name("Just test name").description("Just test description").build();

        sampleRepository.insert(params);

        this.LOGGER.info(String.format("New ID: %d", params.getId()));

        Assertions.assertNotNull(params.getId());

        SamEntity entity = samEntityMapper.find(params.getId());

        Assertions.assertEquals(entity.getId(), params.getId());
        Assertions.assertEquals(entity.getName(), params.getName());
        Assertions.assertEquals(entity.getDescription(), params.getDescription());
        Assertions.assertNotNull(entity.getCreatedAt());
        Assertions.assertNotNull(entity.getUpdatedAt());
    }
}