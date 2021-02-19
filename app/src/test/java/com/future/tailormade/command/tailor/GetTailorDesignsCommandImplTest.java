package com.future.tailormade.command.tailor;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.tailor.impl.GetTailorDesignsCommandImpl;
import com.future.tailormade.model.entity.design.Design;
import com.future.tailormade.payload.request.tailor.GetTailorDesignsRequest;
import com.future.tailormade.payload.response.base.BasePagingResponse;
import com.future.tailormade.payload.response.tailor.GetTailorDesignsResponse;
import com.future.tailormade.repository.DesignRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GetTailorDesignsCommandImplTest extends BaseTest {

    @InjectMocks
    private GetTailorDesignsCommandImpl command;

    @Mock
    private DesignRepository designRepository;

    @Captor
    private ArgumentCaptor<String> tailorIdCaptor;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Override
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(designRepository);
    }

    @Test
    public void getTailorDesigns_success() {
        Design design = createDesign();
        BasePagingResponse<GetTailorDesignsResponse> expectedResponse = createPaging(
                createGetTailorDesignsResponse()
        );

        Mockito.when(designRepository.findAllByTailorId(
                ArgumentMatchers.anyString(), ArgumentMatchers.any(Pageable.class))
        ).thenReturn(Flux.just(design));
        Mockito.when(designRepository.countAllByTailorId(
                ArgumentMatchers.anyString())).thenReturn(Mono.just(ITEM_COUNT));

        BasePagingResponse<GetTailorDesignsResponse> actualResponse = command
                .execute(createGetTailorDesignsRequest())
                .block();

        Mockito.verify(designRepository)
                .findAllByTailorId(tailorIdCaptor.capture(), pageableCaptor.capture());
        Assert.assertEquals(tailorIdCaptor.getValue(), TAILOR_ID);
        Assert.assertEquals(pageableCaptor.getValue(), getPageable());

        Mockito.verify(designRepository).countAllByTailorId(tailorIdCaptor.capture());
        Assert.assertEquals(tailorIdCaptor.getValue(), TAILOR_ID);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void getTailorDesigns_notFound() {
        BasePagingResponse<GetTailorDesignsResponse> expectedResponse = createPaging();

        Mockito.when(designRepository.findAllByTailorId(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(Pageable.class))
        ).thenReturn(Flux.empty());
        Mockito.when(designRepository.countAllByTailorId(
                ArgumentMatchers.anyString())).thenReturn(Mono.just(ITEM_EMPTY_COUNT));

        BasePagingResponse<GetTailorDesignsResponse> actualResponse = command
                .execute(createGetTailorDesignsRequest())
                .block();

        Mockito.verify(designRepository)
                .findAllByTailorId(tailorIdCaptor.capture(), pageableCaptor.capture());
        Assert.assertEquals(tailorIdCaptor.getValue(), TAILOR_ID);
        Assert.assertEquals(pageableCaptor.getValue(), getPageable());

        Mockito.verify(designRepository).countAllByTailorId(tailorIdCaptor.capture());
        Assert.assertEquals(tailorIdCaptor.getValue(), TAILOR_ID);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    private GetTailorDesignsRequest createGetTailorDesignsRequest() {
        return GetTailorDesignsRequest.builder()
                .tailorId(TAILOR_ID)
                .page(PAGE)
                .itemPerPage(ITEM_PER_PAGE)
                .build();
    }

    private GetTailorDesignsResponse createGetTailorDesignsResponse() {
        return GetTailorDesignsResponse.builder()
                .id(DESIGN_ID)
                .title(DESIGN_TITLE)
                .build();
    }

    private Design createDesign() {
        return Design.builder()
                .id(DESIGN_ID)
                .title(DESIGN_TITLE)
                .tailorId(TAILOR_ID)
                .build();
    }
}
