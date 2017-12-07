/*AUTHORED BY STEVEN SOMERS 11/30/2016
  Adaku Uchendu
  Buban Ndeta 
  Steven Sommers 
  Yeabi Demissie 
  Tony Smith  
  THIS CLASS STORES NEW APPOINTMENT VARIABLES BASED ON USER INPUT AND IS FUNDAMENTAL IN THE CREATION OF A NEW APPOINTMENT
*/

import java.util.Calendar;
class Appointment {
   String day, month, year, time, ampm, hour, minute, dateString, appointmentString, user;
   int dayInt, monthInt, yearInt, hourInt, minuteInt, militaryTime;
   boolean valid, monthOverlap;
   
   
   
   //no-arg constructor
   public Appointment() {
      day = "1";
      month = "1";
      year = "2000";
      time = "01:00PM";
      ampm = "AM";
      hour = "01";
      minute = "01";
      dateString = "";
      appointmentString = "";
      dayInt = 1;
      monthInt = 1;
      yearInt = 2000;
      hourInt = 1;
      minuteInt = 1;
      militaryTime = 0;
      valid = false;
      monthOverlap = false;
   }
   
   
   
   //Setters   
   public void setDay(String day) {
      this.day = day;
      dayInt = Integer.parseInt(day);
   }
   public void setMonth(String month) {
      this.month = month;
      monthInt = Integer.parseInt(month);
   }
   public void setYear(String year) {
      this.year = year;
      yearInt = Integer.parseInt(year);
   }
   public void setAMPM(String ampm) {
      this.ampm = ampm;
   }
   public void setHour(String hour) {
      this.hour = hour;
      hourInt = Integer.parseInt(hour);
   }
   public void setMinute(String minute) {
      this.minute = minute;
      minuteInt = Integer.parseInt(minute);
   }
   //Use military time to save appointments
   public void setDateString() {
      if (militaryTime <= 9) {
         dateString = month + "/" + day + "/" + year + 
         ":" + "0" + militaryTime + ":" + minute;
      }
      else {
         dateString = month + "/" + day + "/" + year + 
         ":" + militaryTime + ":" + minute;
      }
   }
   public void setMilitaryTime() {
      if (ampm.equals("PM") && hourInt != 12) {
         militaryTime = hourInt + 12;
      }
      else if (ampm.equals("AM") && hourInt == 12) {
         militaryTime = hourInt - 12;
      } 
      else {
         militaryTime = hourInt;
      }
      setDateString();     
   }
   public void setValid(boolean newValid) {
      valid = newValid;
   }
   public void setMonthOverlap(boolean newMonthOverlap) {
      monthOverlap = newMonthOverlap;
   }
   
   
   
   //Getters
   public String getDay() {
      return day;
   }
   public int getDayInt() {
      return dayInt;
   }
   public String getMonth() {
      return month;
   }
   public int getMonthInt() {
      return monthInt;
   }
   public String getYear() {
      return year;
   }
   public int getYearInt() {
      return yearInt;
   }
   public String getAMPM() {
      return ampm;
   }
   public String getHour() {
      return hour;
   }
   public int getHourInt() {
      return hourInt;
   }
   public String getMinute() {
      return minute;
   }
   public int getMinuteInt() {
      return minuteInt;
   }
   public String getDateString() {
      return dateString;
   }
   public boolean getValid() {
      return valid;
   }
   public boolean getMonthOverlap() {
      return monthOverlap;
   }
   public String getUser() {
      return user;
   }
   
