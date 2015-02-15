package com.paperuni.demo.web.custom;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.paperuni.demo.CustomUser;
import com.paperuni.demo.model.MessageSpecifications;
import com.paperuni.demo.model.OrderSpecifications;
import com.paperuni.demo.model.TdMessage;
import com.paperuni.demo.model.TdMessagePK;
import com.paperuni.demo.model.TdMessageRepository;
import com.paperuni.demo.model.TdOrder;
import com.paperuni.demo.model.TdOrderRepository;
import com.paperuni.demo.model.TdTaskRepository;
import com.paperuni.demo.model.TdUserinfo;
import com.paperuni.demo.model.TdUserinfoRepository;
import com.paperuni.demo.web.selectoption.AdminMessageconverter;
import com.paperuni.demo.web.selectoption.Groupname;

@RequestMapping("/admindashboard/**")
@Controller
public class AdminDashboardController {
	
	@Autowired
	private TdOrderRepository tdOrderRepository;
	
	@Autowired
	private TdMessageRepository tdMessageRepository;
	
	@Autowired
	private ConversionService conversionService;
	
	@Autowired
	TdUserinfoRepository tdUserinfoRepository;
	
	@Autowired
	TdTaskRepository tdTaskRepository;

	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(){
		return "admindashboard/index";
	}
	
	@RequestMapping(value="/addsubadmin", method=RequestMethod.GET)
	public String addSubadmin(Principal principal, Model uiModel){
		renderSubadminForm(principal, uiModel);
		return "admindashboard/addsubadmin";
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
		uiModel.addAttribute("messages", convertMessageList(tdMessageRepository.findAll(messageIsnotReview(), sortByCreateDatedesc())));
		return "admindashboard/messages";
	}
	
	@RequestMapping(value = "/messages/{id}/edit", method = RequestMethod.GET)
	public String editMessageForm(Model uiModel, @PathVariable("id") String id){
		TdMessage message = tdMessageRepository.findOne(conversionService.convert(id, TdMessagePK.class));
		uiModel.addAttribute("tdMessage", message);
		return "admindashboard/editmessage";
	}
	
	@RequestMapping(value="/messages/{id}/edit", method=RequestMethod.POST)
	public String editMessage(@Valid TdMessage tdMessage, BindingResult bindingResult, Model uiModel, @PathVariable("id") String id){
		if (bindingResult.hasErrors()){
			uiModel.addAttribute("tdMessage", tdMessage);
			return "admindashboard/editmessage";
		}
		uiModel.asMap().clear();
		TdMessage oldmessage = tdMessageRepository.findOne(conversionService.convert(id, TdMessagePK.class));
		oldmessage.setBody(tdMessage.getBody());
		oldmessage.setLink(tdMessage.getLink());
		oldmessage.setHasReview(true);
		
		tdMessageRepository.save(oldmessage);
		return "redirect:/admindashboard/messages";
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public String profile(Principal principal, Model uiModel){
		CustomUser loginUser = (CustomUser) ((Authentication) principal).getPrincipal();
		int id = loginUser.getUserID();
        uiModel.addAttribute("tduserinfo", tdUserinfoRepository.findOne(id));
        uiModel.addAttribute("itemId", id);	
        uiModel.addAttribute("list", "f");
        uiModel.addAttribute("create", "f");
        uiModel.addAttribute("update", "f");
        uiModel.addAttribute("delete", "f");
		return "admindashboard/profile";		
	}
	
	@RequestMapping(value="/showtask", method=RequestMethod.GET)
	public String showtask(Principal principal, Model uiModel){
		uiModel.addAttribute("tdtasks", tdTaskRepository.findAll());
		return "admindashboard/showtask";
	}
	
	
	private Specification<TdMessage> messageIsnotRead(){
		return MessageSpecifications.Hasnotread();
	}
	
	private Specification<TdMessage> messageIsnotReview(){
		return MessageSpecifications.HasnotReview();
	}
	
	private Specification<TdOrder> isPendingAndUnassign(){
		return OrderSpecifications.isPendingAndUnassign();
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
	
	public static void renderSubadminForm(Principal principal, Model uiModel){
		TdUserinfo subadmin = new TdUserinfo();
		subadmin.setGroupName("ROLE_SUBADMIN");
		subadmin.setHasVerified(true);
		subadmin.setPasswordNonExpired(true);
		CustomUser loginUser = (CustomUser) ((Authentication) principal).getPrincipal();
		int id = loginUser.getUserID();		
		subadmin.setCreateBy(id);
		uiModel.addAttribute("tdUserinfo", subadmin);
		uiModel.addAttribute("groupnames", Groupname.getAllGroups());		
	}
}
