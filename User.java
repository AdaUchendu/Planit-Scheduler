/*Adaku Uchendu
   Buban Ndeta 
   Steven Sommers 
   Yeabi Demissie 
   Tony Smith  
   Akhil Naraparaju
   THIS CLASS CONTAINS ANY METHOD WHERE FILE WRITING IS REQUIRED
  THIS INCLUDES SCHEDULE CREATION AND DELETION
  AS WELL AS PASSWORD GENERATION AND ASSIGNMENT
*/


interface userInterface {
    String getUserID();
    String getUserFile();
    void setUserFile(String user);
    void setUserID(String iD);

}


class User implements userInterface {
   String iD;
   String userFile;
         
   //no-arg constructor
   public User() {
      iD = "00";
      userFile = "C:\\PlanIt\\Users\\00.txt.";
   }   
   public void setUserFile(String user) {
      userFile = user;
   }
   public void setUserID(String iD) {
      this.iD = iD;
   }      
   public String getUserFile() {
      return userFile;
   }        
   public String getUserID() {
      return iD;
   }
}  
