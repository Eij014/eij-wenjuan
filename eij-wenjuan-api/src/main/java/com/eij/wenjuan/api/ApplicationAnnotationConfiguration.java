package com.eij.wenjuan.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;


/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-02-01
 */
@Configuration
public class ApplicationAnnotationConfiguration implements ApplicationContextAware, ImportAware {
    private static final String PROPERTY_NAME = "application-annotation-config";
    private MapPropertySource propertySource;

    public ApplicationAnnotationConfiguration() {
    }

    public void setImportMetadata(AnnotationMetadata importMetadata) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(importMetadata.getAnnotationAttributes(EijBootApplication.class.getName()));
        if (attributes == null) {
            //  throw new IllegalAccessException("@EijBootApplication is not present on importing class " + importMetadata.getClassName());
            System.out.println("@EijBootApplication is not present on importing class " + importMetadata.getClassName());
        } else {
            String applicationName = attributes.getString("name");
            if (StringUtils.hasText(applicationName)) {
                ((Map) this.propertySource.getSource()).put("spring.application.name", applicationName);
            }
        }
    }

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        if (context.getEnvironment() instanceof ConfigurableEnvironment) {
            this.propertySource = new MapPropertySource("application-annotation-config", new LinkedHashMap());
            ((ConfigurableEnvironment) context.getEnvironment()).getPropertySources().addFirst(this.propertySource);
        }
    }
}
