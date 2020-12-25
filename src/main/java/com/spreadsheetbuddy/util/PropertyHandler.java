package com.spreadsheetbuddy.util;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySources;

public class PropertyHandler {

    PropertySources propertySources;
    PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer;


    public PropertySource<?> getProperty(String name) {
        return this.propertySources.get(name);
    }

    public PropertyHandler() {
        propertySources = propertySourcesPlaceholderConfigurer.getAppliedPropertySources();
    }

}
