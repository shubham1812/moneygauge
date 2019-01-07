package moneyguage.View.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import moneyguage.View.bean.CustomizableTemplateBean;


@Named
@RequestScoped
public class CustomizableTemplateController {
	@Inject
	CustomizableTemplateBean customizableTemplateBean;

	public String startNewMode() {
		if (customizableTemplateBean.isNightMode()) {
			customizableTemplateBean.setNightMode(false);
		} else {
			customizableTemplateBean.setNightMode(true);
		}
		return "pretty:home";
	}

}
