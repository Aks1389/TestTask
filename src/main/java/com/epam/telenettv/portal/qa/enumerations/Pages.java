package com.epam.telenettv.portal.qa.enumerations;

import lombok.Getter;

@Getter
public enum Pages {

	HOME("Home"),
	TV("TV"),
	MOVIES_AND_SERIES("Movies & Series"),
	WEB_TV("Web TV"),
	SAVED("Saved"),
	SETTINGS("Settings");
	
	public final String value;
	
	Pages(final String label){
		this.value = label;
	}
}
