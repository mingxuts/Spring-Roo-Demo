package com.paperuni.demo.web;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.paperuni.demo.CustomUser;
import com.paperuni.demo.model.TdOrder;
import com.paperuni.demo.web.selectoption.Courselevel;
import com.paperuni.demo.web.selectoption.Presentationformat;
import com.paperuni.demo.web.selectoption.Referencingformat;
import com.paperuni.demo.web.selectoption.Sourcescount;
import com.paperuni.demo.web.selectoption.Wordcount;

import org.springframework.security.core.Authentication;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.gvnix.addon.web.mvc.jquery.GvNIXWebJQuery;

@RequestMapping("/tdorders")
@Controller
@RooWebScaffold(path = "tdorders", formBackingObject = TdOrder.class)
@GvNIXWebJQuery
public class TdOrderController {

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new TdOrder());
        uiModel.addAttribute("wordcountvalues", Wordcount.getAllWordCountValues());
        uiModel.addAttribute("courselevels", Courselevel.getAllCourses());
        uiModel.addAttribute("sourcescount", Sourcescount.getAllSourcescountValues());
        uiModel.addAttribute("presentationformat", Presentationformat.getAllPresentationformat());
        uiModel.addAttribute("referencingformat", Referencingformat.getAllReferencingformat());
        return "tdorders/create";
    }

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid TdOrder tdOrder, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest,
    		@RequestParam("file") MultipartFile multipartFile,
    		Principal principal) throws IOException {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, tdOrder);
            return "tdorders/create";
        }
        uiModel.asMap().clear();
        Calendar today = new GregorianCalendar();
        CustomUser user = (CustomUser)((Authentication) principal).getPrincipal();
        tdOrder.setCustomerId(user.getUserID());
        tdOrder.setCreateDate(today);
        tdOrder.setFileContentType(multipartFile.getContentType());
        tdOrderRepository.save(tdOrder);
        return "redirect:/tdorders/" + encodeUrlPathSegment(tdOrder.getId().toString(), httpServletRequest);
    }
	
	@InitBinder
	protected void initBinder(HttpServletRequest request,
	        ServletRequestDataBinder binder) throws ServletException {
	    binder.registerCustomEditor(byte[].class,
	            new ByteArrayMultipartFileEditor());
	}	

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid TdOrder tdOrder, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest,
    		@RequestParam("file") MultipartFile multipartFile) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, tdOrder);
            return "tdorders/update";
        }
        uiModel.asMap().clear();
        tdOrder.setFileContentType(multipartFile.getContentType());
        tdOrderRepository.save(tdOrder);
        return "redirect:/tdorders/" + encodeUrlPathSegment(tdOrder.getId().toString(), httpServletRequest);
    }
}
