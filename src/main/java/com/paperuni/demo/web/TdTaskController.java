package com.paperuni.demo.web;

import com.paperuni.demo.model.TdOrder;
import com.paperuni.demo.model.TdOrderRepository;
import com.paperuni.demo.model.TdTask;
import com.paperuni.demo.model.TdUserinfo;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.gvnix.addon.web.mvc.jquery.GvNIXWebJQuery;

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
}
