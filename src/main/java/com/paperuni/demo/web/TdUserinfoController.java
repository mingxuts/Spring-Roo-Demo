package com.paperuni.demo.web;
import com.paperuni.demo.model.TdUserinfo;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.gvnix.addon.web.mvc.jquery.GvNIXWebJQuery;

@RequestMapping("/tduserinfoes")
@Controller
@RooWebScaffold(path = "tduserinfoes", formBackingObject = TdUserinfo.class)
@GvNIXWebJQuery
public class TdUserinfoController {
}
