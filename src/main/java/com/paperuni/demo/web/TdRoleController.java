package com.paperuni.demo.web;
import com.paperuni.demo.model.TdRole;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.gvnix.addon.web.mvc.jquery.GvNIXWebJQuery;

@RequestMapping("/tdroles")
@Controller
@RooWebScaffold(path = "tdroles", formBackingObject = TdRole.class)
@GvNIXWebJQuery
public class TdRoleController {
}
