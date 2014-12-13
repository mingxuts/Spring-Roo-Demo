package com.paperuni.demo.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.paperuni.demo.model.TdUserinfo;
import com.paperuni.demo.model.TdUserinfoRepository;
import com.paperuni.demo.model.UserinfoSpecifications;

@RequestMapping("/signup/**")
@Controller
public class SignupController {

	static Logger log = Logger.getLogger(SignupController.class.getName());
	
	@Autowired
	TdUserinfoRepository tdUserinfoRepository;
		
    @RequestMapping(method = RequestMethod.POST, value = "index")
	public String post(ModelMap modelMap, @RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("contactType") String contactType,
			@RequestParam("contactnumber") String contactNumber,
			@RequestParam("country") String country,
			@RequestParam("city") String city) {

		
		//We can't let email duplicated in our db
		if (tdUserinfoRepository
				.count(UserinfoSpecifications.emailEqual(email)) != 0) {
			log.debug("Email has existed");
			return "usersignup/index";
		} else {

			TdUserinfo tdUserinfo = new TdUserinfo();

			boolean passwordNonExpired = true;
			tdUserinfo.setEmail(email);
			tdUserinfo.setLoginPassword(password);
			tdUserinfo.setGroupName("ROLE_STUDENT");
			tdUserinfo.setContactType(contactType);
			tdUserinfo.setContactId(contactNumber);
			tdUserinfo.setCountry(country);
			tdUserinfo.setCity(city);
			tdUserinfo.setHasVerified(false);
			tdUserinfo.setPasswordNonExpired(passwordNonExpired);
			tdUserinfoRepository.save(tdUserinfo);
			log.debug("Account created");
			modelMap.addAttribute("signup_success", "true");
			return "signup/index";
		}
	}

    @RequestMapping
    public String index() {
        return "signup/index";
    }
}
