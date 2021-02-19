package com.future.tailormade.command.tailor;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.tailor.impl.DeleteTailorDesignByIdCommandImpl;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.user.TailorDesign;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.model.enums.RoleEnum;
import com.future.tailormade.payload.request.tailor.DeleteTailorDesignRequest;
import com.future.tailormade.repository.DesignRepository;
import com.future.tailormade.repository.UserRepository;
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

public class DeleteTailorDesignCommandImplTest extends BaseTest {

    @InjectMocks
    private DeleteTailorDesignByIdCommandImpl command;

    @Mock
    private DesignRepository designRepository;

    @Mock
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<String> tailorIdCaptor;

    @Captor
    private ArgumentCaptor<RoleEnum> tailorRoleCaptor;

    @Captor
    private ArgumentCaptor<String> designIdCaptor;

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
    public void testDeleteTailorDesign_success() {
        User user = createUser(createTailorDesign(TAILOR_NAME));
        User editedUser = createUser(null);

        Mockito.when(designRepository.deleteByTailorIdAndId(
                ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
        ).thenReturn(Mono.empty());
        Mockito.when(userRepository.findByIdAndRole(
                ArgumentMatchers.anyString(), ArgumentMatchers.any(RoleEnum.class))
        ).thenReturn(Mono.just(user));
        Mockito.when(userRepository.save(
                ArgumentMatchers.any(User.class))
        ).thenReturn(Mono.just(editedUser));

        command.execute(createDeleteTailorDesignRequest()).block();

        Mockito.verify(designRepository)
                .deleteByTailorIdAndId(tailorIdCaptor.capture(), designIdCaptor.capture());
        Assert.assertEquals(tailorIdCaptor.getValue(), TAILOR_ID);
        Assert.assertEquals(designIdCaptor.getValue(), DESIGN_ID);

        Mockito.verify(userRepository)
                .findByIdAndRole(tailorIdCaptor.capture(), tailorRoleCaptor.capture());
        Assert.assertEquals(tailorIdCaptor.getValue(), TAILOR_ID);
        Assert.assertEquals(tailorRoleCaptor.getValue(), TAILOR_ROLE);

        Mockito.verify(userRepository).save(userCaptor.capture());
        Assert.assertEquals(userCaptor.getValue(), editedUser);
    }

    @Test
    public void testDeleteTailorDesign_userNotFound() {
        Mockito.when(designRepository.deleteByTailorIdAndId(
                ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
        ).thenReturn(Mono.empty());
        Mockito.when(userRepository.findByIdAndRole(
                ArgumentMatchers.anyString(), ArgumentMatchers.any(RoleEnum.class))
        ).thenReturn(Mono.empty());

        try {
            command.execute(createDeleteTailorDesignRequest()).block();
        } catch (NotFoundException exception) {
            Mockito.verify(designRepository)
                    .deleteByTailorIdAndId(tailorIdCaptor.capture(), designIdCaptor.capture());
            Assert.assertEquals(tailorIdCaptor.getValue(), TAILOR_ID);
            Assert.assertEquals(designIdCaptor.getValue(), DESIGN_ID);

            Mockito.verify(userRepository)
                    .findByIdAndRole(tailorIdCaptor.capture(), tailorRoleCaptor.capture());
            Assert.assertEquals(tailorIdCaptor.getValue(), TAILOR_ID);
            Assert.assertEquals(tailorRoleCaptor.getValue(), TAILOR_ROLE);
        }
    }

    private DeleteTailorDesignRequest createDeleteTailorDesignRequest() {
        return DeleteTailorDesignRequest.builder()
                .id(DESIGN_ID)
                .tailorId(TAILOR_ID)
                .build();
    }

    private User createUser(TailorDesign design) {
        ArrayList<TailorDesign> designs = new ArrayList<>();
        if (design != null) {
            designs.add(design);
        }
        return User.builder()
                .id(TAILOR_ID)
                .role(TAILOR_ROLE)
                .designs(designs)
                .build();
    }

    private TailorDesign createTailorDesign(String title) {
        return TailorDesign.builder()
                .id(DESIGN_ID)
                .title(title)
                .build();
    }
}
