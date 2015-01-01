package com.paperuni.demo.web.custom;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.paperuni.demo.CustomUser;
import com.paperuni.demo.model.MessageSpecifications;
import com.paperuni.demo.model.OrderSpecifications;
import com.paperuni.demo.model.TdMessage;
import com.paperuni.demo.model.TdMessageRepository;
import com.paperuni.demo.model.TdOrder;
import com.paperuni.demo.model.TdOrderRepository;
import com.paperuni.demo.model.TdUserinfo;
import com.paperuni.demo.web.selectoption.AdminMessageconverter;

@RequestMapping("/admindashboard/**")
@Controller
public class AdminDashboardController {
	
	@Autowired
	private TdOrderRepository tdOrderRepository;
	
	@Autowired
	private TdMessageRepository tdMessageRepository;
	
	@Autowired
	private ConversionService conversionService;	

	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(){
		return "admindashboard/index";
	}
	
	@RequestMapping(value="/enquiry", method=RequestMethod.GET)
	public String enquiry(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel
    		) {
		
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("tdorders", tdOrderRepository.findAll(isPendingAndUnassign(),new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) tdOrderRepository.count(isPendingAndUnassign()) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("tdorders", tdOrderRepository.findAll(isPendingAndUnassign()));
        }
        //addDateTimeFormatPatterns(uiModel);
        return "admindashboard/enquiry";
    }
	
	@RequestMapping(value = "/messages", method=RequestMethod.GET)
	public String messages(Model uiModel){
		uiModel.addAttribute("messages", convertMessageList(tdMessageRepository.findAll(messageIsnotRead(), sortByCreateDatedesc())));
		return "admindashboard/messages";
	}
	
	Specification<TdOrder> isPendingAndUnassign(){
		return OrderSpecifications.isPendingAndUnassign();
	}
	
	Specification<TdMessage> messageIsnotRead(){
		return MessageSpecifications.Hasnotread();
	}
	
	private Sort sortByCreateDatedesc(){
		return new Sort(Sort.Direction.DESC, "createDate");
	}
	
	private List<AdminMessageconverter> convertMessageList(List<TdMessage> list){
		List<AdminMessageconverter> viewlist = new ArrayList<AdminMessageconverter>();
		if (list != null){
			for (TdMessage message : list){
				String key = conversionService.convert(message.getId(), String.class);
				TdUserinfo student = message.getTaskId().getCustomerId();
				TdUserinfo writer = message.getTaskId().getWriterId();
				viewlist.add(new AdminMessageconverter(key, message, student, writer));
			}
		}
		return viewlist;
	}
}
