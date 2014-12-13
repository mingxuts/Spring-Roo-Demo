package com.paperuni.demo.web;
import com.paperuni.demo.model.TdMessage;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.gvnix.addon.web.mvc.jquery.GvNIXWebJQuery;

@RequestMapping("/tdmessages")
@Controller
@RooWebScaffold(path = "tdmessages", formBackingObject = TdMessage.class)
@GvNIXWebJQuery
public class TdMessageController {
}
