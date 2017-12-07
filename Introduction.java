/* Team Planit: 
   Adaku Uchendu
   Buban Ndeta 
   Steven Sommers 
   Yeabi Demissie 
   Tony Smith  
   Akhil Naraparaju
 */

public class Introduction {

    public String Message() {
        String messages = "Welcome to PlanIt Scheduler!";
        return messages;
    }

    public String Message(int num, String message) {
        if (num <= 1)
            return message;
        else
            return Message(num-1, message);
    }

}

