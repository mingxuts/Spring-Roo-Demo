package com.paperuni.demo.web;
import com.paperuni.demo.model.TdSubject;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.gvnix.addon.web.mvc.jquery.GvNIXWebJQuery;

@RequestMapping("/tdsubjects")
@Controller
@RooWebScaffold(path = "tdsubjects", formBackingObject = TdSubject.class)
@GvNIXWebJQuery
public class TdSubjectController {
}
