package com.paperuni.demo.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.paperuni.demo.model.TdOrder;
import com.paperuni.demo.model.TdOrderRepository;
import com.paperuni.demo.model.TdTask;
import com.paperuni.demo.model.TdUserinfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.commons.io.IOUtils;
import org.gvnix.addon.web.mvc.jquery.GvNIXWebJQuery;
import org.joda.time.format.DateTimeFormat;

@RequestMapping("/tdtasks")
@Controller
@RooWebScaffold(path = "tdtasks", formBackingObject = TdTask.class)
@GvNIXWebJQuery
public class TdTaskController {
	

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel, @RequestParam(value= "form", required = false) Integer orderID) {
		TdTask task = new TdTask();
		if (orderID != null && orderID != 0){
			TdOrder selectedOrder = tdOrderRepository.findOne(orderID);
			TdUserinfo customer = tdUserinfoRepository.findOne(selectedOrder.getCustomerId());			
			
			task.setOrderId(selectedOrder.getId());
			task.setCustomerId(customer);
			task.setCourseLevel(selectedOrder.getCourseLevel());
			task.setStartDate(selectedOrder.getStartDate());
			task.setDeadLine(selectedOrder.getDeadLine());
			task.setWordCount(selectedOrder.getWordCount());
			task.setSubjectId(selectedOrder.getSubjectId().getId());
			task.setNote(selectedOrder.getNote());
			task.setCoupon(selectedOrder.getCoupon());
			task.setFile(selectedOrder.getFile());
			task.setFormat(selectedOrder.getFormat());
			task.setReferencing(selectedOrder.getReferencing());
			task.setSourcesCount(selectedOrder.getSourcesCount());
			task.setFileContentType(selectedOrder.getFileContentType());
			task.setIncludeFigure(selectedOrder.getIncludeFigure());
			task.setFileName(selectedOrder.getFileName());
			task.setFileSize(selectedOrder.getFileSize());
		}

        populateEditForm(uiModel, task);
        return "tdtasks/create";
    }

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid TdTask tdTask, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, tdTask);
            return "tdtasks/create";
        }
        uiModel.asMap().clear();
        tdTaskRepository.save(tdTask);
        TdOrder tdOrder = tdOrderRepository.findOne(tdTask.getOrderId());
        tdOrder.setTaskId(tdTask);
        tdOrderRepository.save(tdOrder);
        return "redirect:/tdtasks/" + encodeUrlPathSegment(tdTask.getId().toString(), httpServletRequest);
    }

	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("tdTask_startdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("tdTask_deadline_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("tdTask_createdate_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Integer id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        TdTask task = tdTaskRepository.findOne(id);
        task.setUrl("./showfile/" + id);
        uiModel.addAttribute("tdtask", task);
        uiModel.addAttribute("itemId", id);
        return "tdtasks/show";
    }
	
	@RequestMapping(method = RequestMethod.GET, value="showfile/{id}")
	public String showFile(@PathVariable("id") Integer id, HttpServletResponse response,
            Model uiModel){
		TdTask task = tdTaskRepository.findOne(id);
		
		try{
			response.setHeader("Content-Disposition", "inline;filename=\"" + task.getFileName() + "\"");
			
			OutputStream out = response.getOutputStream();
			response.setContentType(task.getFileContentType());
			IOUtils.copy(new ByteArrayInputStream(task.getFile()), out);
			out.flush();
		}catch (IOException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return null;
	}	
}
