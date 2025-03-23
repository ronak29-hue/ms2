package com.api.gateway.service.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig  {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder)
        {
         //route(information[url,predicate,Filters]
            return builder
                    .routes()//we have passed blank route
                    .route(r->
                           r.path("/category/**")//predicate
                            .filters(
                                    f->f.rewritePath("/category/?(?<segment>.*)","/${segment}")
                                    .addResponseHeader("X-CUSTOM_HEADER","added by Krishna")
                            )
                            .uri("lb://category-service")
                    )
                    .route(r->
                            r.path("/course/**")//predicate
                                    .filters(
                                            f->f.rewritePath("/course/?(?<segment>.*)","/${segment}")
                                                    .addResponseHeader("X-CUSTOM_HEADER","added by Krishna")
                                    )
                                    .uri("lb://course-service")
                    )
                    .route(r->
                            r.path("/vedio/**")//predicate
                                    .filters(
                                            f->f.rewritePath("/vedio/?(?<segment>.*)","/${segment}")
                                                    .addResponseHeader("X-CUSTOM_HEADER","added by Krishna")
                                    )
                                    .uri("lb://vedio-services")
                    )

                    .build();
        }
}
