package com.paperuni.demo.web;
import java.security.Principal;

import com.paperuni.demo.model.TdTaskRepository;
import com.paperuni.demo.model.TdUserinfo;
import com.paperuni.demo.web.custom.AdminDashboardController;
import com.paperuni.demo.web.selectoption.Groupname;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.gvnix.addon.web.mvc.jquery.GvNIXWebJQuery;

@RequestMapping("/tduserinfoes")
@Controller
@RooWebScaffold(path = "tduserinfoes", formBackingObject = TdUserinfo.class)
@GvNIXWebJQuery
public class TdUserinfoController {
	
	@Autowired
	TdTaskRepository tdTaskRepository;

	void populateEditForm(Model uiModel, TdUserinfo tdUserinfo) {
        uiModel.addAttribute("tdUserinfo", tdUserinfo);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("tdtasks", tdTaskRepository.findAll());
        uiModel.addAttribute("groupnames", Groupname.getAllGroups());
    }

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid TdUserinfo tdUserinfo, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest,
    		Principal principal) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, tdUserinfo);
            if (tdUserinfo.getGroupName().equalsIgnoreCase("ROLE_SUBADMIN")){
            	return "admindashboard/addsubadmin";
            } else {
            	return "tduserinfoes/create";
            }
        }
        uiModel.asMap().clear();
        tdUserinfoRepository.save(tdUserinfo);
        if (tdUserinfo.getGroupName().equalsIgnoreCase("ROLE_SUBADMIN")){
        	AdminDashboardController.renderSubadminForm(principal, uiModel);
        	uiModel.addAttribute("MESSAGE_INFO", "message_admin_addsubadmin_info");
        	return "admindashboard/addsubadmin";
        }else {
        	return "redirect:/tduserinfoes/" + encodeUrlPathSegment(tdUserinfo.getId().toString(), httpServletRequest);
        }        
    }
}
