package com.paperuni.demo.web.custom;

import java.security.Principal;

import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.paperuni.demo.CustomUser;
import com.paperuni.demo.model.OrderSpecifications;
import com.paperuni.demo.model.TdOrderRepository;

@RequestMapping("/studentdashboard/**")
@Controller
public class StudentDashboardController {
	
	@Autowired
	private TdOrderRepository tdOrderRepository;

	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(){
		return "studentdashboard/index";
	}
	
	@RequestMapping(value="/listorders", method=RequestMethod.GET)
    public String listOrders(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel,
    		Principal principal) {
		CustomUser user = (CustomUser)((Authentication) principal).getPrincipal();
		int customerId = user.getUserID();
		
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("tdorders", tdOrderRepository.findAll(OrderSpecifications.byCustomer(customerId) ,new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) tdOrderRepository.count(OrderSpecifications.byCustomer(customerId)) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("tdorders", tdOrderRepository.findAll(OrderSpecifications.byCustomer(customerId)));
        }
        addDateTimeFormatPatterns(uiModel);
        return "tdorders/list";
    }
	
	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("tdOrder_startdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("tdOrder_deadline_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("tdOrder_createdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
}
