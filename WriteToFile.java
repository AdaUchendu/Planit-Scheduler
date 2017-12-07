/*
Planit Group Project: 
Buban Ndeta
Adaku Uchendu
Steven Sommers
Akhil Naraparaju 
Yeabi Demissie
THIS CLASS CONTAINS ANY METHOD WHERE FILE WRITING IS REQUIRED
THIS INCLUDES SCHEDULE CREATION AND DELETION, AND USER CREATION
AS WELL AS PASSWORD GENERATION AND ASSIGNMENT
*/

import java.io.*;
class WriteToFile {
   //On first startup, create folders and default files
   public void startUp() {
      File userFolder = new File("C:\\PlanIt\\Users");
      //If user folder does not exist, create it
      if (!userFolder.exists()) {
         userFolder.mkdirs();  
      } 
      File tempFile = new File("c:\\PlanIt\\Users\\00.txt");
      if (!tempFile.exists()) {
         createUserFile("00", "password", "User");
      }
   }
   
 //Create new user file
   public void createUserFile(String fileName, String password, String name) {
      File newUser = new File("C:\\PlanIt\\Users\\" + fileName + ".txt");
      File newUserAppointments = new File("C:\\PlanIt\\Users\\" + fileName + "a" + ".txt");
      try {
         newUser.createNewFile();
         newUserAppointments.createNewFile();
         //Add information to new file
         try {
            BufferedWriter out = new BufferedWriter(new FileWriter(newUser, true));
            out.append("Password: " + password);
            out.newLine();
            out.append(name);
            out.close();   
         }
         catch (IOException a) {
         }  
         System.out.println("New user created. LoginID: " + fileName + " Password: " + password);
      }
      catch (IOException a) {
         System.out.println("Could not create new user file.");
      }
   } 
   
   
   //Generate a new random password
   public static String generatePassword() {
      String[] colors = {"blue", "red", "yellow", "green", 
      "purple", "pink", "brown", "black", "white"};
      //Pick a color and capitalize a random letter
      String color = colors[(int)(Math.random() * 9)];
      int pickANumber = color.length() - (int)(Math.random() * 2);
      String scrambledMiddle = Character.toUpperCase(color.charAt(pickANumber - 1)) + color.substring(0, (pickANumber - 2));
      //Get random numbers
      int randomPrefix = (int)(Math.random() * 10);
      int randomSuffix = (int)(Math.random() * 100);
      //Construct password
      String password = randomPrefix + scrambledMiddle + randomSuffix; 
      return password;
   } //END OF GENERATE PASSWORD METHOD
   
   
   //Change file to overwrite old password with new password
   public static void changePassword(String newPassword, String user) {
      File tempFile = new File("c:\\PlanIt\\Users\\00.txt");
      File userFile = new File(user);
      String line;
      try {
         //Create a temporary file
         BufferedWriter tempOut = new BufferedWriter(new FileWriter(tempFile, false)); 
         BufferedReader tempReader = new BufferedReader(new FileReader(userFile));
         //Keep reading until there is no data
         for (int i = 0; i<2; i++) {
         line = tempReader.readLine();                         
           try {
               if ((line.substring(0, 9)).equals("Password: ")) {
                  tempOut.append("Password: ");
               }
               //Move all lines into temp file
               else {
                  tempOut.append(line);                           
                  tempOut.newLine();
               }
            }
            catch (StringIndexOutOfBoundsException a) {
               tempOut.append(line);                           
               tempOut.newLine();
            }
         }
         
         tempOut.close();   
      }     
      catch (IOException a) {
      System.out.println("Error writing to temporary file.");
      }              
      //Overwrite old file with temp file to update password
      try {
         BufferedWriter originalFile = new BufferedWriter(new FileWriter(userFile, false));
         BufferedReader tempReader = new BufferedReader(new FileReader(tempFile));
         //Keep reading until there is no data
         while((line = tempReader.readLine()) != null) {                           
            try {
               if ((line.substring(0, 9)).equals("Password:")) {
                  originalFile.append("Password: " + newPassword);
                  originalFile.newLine();
               }
               //Move all lines into temp file
               else {
                  originalFile.append(line);                           
                  originalFile.newLine();            
               }
            }
            catch (StringIndexOutOfBoundsException a) {
               originalFile.append(line);                           
               originalFile.newLine();
            }
         }
         originalFile.close();
      }                  
      catch (IOException a) {
         System.out.println("Error overwriting old file.");
      }
      catch (java.lang.NullPointerException a) {
      }
   }//END OF CHANGE PASSWORD METHOD      
   
   
   //Create appointment
   public void createAppointment(String user, String newAppointment) {
      File userFile = new File("C:\\PlanIt\\Users\\" + user + "a.txt");
      //Update file for the user
      updateUser(userFile, newAppointment);
   }
    
   
   //Rewrite file to add new appointment
   public void updateUser(File userFile, String appointment) {
      File tempFile = new File("c:\\PlanIt\\Users\\00.txt");
      String line;
      try {
         //Create a temporary file
         BufferedWriter tempOut = new BufferedWriter(new FileWriter(tempFile, false)); 
         BufferedReader tempReader = new BufferedReader(new FileReader(userFile));
         //Keep reading until there is no data
         while ((line = tempReader.readLine()) != null) {                                   
            tempOut.append(line);                           
            tempOut.newLine();
         }
         tempOut.append(appointment);
         tempOut.close();   
      }     
      catch (IOException a) {
      System.out.println("Error writing to temporary file.");
      }              
      //Overwrite old file with temp file to update user
      try {
         BufferedWriter originalFile = new BufferedWriter(new FileWriter(userFile, false));
         BufferedReader tempReader = new BufferedReader(new FileReader(tempFile));
         //Keep reading until there is no data
         while((line = tempReader.readLine()) != null) {                           
            originalFile.append(line);                           
            originalFile.newLine();            
            
         }
         originalFile.close();
      }                  
      catch (IOException a) {
         System.out.println("Error overwriting old file.");
      }
      catch (java.lang.NullPointerException a) {
      }
   }//END OF NEW EMPLOYEE APPOINTMENT METHOD
   
   
   //Delete targeted appointment
   public static void deleteAppointment(String target, String user) {
      //Delete appointment on user end
      File zeroFile = new File("c:\\PlanIt\\Users\\00.txt");
      File userFile = new File("C:\\PlanIt\\Users\\" + user + ".txt");
      String line;
      try {
         //Create a temporary file
         BufferedWriter tempOut = new BufferedWriter(new FileWriter(zeroFile, false)); 
         BufferedReader tempReader = new BufferedReader(new FileReader(userFile));
         //Keep reading until there is no data
         while ((line = tempReader.readLine()) != null) {
            //If target is found, do not copy it into the temp file                         
            try {
               if ((line.substring(0, 16)).equals(target)) {
               }
               //Move all lines into temp file
               else {
                  tempOut.append(line);                           
                  tempOut.newLine();
               }
            }
            catch (StringIndexOutOfBoundsException b) {
               tempOut.append(line);                           
                  tempOut.newLine();
            }
         }
         tempOut.close();   
      }     
      catch (IOException a) {
      System.out.println("Error writing to temporary file.");
      }              
      //Overwrite old file with temp file to with all appointments excluding the target
      try {
         BufferedWriter originalFile = new BufferedWriter(new FileWriter(userFile, false));
         BufferedReader tempReader = new BufferedReader(new FileReader(zeroFile));
         //Keep reading until there is no data
         while((line = tempReader.readLine()) != null) {                           
               originalFile.append(line);                           
               originalFile.newLine();            
         }
         originalFile.close();
         System.out.println("Appointment deleted from schedule.");
      }                  
      catch (IOException a) {
         System.out.println("Error overwriting old file.");
      }
      catch (java.lang.NullPointerException a) {
      }
   }//END OF DELETE APPOINTMENT METHOD   
}
