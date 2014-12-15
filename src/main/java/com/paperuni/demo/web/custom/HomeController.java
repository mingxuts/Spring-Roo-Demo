package com.paperuni.demo.web.custom;

import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paperuni.demo.CustomUser;


@RequestMapping("/home/**")
@Controller
public class HomeController {
	
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
            }
        }
        return "redirect:" + page;
	}

}
