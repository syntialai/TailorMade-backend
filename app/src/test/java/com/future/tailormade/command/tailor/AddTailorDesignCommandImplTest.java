package com.future.tailormade.command.tailor;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.tailor.impl.AddTailorDesignCommandImpl;
import com.future.tailormade.exceptions.UnauthorizedException;
import com.future.tailormade.model.entity.design.Design;
import com.future.tailormade.model.entity.user.TailorDesign;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.model.enums.RoleEnum;
import com.future.tailormade.payload.request.tailor.AddTailorDesignRequest;
import com.future.tailormade.payload.response.tailor.AddOrEditTailorDesignResponse;
import com.future.tailormade.repository.DesignRepository;
import com.future.tailormade.repository.UserRepository;
import com.future.tailormade.service.SequenceService;
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

import java.util.ArrayList;

public class AddTailorDesignCommandImplTest extends BaseTest {

    private static final String DESCRIPTION = "Description";
    private static final String DESIGN_IMAGE = "Design image";
    private static final Double DESIGN_PRICE = 50000.0;
    private static final Double DESIGN_DISCOUNT = 0.0;

    @InjectMocks
    private AddTailorDesignCommandImpl command;

    @Mock
    private DesignRepository designRepository;
    
    @Mock
    private UserRepository userRepository;

    @Mock
    private SequenceService sequenceService;
    
    @Captor
    private ArgumentCaptor<String> tailorIdCaptor;

    @Captor
    private ArgumentCaptor<RoleEnum> tailorRoleCaptor;

    @Captor
    private ArgumentCaptor<String> designTitleCaptor;

    @Captor
    private ArgumentCaptor<String> designTypeCaptor;

    @Captor
    private ArgumentCaptor<Design> designCaptor;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Override
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testAddTailorDesign_success() {
        User user = createUser(null);
        User editedUser = createUser(createTailorDesign(DESIGN_TITLE));
        Design design = createDesign(DESIGN_TITLE);
        AddOrEditTailorDesignResponse expectedResponse = createAddOrEditTailorDesignResponse();

        Mockito.when(userRepository.findByIdAndRole(
                ArgumentMatchers.anyString(), ArgumentMatchers.any(RoleEnum.class))
        ).thenReturn(Mono.just(user));
        Mockito.when(sequenceService.generateId(
                ArgumentMatchers.anyString(), ArgumentMatchers.anyString()
        )).thenReturn(Mono.just(DESIGN_ID));
        Mockito.when(designRepository.save(
                ArgumentMatchers.any(Design.class))).thenReturn(Mono.just(design));
        Mockito.when(userRepository.save(
                ArgumentMatchers.any(User.class))).thenReturn(Mono.just(editedUser));

        AddOrEditTailorDesignResponse actualResponse =
                command.execute(createAddTailorDesignRequest()).block();

        Mockito.verify(userRepository)
                .findByIdAndRole(tailorIdCaptor.capture(), tailorRoleCaptor.capture());
        Assert.assertEquals(tailorIdCaptor.getValue(), TAILOR_ID);
        Assert.assertEquals(tailorRoleCaptor.getValue(), TAILOR_ROLE);

        Mockito.verify(sequenceService)
                .generateId(designTitleCaptor.capture(), designTypeCaptor.capture());
        Assert.assertEquals(designTitleCaptor.getValue(), DESIGN_TITLE);
        Assert.assertEquals(designTypeCaptor.getValue(), SequenceGeneratorUtil.DESIGN);

        Mockito.verify(designRepository).save(designCaptor.capture());
        Assert.assertEquals(designCaptor.getValue(), design);

        Mockito.verify(userRepository).save(userCaptor.capture());
        Assert.assertEquals(userCaptor.getValue(), editedUser);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testAddTailorDesign_tailorNotFound() {
        Mockito.when(userRepository.findByIdAndRole(
                ArgumentMatchers.anyString(), ArgumentMatchers.any(RoleEnum.class))
        ).thenReturn(Mono.empty());

        try {
            command.execute(createAddTailorDesignRequest()).block();
        } catch (UnauthorizedException exception) {
            Mockito.verify(userRepository)
                    .findByIdAndRole(tailorIdCaptor.capture(), tailorRoleCaptor.capture());
            Assert.assertEquals(tailorIdCaptor.getValue(), TAILOR_ID);
            Assert.assertEquals(tailorRoleCaptor.getValue(), TAILOR_ROLE);
        }
    }

    private AddTailorDesignRequest createAddTailorDesignRequest() {
        return AddTailorDesignRequest.builder()
                .tailorId(TAILOR_ID)
                .title(DESIGN_TITLE)
                .image(DESIGN_IMAGE)
                .price(DESIGN_PRICE)
                .discount(DESIGN_DISCOUNT)
                .description(DESCRIPTION)
                .build();
    }

    private AddOrEditTailorDesignResponse createAddOrEditTailorDesignResponse() {
        return AddOrEditTailorDesignResponse.builder()
                .tailorId(TAILOR_ID)
                .tailorName(TAILOR_NAME)
                .id(DESIGN_ID)
                .title(DESIGN_TITLE)
                .image(DESIGN_IMAGE)
                .price(DESIGN_PRICE)
                .discount(DESIGN_DISCOUNT)
                .description(DESCRIPTION)
                .active(true)
                .build();
    }

    private User createUser(TailorDesign design) {
        ArrayList<TailorDesign> designs = new ArrayList<>();
        if (design != null) {
            designs.add(design);
        }
        return User.builder()
                .id(TAILOR_ID)
                .name(TAILOR_NAME)
                .role(TAILOR_ROLE)
                .designs(designs)
                .build();
    }

    private Design createDesign(String title) {
        return Design.builder()
                .tailorId(TAILOR_ID)
                .tailorName(TAILOR_NAME)
                .id(DESIGN_ID)
                .title(title)
                .image(DESIGN_IMAGE)
                .price(DESIGN_PRICE)
                .discount(DESIGN_DISCOUNT)
                .description(DESCRIPTION)
                .active(true)
                .build();
    }

    private TailorDesign createTailorDesign(String title) {
        return TailorDesign.builder()
                .id(DESIGN_ID)
                .image(DESIGN_IMAGE)
                .title(title)
                .build();
    }
}
