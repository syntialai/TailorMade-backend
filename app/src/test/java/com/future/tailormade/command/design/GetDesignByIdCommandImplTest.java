package com.future.tailormade.command.design;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.design.impl.GetDesignByIdCommandImpl;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.design.Design;
import com.future.tailormade.payload.response.design.GetDesignByIdResponse;
import com.future.tailormade.repository.DesignRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

public class GetDesignByIdCommandImplTest extends BaseTest {

    @InjectMocks
    private GetDesignByIdCommandImpl command;

    @Mock
    private DesignRepository designRepository;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Override
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(designRepository);
    }

    @Test
    public void getDesignById_success() {
        GetDesignByIdResponse expectedResponse = createGetDesignByIdResponse();

        Mockito.when(designRepository.findById(DESIGN_ID)).thenReturn(Mono.just(createDesign()));

        GetDesignByIdResponse actualResponse = command.execute(DESIGN_ID).block();

        Mockito.verify(designRepository).findById(DESIGN_ID);
        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void getDesignById_notFound() {
        Mockito.when(designRepository.findById(DESIGN_ID)).thenReturn(Mono.empty());

        try {
            command.execute(DESIGN_ID).block();
        } catch (NotFoundException exception) {
            Mockito.verify(designRepository).findById(DESIGN_ID);
        }
    }

    private GetDesignByIdResponse createGetDesignByIdResponse() {
        return GetDesignByIdResponse.builder()
                .id(DESIGN_ID)
                .build();
    }

    private Design createDesign() {
        return Design.builder()
                .id(DESIGN_ID)
                .build();
    }
}
