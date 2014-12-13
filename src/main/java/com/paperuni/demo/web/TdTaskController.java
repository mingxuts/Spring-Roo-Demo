package com.paperuni.demo.web;
import com.paperuni.demo.model.TdTask;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.gvnix.addon.web.mvc.jquery.GvNIXWebJQuery;

@RequestMapping("/tdtasks")
@Controller
@RooWebScaffold(path = "tdtasks", formBackingObject = TdTask.class)
@GvNIXWebJQuery
public class TdTaskController {
}
