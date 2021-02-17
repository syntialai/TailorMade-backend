package com.future.tailormade.command.design;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.design.impl.GetDesignsCommandImpl;
import com.future.tailormade.model.entity.design.Design;
import com.future.tailormade.payload.request.design.GetDesignsRequest;
import com.future.tailormade.payload.response.base.BasePagingResponse;
import com.future.tailormade.payload.response.design.GetDesignsResponse;
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

public class GetDesignsCommandImplTest extends BaseTest {

    @InjectMocks
    private GetDesignsCommandImpl command;

    @Mock
    private DesignRepository designRepository;

    @Captor
    private ArgumentCaptor<String> titleCaptor;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Override
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(designRepository);
    }

    @Test
    public void getDesigns_success() {
        Design expectedDesign = createDesign();
        BasePagingResponse<GetDesignsResponse> expectedResponse = createPaging(
                createGetDesignsResponse()
        );

        Mockito.when(
                designRepository.findAllByTitleStartsWith(
                        ArgumentMatchers.any(String.class),
                        ArgumentMatchers.any(Pageable.class)
                )
        ).thenReturn(Flux.just(expectedDesign));
        Mockito.when(
                designRepository.countAllByTitleStartsWith(ArgumentMatchers.any(String.class))
        ).thenReturn(Mono.just(ITEM_COUNT));

        BasePagingResponse<GetDesignsResponse> actualResponse = command
                .execute(createGetDesignsRequest())
                .block();

        Mockito.verify(designRepository)
                .findAllByTitleStartsWith(titleCaptor.capture(), pageableCaptor.capture());
        Assert.assertEquals(titleCaptor.getValue(), DESIGN_TITLE);
        Assert.assertEquals(pageableCaptor.getValue(), getPageable());

        Mockito.verify(designRepository).countAllByTitleStartsWith(titleCaptor.capture());
        Assert.assertEquals(titleCaptor.getValue(), DESIGN_TITLE);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void getDesigns_notFound() {
        BasePagingResponse<GetDesignsResponse> expectedResponse = createPaging();

        Mockito.when(
                designRepository.findAllByTitleStartsWith(
                        ArgumentMatchers.any(String.class),
                        ArgumentMatchers.any(Pageable.class)
                )
        ).thenReturn(Flux.empty());
        Mockito.when(
                designRepository.countAllByTitleStartsWith(ArgumentMatchers.any(String.class))
        ).thenReturn(Mono.just(ITEM_EMPTY_COUNT));

        BasePagingResponse<GetDesignsResponse> actualResponse = command
                .execute(createGetDesignsRequest())
                .block();

        Mockito.verify(designRepository)
                .findAllByTitleStartsWith(titleCaptor.capture(), pageableCaptor.capture());
        Assert.assertEquals(titleCaptor.getValue(), DESIGN_TITLE);
        Assert.assertEquals(pageableCaptor.getValue(), getPageable());

        Mockito.verify(designRepository).countAllByTitleStartsWith(titleCaptor.capture());
        Assert.assertEquals(titleCaptor.getValue(), DESIGN_TITLE);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    private GetDesignsRequest createGetDesignsRequest() {
        return GetDesignsRequest.builder()
                .keyword(DESIGN_TITLE)
                .page(PAGE)
                .itemPerPage(ITEM_PER_PAGE)
                .build();
    }

    private GetDesignsResponse createGetDesignsResponse() {
        return GetDesignsResponse.builder()
                .id(DESIGN_ID)
                .title(DESIGN_TITLE)
                .build();
    }

    private Design createDesign() {
        return Design.builder()
                .id(DESIGN_ID)
                .title(DESIGN_TITLE)
                .build();
    }
}
