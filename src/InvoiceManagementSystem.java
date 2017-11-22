/*
 * HW5_104403016 資管3A 杜孝顯
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Map;

public class InvoiceManagementSystem {

	public static void main(String[] args) {
		
		//data
		Invoice[] invoices = {
				new Invoice(83,"Electric Sander", 7, 57.98),
				new Invoice(24, "Power Saw", 18, 99.99),
				new Invoice(7, "Sledge Hammer", 11, 21.50),
				new Invoice(77, "Hammmer", 76, 11.99),
				new Invoice(39, "Lawn Mower", 3, 79.50),
	         	new Invoice(68, "Screwdriver", 106, 6.99),
	         	new Invoice(56, "Jig saw", 21, 11.00),
				new Invoice(3,"Wrench", 34, 7.50),
				new Invoice(45,"Wrench", 13, 7.50),
				new Invoice(22,"Hammer", 47, 11.99)};

		int stopSys = 0;
		
		do {		
			System.out.println("Welcome to invoices management system!");
			System.out.println("Functions: sort/report/select");
			printExitInstructions();
			Scanner input = new Scanner(System.in);
			String command = input.nextLine();
			
			System.out.println("You entered: " + command);
			
			// get List view of the invoices
			List<Invoice> list = Arrays.asList(invoices);
			
			if(command.equalsIgnoreCase("sort")){
				//sort function
				int stopSort = 0;
				do {
				  printALine();
			      // get List view of the invoices			      
			      System.out.println("Order by ID/Quantity/Price");
			      printExitInstructions();
		    	  printGoBack();
			      Scanner sortInput = new Scanner(System.in);
			      String sortCommand = sortInput.next();
			  
			      System.out.println("You entered: " + sortCommand);
		    	  printALine();
			      if(sortCommand.equalsIgnoreCase("ID")) {
			    	  //Invoices sorted by ID
			  			System.out.println("Invoices sorted by ID");
			  			list.stream()
			  			.sorted(Comparator.comparing(Invoice::getPartNumber))
			  			.forEach(System.out::println);
			      } else if (sortCommand.equalsIgnoreCase("Quantity")) {			      
			    	  // Invoices sorted by Quantity
			    	  System.out.println("Invoices sorted by Quantity");
			    	  list.stream()
			          .sorted(Comparator.comparing(Invoice::getQuantity))
			          .forEach(System.out::println);
			      } else if (sortCommand.equalsIgnoreCase("Price")) {     
				      // Invoices sorted by Price
				      System.out.println("Invoices sorted by Price");
				      list.stream()
			          .sorted(Comparator.comparing(Invoice::getPrice))
			          .forEach(System.out::println);
			      } else if (sortCommand.equalsIgnoreCase("-1")){
			    	  stopSys = exitProcedure();		
			      } else if (sortCommand.equalsIgnoreCase("function")){
			    	  //for when the user wants to go back to functions
			    	  stopSort = 1;
			      } else {
			    	  printInputError();
			      }
			      
				    
				}while(stopSort == 0);
				
			} else if (command.equalsIgnoreCase("report")) {
				//report function
				System.out.println("Invoice group by description: \n");
				Map<String, Double> groupByDescr =
						list.stream()
						.collect(Collectors.groupingBy(Invoice::getPartDescription,Collectors.summingDouble(Invoice::getTotal)));
				List<Map.Entry<String,Double>> sortList = new ArrayList<Map.Entry<String,Double>>(groupByDescr.entrySet());
				Collections.sort(sortList,(m, m2) ->m.getValue().compareTo(m2.getValue()));
				sortList.forEach(m -> System.out.printf("Description: %-20sInvoice amount:%.2f\n",m.getKey(),m.getValue()));
				printALine();
				
			} else if (command.equalsIgnoreCase("select")) {
				//select function
				int stopSelect = 0;
				do {
					try {
						System.out.println("Input the range to show (min, max)");
						printExitInstructions();
						printGoBack();
						
						Scanner selectInput = new Scanner(System.in);										
						String selectInputString = selectInput.nextLine();
						String selectMinMax[] = selectInputString.split(",");
											
						if (selectInputString.equalsIgnoreCase("function")) {
					    	//for when the user wants to go back to functions
							stopSelect = 1;
						} else if (selectInputString.equalsIgnoreCase("-1")) {
							stopSys = exitProcedure();
						} else {						
						double selectMin = Double.parseDouble(selectMinMax[0]);
						double selectMax = Double.parseDouble(selectMinMax[1]);	
						
						// Predicate that returns true for invoices in range
						Predicate<Invoice> minMaxSelect = 
							e -> (e.getPrice() * e.getQuantity() >= selectMin && e.getPrice() * e.getQuantity() <= selectMax);
	
						// Display invoices in range
					    printALine();
						System.out.printf(
					         "%nInvoices in range of " + selectMinMax[0] + " and " +selectMinMax[1]+"%n");
						list.stream()
						.filter(minMaxSelect)
						.sorted(Comparator.comparing(Invoice::getTotal))									// sorted into ascending order by price
						.forEach(
								invoice ->{
									System.out.printf("Description: %-15s Invoice Amount:  $%,6.2f%n", invoice.getPartDescription(), invoice.getPrice() * invoice.getQuantity());
									}							
								);
											
							}	//close if else
						} catch (ArrayIndexOutOfBoundsException e){
							printInputError();
						}
									
					} while (stopSelect == 0);
				
			} else if (command.equalsIgnoreCase("-1")) {
				stopSys = exitProcedure();
			} else {
				printInputError();
			}
			
		}while(stopSys == 0);
		
		//system exit when done
		System.exit(0);
		
	}
	
	private static void printExitInstructions() {
		//print exit instructions
		System.out.println("Enter -1 to exit");	
	}

	private static void printGoBack() {
		//print go back instructions
		String goBack = "To go back to function options, enter function";
		System.out.println(goBack);
	}
	
	private static void printALine() {
		//print a line
		int lineMaker = 0;
		while(lineMaker <= 80) {
			if(lineMaker == 80) {
				System.out.print("-\n");
				break;
			}
			System.out.print("-");
			lineMaker ++;
		}
	}

	private static void printInputError() {
		//print error message
		String inputError = "Invalid Input. Try again.";
		System.out.println(inputError);
	}
	
	private static int exitProcedure() {
		//prepare for exit
		String exitMessage = "Bye!";
		System.out.println(exitMessage);
		return 1;
	}
}
