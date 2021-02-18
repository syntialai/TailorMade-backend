package com.future.tailormade.service;

import com.future.tailormade.BaseTest;
import com.future.tailormade.model.entity.base.Sequence;
import com.future.tailormade.repository.SequenceRepository;
import com.future.tailormade.service.impl.SequenceServiceImpl;
import com.future.tailormade.utils.SequenceGeneratorUtil;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

public class SequenceServiceImplTest extends BaseTest {

    private static final String TITLE = "TITLE";
    private static final String NAME = "NAME";
    private static final String TITLE_NAME = "NAME_TITL";

    @InjectMocks
    private SequenceServiceImpl sequenceService;

    @Mock
    private SequenceRepository sequenceRepository;

    @Captor
    private ArgumentCaptor<String> sequenceIdCaptor;

    @Captor
    private ArgumentCaptor<Sequence> sequenceCaptor;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Override
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(sequenceRepository);
    }

    @Test
    public void generateId_sequenceIsExist_success() {
        Sequence sequence = createSequence(1L);
        Sequence savedSequence = createSequence(2L);
        String expectedResponse = getSequence(savedSequence);

        Mockito.when(sequenceRepository.findById(
                ArgumentMatchers.anyString())).thenReturn(Mono.just(sequence));
        Mockito.when(sequenceRepository.save(
                ArgumentMatchers.any(Sequence.class))).thenReturn(Mono.just(savedSequence));

        String actualResponse = sequenceService.generateId(TITLE, NAME).block();

        Mockito.verify(sequenceRepository).findById(sequenceIdCaptor.capture());
        Assert.assertEquals(sequenceIdCaptor.getValue(), TITLE_NAME);

        Mockito.verify(sequenceRepository).save(sequenceCaptor.capture());
        Assert.assertEquals(sequenceCaptor.getValue(), savedSequence);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void generateId_sequenceIsEmpty_success() {
        Sequence savedSequence = createSequence(1L);
        String expectedResponse = getSequence(savedSequence);

        Mockito.when(sequenceRepository.findById(
                ArgumentMatchers.anyString())).thenReturn(Mono.empty());
        Mockito.when(sequenceRepository.save(
                ArgumentMatchers.any(Sequence.class))).thenReturn(Mono.just(savedSequence));

        String actualResponse = sequenceService.generateId(TITLE, NAME).block();

        Mockito.verify(sequenceRepository).findById(sequenceIdCaptor.capture());
        Assert.assertEquals(sequenceIdCaptor.getValue(), TITLE_NAME);

        Mockito.verify(sequenceRepository).save(sequenceCaptor.capture());
        Assert.assertEquals(sequenceCaptor.getValue(), savedSequence);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    private String getSequence(Sequence sequence) {
        return SequenceGeneratorUtil.generateSequence(sequence);
    }

    private Sequence createSequence(Long count) {
        return Sequence.builder()
                .id(TITLE_NAME)
                .count(count)
                .build();
    }
}
