/*Adaku Uchendu
  Buban Ndeta 
  Steven Sommers 
  Yeabi Demissie 
  Tony Smith  
  Akhil Naraparaju
  THIS CLASS CONTAINS METHODS THAT SEARCH CLIENT OR EMPLOYEE FILES
  INCLUDES VERIFYING THE EXISTENCE OF CLIENTS OR EMPLOYEES
  RETRIEVES EMPLOYEE ACCESS AND NAME, AS WELL AS LOCATES AND DISPLAYS APPOINTMENTS WITH NOTES
*/
import java.io.*;

abstract class SearchCalendar {
    void doesUserExist() {}
    String getUserName(String fileName) {
        return fileName;
    }
    void searchAppointments() {}
    void searchMonthlyAppointments() {}
}


class Search extends SearchCalendar {
   //If .txt file exists, return true
   public boolean doesUserExist(String fileName) {
      //remove whitespace
      File searchUser = new File("C:\\PlanIt\\Users\\" + fileName + ".txt");
      if (searchUser.exists()) {
         return true;
      }
      else {
         return false;
      }
   }
  
     
   //Store user name from file
   public String getUserName(String fileName) {
      File checkName = new File("C:\\PlanIt\\Users\\" + fileName + ".txt");
      try {
         BufferedReader pullName = new BufferedReader(new FileReader(checkName));
         pullName.readLine();
         String name = pullName.readLine();
         pullName.close();
         return name;
      }
      catch (FileNotFoundException a) {
         System.out.println("Error obtaining user name.");
         return "Null";
      }
      catch (IOException a) {
         System.out.println("Error reading user file for a name.");
         return "Null";
      }
   } 
   
   
   public void searchAppointments(String year, String fileName) {
   //user schedule display
      File searchFile = new File("c:\\PlanIt\\Users\\" + fileName + "a" + ".txt");
      String line;
      String ampm;
      try {
         //Create a temporary file
         BufferedReader tempReader = new BufferedReader(new FileReader(searchFile));
         while ((line = tempReader.readLine()) != null) {
            String existingYear = line.substring(6, 10);                                   
            if (existingYear.equals(year)) {
               String minutes = line.substring(14, 16);
               int foundMilitaryTime = Integer.parseInt(line.substring(11, 13));
               int userEnd = line.lastIndexOf("*");
               int userStart = line.lastIndexOf("@") + 1;
               String user = line.substring(userStart);
               if (foundMilitaryTime > 12) {
                  foundMilitaryTime = foundMilitaryTime - 12;
                  ampm = "PM";
                  System.out.println("Appointment found on " + line.substring(0, 10) + " at " + foundMilitaryTime + 
                  ":" + minutes + ampm + ".");
               }
               else if (foundMilitaryTime == 0) {
                  foundMilitaryTime = foundMilitaryTime + 12;
                  ampm = "PM";
                  System.out.println("Appointment found on " + line.substring(0, 10) + " at " + foundMilitaryTime + 
                  ":" + minutes + ampm + ".");
               }
               else {
                  ampm = "AM";
                  System.out.println("Appointment found on " + line.substring(0, 10) + " at " + foundMilitaryTime + 
                  ":" + minutes + ampm + ".");
               }
               int searchEnd = line.lastIndexOf("*");
               try {
               String foundNote = line.substring(searchEnd + 1);
                  if (foundNote.equals("")) {
                  }
                  else {
                     System.out.println("Note: " + line.substring(searchEnd + 1));
                  }
               }
               catch (StringIndexOutOfBoundsException a) {
                  System.out.println("No note found.");
               }
            }
         }
      }     
      catch (IOException a) {
         System.out.println("Error writing to temporary file.");
      } 
   }
   
         
   //Locates and displays monthly appointments with notes
   public void searchMonthlyAppointments(String monthYear, String fileName) {
      //Separate month from year
      InputChecker verifyInput = new InputChecker();
      String[] splitMonth = monthYear.split("\\s+");
      String month = splitMonth[0];
      month = month.toUpperCase();
      month = verifyInput.getMonthNumber(month);
      String year = splitMonth[1];
      File searchFile = new File("c:\\PlanIt\\Users\\" + fileName + "a.txt");
      String line;
      String ampm;
      try {
         //Create a temporary file
         BufferedReader tempReader = new BufferedReader(new FileReader(searchFile));
         while ((line = tempReader.readLine()) != null) {
            String existingYear = line.substring(6, 10); 
            String existingMonth = line.substring(0,2);                                 
            if (existingYear.equals(year) && existingMonth.equals(month)) {
               int foundMilitaryTime = Integer.parseInt(line.substring(11, 13));
               int userEnd = line.lastIndexOf("*");
               int userStart = line.lastIndexOf("*") + 1;
               String user = line.substring(userStart);
               String minutes = line.substring(14, 16);
               if (foundMilitaryTime > 12) {
                  foundMilitaryTime = foundMilitaryTime - 12;
                  ampm = "PM";
                  System.out.println("Appointment found on " + line.substring(0, 10) + " at " + foundMilitaryTime + 
                  ":" + minutes + ampm + ".");
               }
               else if (foundMilitaryTime == 0) {
                  foundMilitaryTime = foundMilitaryTime + 12;
                  ampm = "PM";
                  System.out.println("Appointment found on " + line.substring(0, 10) + " at " + foundMilitaryTime + 
                  ":" + minutes + ampm + ".");
               }
               else {
                  ampm = "AM";
                  System.out.println("Appointment found on " + line.substring(0, 10) + " at " + foundMilitaryTime + 
                  ":" + minutes + ampm + ".");
               }
               try {
                  String foundNote = line.substring(userEnd + 1);
                  if (foundNote.equals("")) {}
                  else {
                     System.out.println("Note: " + line.substring(userEnd + 1));
                  }
               }
               catch (StringIndexOutOfBoundsException a) {
                  System.out.println("No note found.");
               }
            }
         }
      }     
      catch (IOException a) {
         System.out.println("Error writing to temporary file.");   
      }
   }
}
