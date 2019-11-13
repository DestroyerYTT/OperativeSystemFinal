package co.edu.icesi.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStream.*;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Service
public class TemplateExample {

	public final static String routeFile = "./src/main/resources/templates/table.html";

	private static File table;
	
	private Map<Integer, String> map;

	public TemplateExample() {
		table = new File(routeFile);
		map = new HashMap<Integer, String>();
	}

	public void showProcess() {

		try {
			String cmd = "powershell.exe Get-Process | select Name, Id, @{n='CPU'; e={[math]::Round($_.CPU,2)}} | sort -Property CPU -Descending | ConvertTo-Csv";
			Process process = Runtime.getRuntime().exec(cmd);

			InputStream in = process.getInputStream();
			InputStreamReader inputReader = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(inputReader);

			// FileWriter fw = new FileWriter(table);

			PrintWriter pw = new PrintWriter(table);
			br.readLine();
			String linea;
			String[] arg = new String[] {};

			pw.println("<!DOCTYPE html>");
			pw.println("<html xmlns:\"http://www.w3.org/1999/xhtml\" xmlns:th=\"http:thymeleaf.org\">");
			pw.println("<head>");
			pw.println("<link rel='stylesheet' th:href=\"@{/styles.css}\">");
			pw.println("<meta charset=utf-8>");
			pw.println("<title>POWERSHELL</title>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<h2>Seleccione los procesos a finalizar:</h2>");
			pw.println("<form action=/hola/>");
			pw.println("<table style='text-align:center; border:1px solid black;'>"); 

			
			linea = br.readLine();
			char oldChar = 34;
			linea.replace(oldChar,' ');
			arg = linea.split(",");
			
			pw.println("<tr style= 'border:1px solid black;'>");
			pw.println("<th style= 'border:1px solid black;'>" + arg[0] + "</th>");
			pw.println("<th style= 'border:1px solid black;'>" + arg[1] + "</th>");
			pw.println("<th style= 'border:1px solid black;'>" + arg[2] + "</th>");
			pw.println("<th style= 'border:1px solid black;'>Process Selected</th>");
			pw.println("</tr>");
			
			br.readLine();
			int counter = 1;
			while ((linea = br.readLine()) != null) {
					linea.replace("\"", "");
					arg = linea.split(",");
					pw.println("<tr>");
					pw.println("<td>" + arg[0] + "</td>");
					pw.println("<td>" + arg[1] + "</td>");
					pw.println("<td>" + arg[2] + "</td>");
					pw.println("<td><input type='checkbox' id=" + counter +"></td>");
					pw.println("</tr>");
					map.put(counter, arg[1]);
					counter++;
			}
			pw.println("</table>");
			pw.println("<input type='submit'>");			
			pw.println("</form>");
			pw.println("</body>");
			pw.println("</html>");

			br.close();
			pw.close();

		} catch (Exception e) {

		}
	}

}
