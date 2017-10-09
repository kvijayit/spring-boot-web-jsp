package com.betfair;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.betfair.dao.BetsDao;
import com.betfair.pojo.BetReport;
import com.betfair.pojo.Bets;
/**
 * @author KesavarajuluV
 *
 */
@Controller
public class CSVReader {
	
	@Autowired
	BetsDao betsDao;
	
	@Value("${current.eur.to.gbp.rate}")
	String euroRate;
	
	private EmbeddedDatabase db;
	
	public void setup(){
		db = new EmbeddedDatabaseBuilder()
	    		.setType(EmbeddedDatabaseType.HSQL)
	    		.addScript("db/sql/create-db.sql")
	    		.build();
    }
	
    public List<BetReport> readCSV(MultipartFile csvMultipartfile) {

        List<BetReport> betList = new ArrayList<BetReport>();
        
        Bets bets;
        
        
        try {
        	int count = 0;
        	setup();
        	File file = multipartToFile(csvMultipartfile);
        	Reader in = new FileReader(file); //The file is the file which you get from the request(java.io.File)
            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
            for (CSVRecord record : records) {
                //Do something with the records
            	if(count>0){
            	bets = new Bets();
                bets.setBetId(record.get(0));
                bets.setTimestamp(Long.parseLong(record.get(1)));
                bets.setSelectionId(Long.parseLong(record.get(2)));
                bets.setSelectionName(record.get(3));
                bets.setStake(Double.parseDouble(record.get(4)));
                bets.setPrice(Double.parseDouble(record.get(5)));
                bets.setCurrency(record.get(6));
                bets.setPayout(bets.getStake() * bets.getPrice());
                
                if(bets.getCurrency().equals("EUR"))
                	bets.setEuroStake(bets.getStake());
                else
                	bets.setEuroStake(bets.getStake()*Double.parseDouble(euroRate));
                
                if(bets.getCurrency().equals("EUR"))
                	bets.setEuroPrice(bets.getPayout());
                else
                	bets.setEuroPrice(bets.getPayout()*Double.parseDouble(euroRate));
                
                bets.setPayout(bets.getStake() * bets.getPrice());
                betsDao.insertBets(bets);
            	}
            	count++;
            }
            
            betList = betsDao.generateReport();
            tearDown();
         } catch (IOException e) {
            e.printStackTrace();
        }
        
       return betList;
    }
    
    public String xmlReport(List<BetReport> betList){
    	return jaxbObjectToXML(betList);
    }
    
    public void tearDown() {
        db.shutdown();
    }
    
    private File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException 
    {
    	File convFile = new File(multipart.getOriginalFilename());
	    convFile.createNewFile(); 
	    FileOutputStream fos = new FileOutputStream(convFile); 
	    fos.write(multipart.getBytes());
	    fos.close(); 
	    return convFile;
    }
    
    
    private static String jaxbObjectToXML(List<BetReport> betList) {
        String xmlString = "";
        try {
        	JAXBContext context = JAXBContext.newInstance(BetReport.class);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

        	for(BetReport bet:betList){
	            StringWriter sw = new StringWriter();
	            m.marshal(bet, sw);
	            xmlString =xmlString+ sw.toString();
        	}

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return xmlString;
    }
    
}
