package com.mimacom.sample.spring.portlet.controller;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.mimacom.sample.spring.portlet.controller.Keys.Views;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by alberto.martinez@mimacom.com on 2/06/14.
 */

@Controller
@RequestMapping("VIEW")
public class ViewController {
    private static final Log LOGGER = LogFactoryUtil.getLog(ViewController.class);

    @RenderMapping
    public String defaultView() {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Default View");
        }

        return Views.DEFAULT_VIEW;
    }

    @RenderMapping(params = "render=alternative-view")
    public String alternativeView() {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Alternative view");
        }

        return Views.ALTERNATIVE_VIEW;
    }

    @ActionMapping(params = "action=action-one")
    public void actionOne() {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Action one");
        }

        // Returns control to default view
    }

    @ActionMapping(params = "action=action-two")
    public void actionTwo(ActionResponse actionResponse) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Action two");
        }

        // Returns control to alternative view
        actionResponse.setRenderParameter("render", "alternative-view");
    }

    @ResourceMapping(value = "resource-one")
    public void resourceOne(ResourceResponse resourceResponse) throws PortletException {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Resource one");
        }

        try {
            resourceResponse.setContentType("text/html");
            PrintWriter writer = resourceResponse.getWriter();
            writer.println("<p>This request handle the complete response. This is usefull to return JSON, images, files or any other resource that are needed by our portlets</p>");
        } catch (IOException e) {
            throw new PortletException(e);
        }
    }
}
