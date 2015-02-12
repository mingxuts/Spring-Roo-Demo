package com.paperuni.demo.web;

import com.paperuni.demo.model.TdTask;
import com.paperuni.demo.model.TdUserinfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.converter.RooConversionService;

/**
 * A central place to register application converters and formatters. 
 */
@RooConversionService
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
	}

	public Converter<TdUserinfo, String> getTdUserinfoToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.paperuni.demo.model.TdUserinfo, java.lang.String>() {
            public String convert(TdUserinfo tdUserinfo) {
                return new StringBuilder().append(tdUserinfo.getPreferName()).append("<").append(tdUserinfo.getEmail()).append(">").toString();
            }
        };
    }

	public Converter<TdTask, String> getTdTaskToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.paperuni.demo.model.TdTask, java.lang.String>() {
            public String convert(TdTask tdTask) {            	
                return new StringBuilder().append(tdTask.getOrderId()).append(' ').append(tdTask.getStatus()).append(' ').toString();
            }
        };
    }
}
