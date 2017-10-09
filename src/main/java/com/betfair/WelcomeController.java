package com.betfair;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.betfair.pojo.BetReport;
/**
 * @author KesavarajuluV
 *
 */
@Controller
public class WelcomeController {

	@Autowired
	CSVReader csvReader;

	@RequestMapping("/betreport")
	public String welcome(Map<String, Object> model) {
		return "welcome";
	}


	@PostMapping("/uploadCSV")
	public String singleFileUpload(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) {
		if(file.getOriginalFilename().equals("") || file == null){
			request.setAttribute("Error", "Uploaded empty file. Please upload the file correctly.");
			return "welcome"; 
		}
		try{
			// Get the file and generate the report
			List<BetReport> betList = csvReader.readCSV(file);
			request.setAttribute("betList", betList);
		}catch(Exception e){
			return "error";
		}

		return "welcome";
	}

	@PostMapping("/xmlreport")
	public String doGetXML(HttpServletRequest request, HttpServletResponse response,@RequestParam("file") MultipartFile file) {
		if(file.getOriginalFilename().equals("") || file == null){
			request.setAttribute("Error", "Uploaded empty file. Please upload the file correctly.");
			return "welcome"; 
		}
		
		try{
			response.setContentType("xml/html");
			String gurufile = "report.xml";
			PrintWriter out = response.getWriter();	
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ gurufile + "\"");
			List<BetReport> betList = csvReader.readCSV(file);
			request.setAttribute("betList", betList);
			out.println(csvReader.xmlReport(betList));
			out.close();
		}catch(IOException e){
			return "error";
		}
		return "welcome";
		
	}


}