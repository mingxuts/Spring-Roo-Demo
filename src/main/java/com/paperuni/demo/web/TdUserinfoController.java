package com.paperuni.demo.web;
import com.paperuni.demo.model.TdUserinfo;
import com.paperuni.demo.web.selectoption.Groupname;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.gvnix.addon.web.mvc.jquery.GvNIXWebJQuery;

@RequestMapping("/tduserinfoes")
@Controller
@RooWebScaffold(path = "tduserinfoes", formBackingObject = TdUserinfo.class)
@GvNIXWebJQuery
public class TdUserinfoController {
	
	@Autowired
	Groupname groupName;

	void populateEditForm(Model uiModel, TdUserinfo tdUserinfo) {
        uiModel.addAttribute("tdUserinfo", tdUserinfo);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("tdtasks", tdTaskRepository.findAll());
        uiModel.addAttribute("groupnames", groupName.getAllGroupnames());
    }
}
