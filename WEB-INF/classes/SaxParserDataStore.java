import org.xml.sax.InputSource;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import  java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SaxParserDataStore extends DefaultHandler {
    Phone phone;
    Laptop laptop;
    WearableTech wt;
    VoiceAsst va;
    Accessory accessory;
    static HashMap<String,Phone> phones;
    static HashMap<String,Laptop> laptops;
    static HashMap<String,WearableTech> wts;
	static HashMap<String,VoiceAsst> vas;
	static HashMap<String,Accessory> accessories;
    
    String consoleXmlFileName;
	HashMap<String,String> accessoryHashMap;
    String elementValueRead;
	String currentElement="";
    public SaxParserDataStore()
	{
	}
	public SaxParserDataStore(String consoleXmlFileName) {
    this.consoleXmlFileName = consoleXmlFileName;
    
    phones = new HashMap<String, Phone>();
    laptops=new HashMap<String, Laptop>();
    wts=new HashMap<String, WearableTech>();
    vas=new HashMap<String, VoiceAsst>();
	accessories=new HashMap<String, Accessory>();
	accessoryHashMap=new HashMap<String,String>();  
	parseDocument();
    }

   //parse the xml using sax parser to get the data
    private void parseDocument() 
	{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try 
		{
	    SAXParser parser = factory.newSAXParser();
	    parser.parse(consoleXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
	}

	@Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("phone")) 
		{
			currentElement="phone";
			phone = new Phone();
            phone.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("laptop")) 
		{
			currentElement="laptop";
			laptop = new Laptop();
            laptop.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("wt")) 
		{
			currentElement="wt";
			wt = new WearableTech();
            wt.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("va")) 
		{
			currentElement="va";
			va = new VoiceAsst();
            va.setId(attributes.getValue("id"));
		}

        
        if (elementName.equals("accessory") &&  !currentElement.equals("phone"))
		{
			currentElement="accessory";
			accessory=new Accessory();
			accessory.setId(attributes.getValue("id"));
	    }

	}

	@Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equals("phone")) {
			phones.put(phone.getId(),phone);
			return;
        }
        if (element.equals("laptop")) {
			laptops.put(laptop.getId(),laptop);
			return;
        }
        if (element.equals("wt")) {
			wts.put(wt.getId(),wt);
			return;
        }
         if (element.equals("va")) {
			vas.put(va.getId(),va);
			return;
        }
 
        
        if (element.equals("accessory") && currentElement.equals("accessory")) {
			accessories.put(accessory.getId(),accessory);       
			return; 
        }  
		if (element.equals("accessory") && currentElement.equals("phone")) 
		{
			accessoryHashMap.put(elementValueRead,elementValueRead);
		}
      	if (element.equalsIgnoreCase("accessories") && currentElement.equals("phone")) {
			phone.setAccessories(accessoryHashMap);
			accessoryHashMap=new HashMap<String,String>();
			return;
		}


	    if (element.equalsIgnoreCase("image")) {
		    if(currentElement.equals("phone"))
				phone.setImage(elementValueRead);
			if(currentElement.equals("laptop"))
				laptop.setImage(elementValueRead);
			if(currentElement.equals("wt"))
				wt.setImage(elementValueRead);
			if(currentElement.equals("va"))
				va.setImage(elementValueRead);
            
            if(currentElement.equals("accessory"))
				accessory.setImage(elementValueRead);    
			return;
        }
        

		if (element.equalsIgnoreCase("discount")) {
            if(currentElement.equals("phone"))
				phone.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("laptop"))
				laptop.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("wt"))
				wt.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("va"))
				va.setDiscount(Double.parseDouble(elementValueRead));

            if(currentElement.equals("accessory"))
				accessory.setDiscount(Double.parseDouble(elementValueRead));       
			return;
	    }


		if (element.equalsIgnoreCase("condition")) {
            if(currentElement.equals("phone"))
				phone.setCondition(elementValueRead);
			if(currentElement.equals("laptop"))
				laptop.setCondition(elementValueRead);
			if(currentElement.equals("wt"))
				wt.setCondition(elementValueRead);
			if(currentElement.equals("va"))
				va.setCondition(elementValueRead);
        
            if(currentElement.equals("accessory"))
				accessory.setCondition(elementValueRead);          
			return;  
		}

		if (element.equalsIgnoreCase("manufacturer")) {
            if(currentElement.equals("phone"))
				phone.setRetailer(elementValueRead);
			if(currentElement.equals("laptop"))
				laptop.setRetailer(elementValueRead);
			if(currentElement.equals("wt"))
				wt.setRetailer(elementValueRead);
			if(currentElement.equals("va"))
				va.setRetailer(elementValueRead);
        	
            if(currentElement.equals("accessory"))
				accessory.setRetailer(elementValueRead);          
			return;
		}

        if (element.equalsIgnoreCase("name")) {
            if(currentElement.equals("phone"))
				phone.setName(elementValueRead);
			if(currentElement.equals("laptop"))
				laptop.setName(elementValueRead);
			if(currentElement.equals("wt"))
				wt.setName(elementValueRead);
			if(currentElement.equals("va"))
				va.setName(elementValueRead);
        				
            if(currentElement.equals("accessory"))
				accessory.setName(elementValueRead);          
			return;
	    }
	
        if(element.equalsIgnoreCase("price")){
			if(currentElement.equals("phone"))
				phone.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("laptop"))
				laptop.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("wt"))
				wt.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("va"))
				va.setPrice(Double.parseDouble(elementValueRead));
        	
            if(currentElement.equals("accessory"))
				accessory.setPrice(Double.parseDouble(elementValueRead));          
			return;
        }
    }



	@Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }



 public static void addHashmap() {
		String TOMCAT_HOME = System.getProperty("catalina.home");	
		new SaxParserDataStore(TOMCAT_HOME+"//webapps//Assignment1//ProductCatalog.xml");
    } 
}