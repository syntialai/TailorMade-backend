package com.future.tailormade.command.auth;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.auth.impl.SignInCommandImpl;
import com.future.tailormade.component.CustomPasswordEncoder;
import com.future.tailormade.component.JwtTokenProvider;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.exceptions.UnauthorizedException;
import com.future.tailormade.model.entity.auth.Token;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.payload.request.auth.SignInRequest;
import com.future.tailormade.payload.response.auth.SignInResponse;
import com.future.tailormade.payload.response.user.GetUserByIdResponse;
import com.future.tailormade.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

public class SignInCommandImplTest extends BaseTest {

    private static final String USER_PASSWORD = "USER_PASSWORD";
    private static final String USER_PASSWORD_ENCODED = "USER_PASSWORD_ENCODED";
    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private static final String REFRESH_TOKEN = "REFRESH_TOKEN";

    @InjectMocks
    private SignInCommandImpl command;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CustomPasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Override
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(
                userRepository,
                passwordEncoder,
                jwtTokenProvider
        );
    }

    @Test
    public void testSignIn_success() {
        User user = createUser();
        SignInResponse expectedResponse = createSignInResponse();

        Mockito.when(userRepository.findByEmail(USER_EMAIL)).thenReturn(Mono.just(user));
        Mockito.when(passwordEncoder.encode(USER_PASSWORD)).thenReturn(USER_PASSWORD_ENCODED);
        Mockito.when(jwtTokenProvider.generateAccessToken(user)).thenReturn(ACCESS_TOKEN);
        Mockito.when(jwtTokenProvider.generateRefreshToken(user)).thenReturn(REFRESH_TOKEN);

        SignInResponse actualResponse = command.execute(createSignInRequest()).block();

        Mockito.verify(userRepository).findByEmail(USER_EMAIL);
        Mockito.verify(passwordEncoder).encode(USER_PASSWORD);
        Mockito.verify(jwtTokenProvider).generateAccessToken(user);
        Mockito.verify(jwtTokenProvider).generateRefreshToken(user);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testSignIn_notFound() {
        Mockito.when(userRepository.findByEmail(USER_EMAIL)).thenThrow(new NotFoundException());

        try {
            command.execute(createSignInRequest()).block();
        } catch (NotFoundException exception) {
            Mockito.verify(userRepository).findByEmail(USER_EMAIL);
        }
    }

    @Test
    public void testSignIn_wrongPassword() {
        User user = createUser();
        user.setPassword(USER_PASSWORD);

        Mockito.when(userRepository.findByEmail(USER_EMAIL)).thenReturn(Mono.just(user));
        Mockito.when(passwordEncoder.encode(USER_PASSWORD)).thenReturn(USER_PASSWORD_ENCODED);

        try {
            command.execute(createSignInRequest()).block();
        } catch (UnauthorizedException exception) {
            Mockito.verify(userRepository).findByEmail(USER_EMAIL);
            Mockito.verify(passwordEncoder).encode(USER_PASSWORD);
        }
    }

    @Test
    public void testSignIn_wrongRole() {
        User user = createUser();
        user.setRole(TAILOR_ROLE);

        Mockito.when(userRepository.findByEmail(USER_EMAIL)).thenReturn(Mono.just(user));
        Mockito.when(passwordEncoder.encode(USER_PASSWORD)).thenReturn(USER_PASSWORD);

        try {
            command.execute(createSignInRequest()).block();
        } catch (UnauthorizedException exception) {
            Mockito.verify(userRepository).findByEmail(USER_EMAIL);
            Mockito.verify(passwordEncoder).encode(USER_PASSWORD);
        }
    }

    private User createUser() {
        return User.builder()
                .id(USER_ID)
                .name(USER_NAME)
                .email(USER_EMAIL)
                .password(USER_PASSWORD_ENCODED)
                .role(USER_ROLE)
                .build();
    }

    private GetUserByIdResponse createUserResponse() {
        return GetUserByIdResponse.builder()
                .id(USER_ID)
                .name(USER_NAME)
                .role(USER_ROLE)
                .build();
    }

    private SignInRequest createSignInRequest() {
        return SignInRequest.builder()
                .username(USER_EMAIL)
                .password(USER_PASSWORD)
                .role(USER_ROLE)
                .build();
    }

    private SignInResponse createSignInResponse() {
        return SignInResponse.builder()
                .token(Token.builder()
                        .access(ACCESS_TOKEN)
                        .refresh(REFRESH_TOKEN)
                        .build())
                .user(createUserResponse())
                .build();
    }
}
