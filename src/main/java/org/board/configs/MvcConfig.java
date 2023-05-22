package org.board.configs;

import lombok.RequiredArgsConstructor;
import org.board.configs.interceptors.SiteConfigInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {
    @Value("${file.upload.path}")
    private String fileUploadPath;
    private final SiteConfigInterceptor siteConfigInterceptor; /** 사이트 설정 유지 인터셉터 */
    @Override /** 컨트롤러 없이 뷰를 사용할 수 있음 */
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("main/index");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /** 정적 경로 설정 */
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:///" + fileUploadPath);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(siteConfigInterceptor)
                .addPathPatterns("/**"); /** 전체 모든 URL에 적용 */
    }
}
