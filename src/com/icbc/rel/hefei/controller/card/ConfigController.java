package com.icbc.rel.hefei.controller.card;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/mp")
public class ConfigController {
    /**
     * ≈‰÷√ ◊“≥ ”Õº
     * @param request
     * @return
     */
    @RequestMapping(value="/ecardsCfg")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("card/cardconfig");
        return mav;
    }
}
