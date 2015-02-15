package com.paperuni.demo.web.custom;

import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;

import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paperuni.demo.CustomUser;
import com.paperuni.demo.model.TdUserinfoRepository;


@RequestMapping("/home/**")
@Controller
public class HomeController {
	
	@Autowired
	TdUserinfoRepository tdUserinfoRepository;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(Principal principal){
		String page = "/index";
		CustomUser loginUser = (CustomUser) ((Authentication) principal).getPrincipal();
		Collection loginUserRoles = loginUser.getAuthorities();
        String aRoleDesc;
        for (Iterator<GrantedAuthority> it = loginUserRoles.iterator(); it.hasNext(); ){
            aRoleDesc = it.next().getAuthority();
            if("ROLE_ADMIN".equals(aRoleDesc)){
                page = "/admindashboard" + page;
                break;
            }else if("ROLE_STUDENT".equals(aRoleDesc)){
                page = "/studentdashboard" + page;
                break;
            }else if ("ROLE_SUBADMIN".equalsIgnoreCase(aRoleDesc)){
            	page = "/subadmindashboard" + page;
            	break;
            }
        }
        return "redirect:" + page;
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public String profile(Principal principal, Model uiModel){
		addDateTimeFormatPatterns(uiModel);
		CustomUser loginUser = (CustomUser) ((Authentication) principal).getPrincipal();
		int id = loginUser.getUserID();
        uiModel.addAttribute("tduserinfo", tdUserinfoRepository.findOne(id));
        uiModel.addAttribute("itemId", id);	
        uiModel.addAttribute("list", "f");
        uiModel.addAttribute("create", "f");
        uiModel.addAttribute("update", "f");
        uiModel.addAttribute("delete", "f");
		return "common/profile";
	}
	
	private void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("tdUserinfo_createdate_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
    }

}
