package ru.netology.spring_boot_rest.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.netology.spring_boot_rest.model.User;
import ru.netology.spring_boot_rest.model.UserArgument;

import java.util.List;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new MyArgumentResolver());
    }

    private static class MyArgumentResolver implements HandlerMethodArgumentResolver {

        @Override
        public boolean supportsParameter(MethodParameter methodParameter) {
            return methodParameter.hasParameterAnnotation(UserArgument.class);
        }

        @Override
        public Object resolveArgument(MethodParameter methodParameter,
                                      ModelAndViewContainer modelAndViewContainer,
                                      NativeWebRequest nativeWebRequest,
                                      WebDataBinderFactory webDataBinderFactory) throws Exception {
            String name = nativeWebRequest.getParameter("user");
            String password = nativeWebRequest.getParameter("password");

            return new User(name, password);
        }
    }
}
