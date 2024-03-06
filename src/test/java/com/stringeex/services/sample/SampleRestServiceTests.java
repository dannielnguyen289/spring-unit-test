package com.stringeex.services.sample;

import com.stringeex.services.sample.domain.ListEntityRes;
import com.stringeex.services.sample.impl.SampleRestRestServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    @BeforeEach
    public void init() {
    }

    @Test
    public void SampleRestService_GetList_ListEmpty() {

        ListEntityRes listEntityRes = sampleRestService.getList(1L, 10);

        Assertions.assertThat(listEntityRes).isNotNull();
    }
}
