package com.paperuni.demo.web;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;

import com.paperuni.demo.model.TdMessage;
import com.paperuni.demo.model.TdMessagePK;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.apache.commons.io.IOUtils;
import org.gvnix.addon.web.mvc.jquery.GvNIXWebJQuery;

@RequestMapping("/tdmessages")
@Controller
@RooWebScaffold(path = "tdmessages", formBackingObject = TdMessage.class)
@GvNIXWebJQuery
public class TdMessageController {
	
	@Autowired
	private ConversionService conversionService;
	

	@InitBinder
	protected void initBinder(HttpServletRequest request,
	        ServletRequestDataBinder binder) throws ServletException {
	    binder.registerCustomEditor(byte[].class,
	            new ByteArrayMultipartFileEditor());
	}		


	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid TdMessage tdMessage, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest,
    		@RequestParam("file") MultipartFile multipartFile) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, tdMessage);
            return "tdmessages/create";
        }
        uiModel.asMap().clear();
        tdMessage.setFileContentType(multipartFile.getContentType());
        tdMessageRepository.save(tdMessage);
        return "redirect:/tdmessages/" + encodeUrlPathSegment(conversionService.convert(tdMessage.getId(), String.class), httpServletRequest);
    }
	
	@RequestMapping(value = "/{id}/file", method = RequestMethod.GET)
    public String showImage(@PathVariable("id") String id, HttpServletResponse response, Model model) {		
        TdMessage message = tdMessageRepository.findOne(conversionService.convert(id, TdMessagePK.class));
        if (message != null) {
            byte[] image = message.getFile();
            if (image != null) {
                try {
                    response.setContentType(message.getFileContentType());
                    OutputStream out = response.getOutputStream();
                    IOUtils.copy(new ByteArrayInputStream(image), out);
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }	
}
