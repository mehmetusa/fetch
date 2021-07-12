package com.fetch.pages;

import static org.junit.Assert.assertEquals;

import java.io.FileReader;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.fetch.utilities.Driver;
import com.google.gson.Gson;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
public class CleanDirtsPage {
	
	public void hoover_finds_the_dirts_and_cleans_them() {
		
	}
	
	
	public void output_data_were_released_and_verified() {
		
	}
   
	public void loadInputData(){
      JSONParser parser = new JSONParser();
	   int patchesAmount = 0;

	
	   
      try {
         Object obj = parser.parse(new FileReader("src/test/java/com/fetch/utilities/input.json"));
         JSONObject jsonObject = (JSONObject)obj;
         System.out.println(jsonObject.toJSONString());

          Object name = jsonObject.get("roomSize");
          JSONArray roomSize = (JSONArray) jsonObject.get("roomSize");
          JSONArray patches = (JSONArray) jsonObject.get("patches");

          

//         String course = (String)jsonObject.get("coords");
//         JSONArray subjects = (JSONArray)jsonObject.get("patches");
          
         System.out.println("Name: " + name.toString());
         System.out.println("jsonArray: " + roomSize.get(0));
         System.out.println("patches: " + patches.get(1));

          String aa = roomSize.get(0).toString();
          String bb = roomSize.get(1).toString();
        //  String patche= patches.get(1).toString();


         System.out.println(Integer.parseInt(aa));
         
         int number_of_rows=Integer.parseInt(aa);
         int number_of_columns=Integer.parseInt(bb);
         int[][] arr = new int[number_of_rows][number_of_columns];
         System.out.println("Subjects:"+ arr.toString());
         for (int i = 0; i < number_of_rows+1 ; i++) {
	            for (int j = 0; j < number_of_columns+1; j++)
	                for (int k = 0; k < patches.size(); k++) {
		                System.out.println("[" + i + "][" + j + "]");
                     String hoover = "[" + i + "," + j + "]";
	            	 String patche= patches.get(k).toString();
          		 //  System.out.println("inprogress..."+patche );
          		  // System.out.println("hover..."+hoover );

	            	   if (hoover.contains(patche)) {
	            		   patchesAmount++;
	            		   patches.remove(k);
	            		   System.out.println("cleaned..."+patche );
	            		   if(patches.size()==0) {
		            		   System.out.println("no patches"+patchesAmount );
		            		   System.out.println("[" + i + "][" + j + "]");
		            		   
	            		    
	            		      JSONObject actualOutput = new JSONObject();

		            		   actualOutput.put("patches", patchesAmount);
		            		   actualOutput.put("coords", "[" + i + "][" + j + "]");
 
		            		   try (FileWriter file = new FileWriter("src/test/java/com/fetch/utilities/actualOutput.json")) {
		            		         file.write(actualOutput.toJSONString());
		            		         System.out.println("JSON Object write to a File successfully");
		            		         System.out.println("JSON Object: " + actualOutput);
		            		      }
	            		   }
	            	   }
	                }
	        }  
      } catch(Exception e) {
         e.printStackTrace();
      }
   }
}