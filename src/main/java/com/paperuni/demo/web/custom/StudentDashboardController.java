package com.paperuni.demo.web.custom;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
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

import com.paperuni.demo.CustomUser;
import com.paperuni.demo.model.OrderSpecifications;
import com.paperuni.demo.model.TaskSpecifications;
import com.paperuni.demo.model.TdMessage;
import com.paperuni.demo.model.TdMessagePK;
import com.paperuni.demo.model.TdMessageRepository;
import com.paperuni.demo.model.TdOrderRepository;
import com.paperuni.demo.model.TdTaskRepository;
import com.paperuni.demo.model.TdUserinfo;
import com.paperuni.demo.model.TdUserinfoRepository;
import com.paperuni.demo.web.selectoption.Messageconverter;

@RequestMapping("/studentdashboard/**")
@Controller
public class StudentDashboardController {
	
	@Autowired
	private TdOrderRepository tdOrderRepository;
	
	@Autowired
	private TdMessageRepository tdMessageRepository;
	
	@Autowired
	private TdUserinfoRepository tdUSerinfoRepository;
	
	@Autowired
	private TdTaskRepository tdTaskRepository;
	
	@Autowired
	private ConversionService conversionService;
	
	@Autowired
	TdUserinfoRepository tdUserinfoRepository;

	@InitBinder
	protected void initBinder(HttpServletRequest request,
	        ServletRequestDataBinder binder) throws ServletException {
	    binder.registerCustomEditor(byte[].class,
	            new ByteArrayMultipartFileEditor());
	}	
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(){
		return "studentdashboard/index";
	}
	
	@RequestMapping(value="/listorder", method=RequestMethod.GET)
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
        return "studentdashboard/listorders";
    }
	
	@RequestMapping(value="/listmessage", method=RequestMethod.GET, produces="text/html")
	public String listMessages(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel, Principal principal){
		CustomUser user = (CustomUser)((Authentication) principal).getPrincipal();
		TdUserinfo userinfo = user.loadUserinfo(tdUSerinfoRepository);
		
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            PageRequest pageable = new PageRequest(firstResult / sizeNo, sizeNo, Sort.Direction.DESC, "createDate");
            uiModel.addAttribute("tdmessages", convertMessage(tdMessageRepository.findByTaskId_CustomerId(userinfo, pageable)));
            float nrOfPages = (float) tdMessageRepository.count(byCustomer(userinfo)) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("tdorders", convertMessage(tdMessageRepository.findByTaskId_CustomerId(userinfo)));
        }		
		
        uiModel.addAttribute("nrcount", tdMessageRepository.count(byCustomer(userinfo)));
		return "studentdashboard/showmessage";
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
        return "studentdashboard/profile";
	}
	
	@RequestMapping(value="/paymentmethods", method=RequestMethod.GET)
	public String paymentmethods(Model uiModel){
		return "studentdashboard/paymentmethods";
	}
	
	
	@RequestMapping(value="/sendmsg", method=RequestMethod.GET)
	public String sendMsgForm(Model uiModel, Principal principal){
		CustomUser user = (CustomUser)((Authentication) principal).getPrincipal();
		populateEditForm(uiModel, new TdMessage(), user.loadUserinfo(tdUSerinfoRepository));
		return "studentdashboard/sendmsg";
	}
	
	@RequestMapping(value="/sendmsg", method=RequestMethod.POST)
	public String sendMsg(@Valid TdMessage tdMessage, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest,
			@RequestParam("file") MultipartFile multipartFile, Principal principal) {
		CustomUser user = (CustomUser)((Authentication) principal).getPrincipal();
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, tdMessage, user.loadUserinfo(tdUSerinfoRepository));
            return "studentdashboard/sendmsg";
        }
        uiModel.asMap().clear();
        Calendar today = new GregorianCalendar();

        tdMessage.setSource("W");
        tdMessage.setCreateDate(today);
        tdMessage.setFileContentType(multipartFile.getContentType());
        tdMessageRepository.save(tdMessage);
        
        populateEditForm(uiModel, new TdMessage(), user.loadUserinfo(tdUSerinfoRepository));
        uiModel.addAttribute("MESSAGE_INFO", "message_student_message_sent_info");
        return "studentdashboard/sendmsg";
    }
	
	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("tdOrder_startdate_date_format", DateTimeFormat.patternForStyle("S-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("tdOrder_deadline_date_format", DateTimeFormat.patternForStyle("S-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("tdOrder_createdate_date_format", DateTimeFormat.patternForStyle("S-", LocaleContextHolder.getLocale()));
    }
	

	Specification<TdMessage> byCustomer(TdUserinfo userinfo){
		return com.paperuni.demo.model.MessageSpecifications.byCustomer(userinfo);
	}
	
	Specification<TdMessage> bySource(String source){
		return com.paperuni.demo.model.MessageSpecifications.bySource(source);
	}
	
	
	void populateEditForm(Model uiModel, TdMessage tdMessage, TdUserinfo tdUserinfo) {
        uiModel.addAttribute("tdMessage", tdMessage);
        uiModel.addAttribute("tdMessage_createdate_date_format", "MMM d, yyyy");
        uiModel.addAttribute("tdtasks", tdTaskRepository.findAll(TaskSpecifications.byCustomer(tdUserinfo)));
    }
	
	Specification<TdMessage> byTaskId(int taskId){
		return com.paperuni.demo.model.MessageSpecifications.byTaskId(taskId);
	}
	
	List<Messageconverter> convertMessage(List<TdMessage> list){
		List<Messageconverter> viewlist = new ArrayList<Messageconverter>();
		for (TdMessage msg : list){
			String key = conversionService.convert(msg.getId(), String.class);
			viewlist.add(new Messageconverter(key, msg));
		}
		return viewlist;
	}
	
}
