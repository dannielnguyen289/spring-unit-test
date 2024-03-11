package com.stringeex.services.sample;

import com.stringeex.services.sample.domain.ListEntityRes;
import com.stringeex.services.sample.impl.SampleRestRestServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SampleRestServiceTests {

    @Mock
    SampleRestRepository sampleRestRepository;

    @InjectMocks
    private SampleRestRestServiceImpl sampleRestService;

    @BeforeAll
    static void initial() {
        System.out.println("initial");
    }

    @AfterAll
    static void cleanup() {
        System.out.println("cleanup");
    }

    @BeforeEach
    void setup() {
        System.out.println("setup");
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearDown");

    }

    @Test
    public void SampleRestService_GetList_ListEmpty() {

        ListEntityRes listEntityRes = sampleRestService.getList(1L, 10);

        System.out.println("Just test");

        Assertions.assertThat(listEntityRes).isNotNull();
    }
}