   //Establish date
   public void setDate(String newDate) {
      //Get first two characters, convert to integers for variable month   
      if (newDate.charAt(1) == '/') {                                   
         //if month is SINGLE digit
         setMonth("0" + Character.toString(newDate.charAt(0)));
      }
      else {                                                           
         //if month is DOUBLE digit
         setMonth(Character.toString(newDate.charAt(0)) + Character.toString(newDate.charAt(1)));
      }
      //Get determine where "/" is, establish day and year
      if (newDate.charAt(3) == '/') {                                   
         //if month and day are SINGLE digit
         setDay("0" + Character.toString(newDate.charAt(2)));
         setYear(Character.toString(newDate.charAt(4)) + Character.toString(newDate.charAt(5)) + 
         Character.toString(newDate.charAt(6)) + Character.toString(newDate.charAt(7)));
      }   
      //if month is SINGLE digit and day is DOUBLE digit
      else if (newDate.charAt(1) == '/' && newDate.charAt(4) == '/') {  
         setDay(Character.toString(newDate.charAt(2)) + Character.toString(newDate.charAt(3)));
         setYear(Character.toString(newDate.charAt(5)) + Character.toString(newDate.charAt(6)) + 
         Character.toString(newDate.charAt(7)) + Character.toString(newDate.charAt(8)));
      }
      //if month is DOUBLE digit and day is SINGLE digit
      else if (newDate.charAt(2) == '/' && newDate.charAt(4) == '/') {  
         setDay("0" + Character.toString(newDate.charAt(3)));
         setYear(Character.toString(newDate.charAt(5)) + Character.toString(newDate.charAt(6)) + 
         Character.toString(newDate.charAt(7)) + Character.toString(newDate.charAt(8)));      
      } 
      //if month is DOUBLE digit and day is DOUBLE digit               
      else {                                                            
         setDay(Character.toString(newDate.charAt(3)) + Character.toString(newDate.charAt(4)));
         setYear(Character.toString(newDate.charAt(6)) + Character.toString(newDate.charAt(7)) + 
         Character.toString(newDate.charAt(8)) + Character.toString(newDate.charAt(9)));      
      }    
   }
   
   
   //Set the appointment time based on user input
   public void setTime(String newTime) {
      if (newTime.length() == 6) {
         setAMPM(Character.toString(newTime.charAt(4)) + Character.toString(newTime.charAt(5)));
         //Get integer value of hour
         setHour("0" + Character.toString(newTime.charAt(0)));
         //Get integer value of minute
         setMinute(Character.toString(newTime.charAt(2)) + Character.toString(newTime.charAt(3)));
      }
      else if (newTime.length() == 7) {
         setAMPM(Character.toString(newTime.charAt(5)) + Character.toString(newTime.charAt(6)));
         //Get integer value of hour
         setHour(Character.toString(newTime.charAt(0)) + Character.toString(newTime.charAt(1)));
         //Get integer value of minute
         setMinute(Character.toString(newTime.charAt(3)) + Character.toString(newTime.charAt(4)));
      } 
      setMilitaryTime(); 
   }//END OF SET APPOINTMENT TIME
   
   
   
   //Get the length of the month based on numerical value
   public int lastDay(int monthInt, int yearInt) {
      switch (monthInt) {
      case 1: return 31;
      //Adjust for leap year
      case 2: if (((yearInt % 4 == 0) && yearInt % 100 != 0) || yearInt % 400 == 0) {
                 return 29;
              }
              else {
                 return 28;
              }
      case 3: return 31;
      case 4: return 30;
      case 5: return 31;
      case 6: return 30;
      case 7: return 31;
      case 8: return 31;
      case 9: return 30;
      case 10: return 31;
      case 11: return 30;
      case 12: return 31;
      }
      return 0;
   }//END OF LAST DAY OF MONTH METHOD
   
   
   
