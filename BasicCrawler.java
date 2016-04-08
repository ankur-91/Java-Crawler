

package basiccrawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import edu.uci.ics.crawler4j.frontier.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.Iterator;

import org.apache.http.HttpStatus;
public class BasicCrawler extends WebCrawler {
	
	int count= 0;
	
String words[];
static TreeSet<String> dict = new TreeSet<String>(); 
static Map<Integer,String> url_list = new HashMap<Integer,String>();
static int docid;
        private final static Pattern FILTERS = 
        		      Pattern.compile(".*(\\.(css|js|bmp|gif" + "|png|tiff?|mid|mp2|mp3|mp4"
                        + "|wav|avi|mov|mpeg|ram|m4v|pdf|pptx" + "|rm|smil|wmv|swf|wma|zip|rar|gz|xml|wmf))$");
     protected Frontier frontier;

        /**
         * You should implement this function to specify whether
         * the given url should be crawled or not (based on your
         * crawling logic).
         */
        @Override
        public boolean shouldVisit(WebURL url) {
        	    boolean myresult;
                String href = url.getURL().toLowerCase();
                // Filtering what is to be crawled
                myresult = !FILTERS.matcher(href).matches() && href.startsWith("http://lyle.smu.edu/~fmoore/");
               //To find how many jpg's are in test data
               
                if(href.contains("jpg")){
                	count++;
                }
                //System.out.println(url+"   "+myresult);
                return myresult;
        }
        
        /**
         * This function is called when a page is fetched and ready 
         * to be processed by your program.
         */
        @Override
        public void visit(Page page) 
        {
                 docid = page.getWebURL().getDocid();
                String url = page.getWebURL().getURL();
                int parentDocid = page.getWebURL().getParentDocid();
                url_list.put(docid,url);
                
                //Printing Docid, URL, parent page Docid
            // System.out.println("Docid: " + docid);
//               	
             //	System.out.println("URL: " + url);
//              System.out.println("Docid of parent page: " + parentDocid);
                if (page.getParseData() instanceof HtmlParseData) {
                	    //Parse data with HTML tags and palne text
                        HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
                        // Pull out only text without HTML tags
                        String text = htmlParseData.getText();
                        // Pull out HTML tags
                        String html = htmlParseData.getHtml();
                        //List with out going link
                        List<WebURL> links = htmlParseData.getOutgoingUrls();
                        String lower_text = text.toLowerCase();
                        // Logic to print the out going links list
                        ListIterator lit = links.listIterator();
                       
                       //Splitting text according to regular expression (Question 5)
                        
                       
                 	   
//                 	   if(docid == 1){
//                 		   System.out.println("preparing list");
//                        File f = new File("C:/Users/Madhuri/Desktop/Info_Retrival/Info_Project/webcrawler/Doc_List22.log");
//                        try (BufferedReader reader = new BufferedReader(new FileReader(f));)
//                        {
//                            String line = null;
//                            while ((line = reader.readLine()) != null) 
//                            {
//                         	    
//                                entries.add(line);
//                            }
//                            System.out.println(entries.size());
//                            dict_matrix = new int[entries.size()][23];
//                        }
//                        catch (IOException e)
//                        {
//                            e.printStackTrace();
//                        }
//                 	   }
                 	  
                        words = lower_text.split("[\\s\\t\\r.,():?\\//@~-]+");
                       //  Pulling text only if length is > 0
                       if(text.length() > 0){
                    	if(url.contains("txt") || url.contains("htm") || url.contains("html")){
                    	//  Logic to pull text into new .log text document and save it into project folder (Workspaces) 
//                    		   try {
//                       			System.setOut(new PrintStream(new FileOutputStream("Doc"+docid+".log")));
//                       		} catch (FileNotFoundException e) {
//                       			// TODO Auto-generated catch block
//                       			e.printStackTrace();
//                       		}

                    		
                       
                       for(int i=0;i < words.length;i++){
                    	  
                    		   if(words[i].substring(0, 1).matches("[a-zA-Z]+")){
                    			   	dict.add(words[i]);
                    			// System.out.println(words[i]);
                    		   }
                       }
                       
                      
                       
                       }
//                    	System.out.println(docid);
//                    	System.out.println(url);
//                       System.out.println(entries.size());
//                       System.out.println(words.length);
                     
//                    	for(int j = 0;j < entries.size();j++){
//                    		 for(int i =0; i<words.length;i++){
//                    			 		
//                    		   	if(entries.get(j).equals(words[i])){
//                    		   				
//                    		   				
//                    		   				dict_matrix[j][docid] = 1;
//                    		   				
//                    		   			}
//                    		   				
//                    		   			}
//                    		  // 	System.out.println(dict_matrix[j][docid]);
//                    		   		}
                 	}
                	   }
                    	  
                    	}
                       
                
                       
                
                    		
                    		    
                 	   
                
                
        
                
                      
                       
                       
                
        
//                       
               
               //Printing JPG's count
               //System.out.println("JPG Count:"+count);
             //  System.out.println("=============");
                

      
        /*
         * Method to identify broken_links based on statusCode
         */
        
       
        protected void handlePageStatusCode(WebURL url, int statusCode, String statusDescription)
        {
           // Logic to compare statusCode to find boken link
//            if (statusCode != HttpStatus.SC_OK)
//            {
//            		if (statusCode == HttpStatus.SC_NOT_FOUND) 
//            		{
//                    		System.out.println("======*======");
//                            System.out.println("Broken link: "+ url.getURL() + "\nthis link was found in page: " + url.getParentUrl());
//                            System.out.println("======*======");
//                    } 
//            }
       }
}
        
        
        
