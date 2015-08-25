package com.ramselabs.scenario.util;

import java.text.MessageFormat;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


/**
 * @author Ravikant
 *
 */
public class MessageUtil {

	public static void addInfoMessageWithoutSummary(String str) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, str,""));
	}

	public static void addInfoMessageWithSummary(String summary, String detail) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
	}

    public static void addInfoMessage(String str, Object... args) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (args != null) {
            str = MessageFormat.format(str, args);
        }
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, str, ""));
    }
}
