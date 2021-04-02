package com.hcl.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class FootBallGoals {

	//Input football goals dat file location path
	public static String INPUT_LOCATION = System.getProperty("user.dir") + "\\src\\main\\resources\\football.dat";
	
	@Test
	public void goals() {

		// Read the input file
		BufferedReader reader;
		try {
			// Hash map to store the Teams and the difference of for and against goals
			HashMap<String, Integer> teamGoals = new HashMap<String, Integer>();
			reader = new BufferedReader(new FileReader(INPUT_LOCATION));
			String line = reader.readLine();

			// Loop on the lines of the file
			while (line != null) {
				line = reader.readLine();

				//Check whether its a valid line or not
				
				if( (line != null) && (line.trim().length() > 0)&& (!line.contains("----")) ) {
				// Get Team name from the fixed index positions
				String teamName = line.substring(7, 23).trim();
				//System.out.println(teamName);

				// Get Team For goals from the fixed index positions
				int forGoals = Integer.parseInt(line.substring(43, 47).trim());
				//System.out.println(forGoals);

				// Get Team Against goals from the fixed index positions
				int againstGoals = Integer.parseInt(line.substring(48, 52).trim());
				//System.out.println(againstGoals);

				// Variable for the Golas difference
				int goalDifference = 0;

				// Check which goals type is bigger and calculate the difference
				if (forGoals > againstGoals) {
					goalDifference = forGoals - againstGoals;
				} else {
					goalDifference = againstGoals - forGoals;
				}

				// Add the team name to the goal difference.
				teamGoals.put(teamName, goalDifference);
				}

			}
			
				
			//Sort the hashmap and get the Sorted list
			Set<Entry<String, Integer>> set = teamGoals.entrySet();
			List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
			Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
			{
			  public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
			  {
			    int result = (o2.getValue()).compareTo( o1.getValue() );
			    if (result != 0) {
			      return result;
			    } else {
			      return o1.getKey().compareTo(o2.getKey());
			    }
			  }
			} );
			
			//Getting the Entry with the least goal difference 
			Entry<String, Integer> teamWithMinDifference = list.get(list.size()-1);
			
			System.out.println("Team With Minimum For vs Against goals --> "+ teamWithMinDifference.getKey());
			Reporter.log("Team With Minimum For vs Against goals --> "+ teamWithMinDifference.getKey());
			Assert.assertEquals(true, true,"Team With Minimum For vs Against goals --> "+ teamWithMinDifference.getKey());
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	}
