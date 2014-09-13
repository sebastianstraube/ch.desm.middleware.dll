package ch.desm.middleware.gui;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "helloWorld", eager = true)

public class gui {
	public gui() {
		System.out.println("HelloWorld started!");
	}

	public String getMessage() {
		
		return "Hello World!";
	}
}
