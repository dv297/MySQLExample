package edu.sc.cse.rdc.mysqlexample;

/**
 * Created by Daniel Vu on 3/17/2015.
 */
public class Item {
    private String message;
    private long id;

    public Item(){
        message = "";
        id = -1;
    }

    public Item(String _message) {
        message = _message;
    }

    public void setId(long _id){ id = _id ;   }
    public long getId(){      return id;      }
    public void setMessage(String msg){ message = msg;}
    public String getMessage(){ return message; }
    public String toString(){   return message; }
}
