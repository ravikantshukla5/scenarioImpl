package com.ramselabs.scenario.converter;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.ramselabs.scenario.model.AvailableThemes;
import com.ramselabs.scenario.model.Theme;

@ManagedBean
@SessionScoped
public class ThemeConverter implements Converter,Serializable {

	private static final long serialVersionUID = 462342467124560432L;

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return AvailableThemes.instance().getTheme(value);
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value instanceof Theme)
		  return ((Theme) value).getName();
		return null;
	}
}