   //Look for an overlap within one hour
   public boolean hourOverlap(String line) {
      String newDate = line.substring(0, 10);
      String hourString = line.substring(11, 13);
      String minuteString = line.substring(14, 16);
      int lineHour = Integer.parseInt(hourString);
      int lineMinute = Integer.parseInt(minuteString);
      
      //Check for matching date 
      if (newDate.equals(dateString.substring(0, 10)) && 
      //Check for matching hour
      (lineHour == militaryTime ||
      //Check for match within one hour
      ((lineHour == militaryTime - 1 && minuteInt - lineMinute <= 0) || 
      (lineHour == militaryTime + 1 && minuteInt - lineMinute >= 0) ||
      //Account for midnight
      ((lineHour == 0 && militaryTime == 23) && minuteInt - lineMinute <= 0) ||
      ((lineHour == 23 && militaryTime == 0) && minuteInt - lineMinute >= 0)))) {   
         return true;
      }
      else {
         //Return false if no overlap exists 
         //****THIS IS WHAT IS NEEDED TO CONTINUE CREATING AN APPOINTMENT
         return false;
      }
   }
   
   
   //Look for exact matching appointments
   public boolean exactMatch(String line) {
      String newDate = line.substring(0, 16);    
      //Check for matching date 
      if (newDate.equals(dateString)) {   
         return true;
      }
      else {
         return false;
      }
   }//END OF EXACT MATCH METHOD

   
   //Look for overlapping appointments within one week
   public void overlapMonth(String line) {
      String lineMonth = line.substring(0, 2);
      int lineDay = Integer.parseInt(line.substring(3, 5));
      String lineYear = line.substring(6,10);
      
      //Check for matching month
      if ((lineMonth.equals(dateString.substring(0, 2))) && 
      //Check for matching year
      (lineYear.equals(dateString.substring(6, 10))) &&
      //Check for match within seven days
      ((dayInt - lineDay <= 7) || (lineDay - dayInt >= -7))) {
         setMonthOverlap(true);
      }
   }//END OF OVERLAP MONTH METHOD
   
   
   
   //Determine the name of the day of the week from numerical value
   Calendar appointmentDay = Calendar.getInstance();
   public String getDayName() {
      //Get day of week
      appointmentDay.set(yearInt, (monthInt - 1), dayInt);
      int dayOfWeek = appointmentDay.get(Calendar.DAY_OF_WEEK);
      String dayName;
      switch (dayOfWeek) {
      case 1: dayName = "Sunday"; return dayName;
      case 2: dayName = "Monday"; return dayName;
      case 3: dayName = "Tuesday"; return dayName;
      case 4: dayName = "Wednesday"; return dayName;
      case 5: dayName = "Thursday"; return dayName;
      case 6: dayName = "Friday"; return dayName;
      case 7: dayName = "Saturday"; return dayName;
      }
      return "Sunday";
   }//END OF DAY OF WEEK METHOD
   
   
   
   //Determine the name of a month from a numerical value
   public String getMonthName() {
      //Get name of month
      String monthName;
      switch (monthInt) {
      case 1: monthName = "January"; return monthName;
      case 2: monthName = "February"; return monthName;
      case 3: monthName = "March"; return monthName;
      case 4: monthName = "April"; return monthName;
      case 5: monthName = "May"; return monthName;
      case 6: monthName = "June"; return monthName;
      case 7: monthName = "July"; return monthName;
      case 8: monthName = "August"; return monthName;
      case 9: monthName = "September"; return monthName;      
      case 10: monthName = "October"; return monthName;
      case 11: monthName = "November"; return monthName;
      case 12: monthName = "December"; return monthName;
      }
      return "January";
   }//END OF GET MONTH NAME METHOD
   
   

   Search fileSearch = new Search();
   //Display finalized appointment to user
   public void displayAppointment(String newNote, String userFile, String newUser) {
      String user = fileSearch.getUserName(userFile);
      user = user.toUpperCase();
      System.out.println("New appointment created for user " + user + 
      " on:\n" + getDayName() + " " + getMonthName() + 
      " " + day + ", " + year + " at " + hourInt + ":" + minute + ampm + ".");
         
      //If note was made, display it
      if (newNote.length() == 0) {
         System.out.println("No note attached.");
         //Attach date to user name
         appointmentString = dateString + ":" + user + "*";
         if (valid = true) {
            WriteToFile write = new WriteToFile();
            write.createAppointment(userFile, appointmentString);
      }
      else {
         System.out.println("Could not create appointment");
      }
      }
      //If no note found
      else {
         System.out.println("Note attached: " + newNote);
         //Attach date to user name and note
         appointmentString = dateString + ":" + user + "*" + newNote; 
         if (valid = true) {
            WriteToFile write = new WriteToFile();
            write.createAppointment(userFile, appointmentString);
         }
         else {
            System.out.println("Could not create appointment");
         } 
      }
   }   
}
