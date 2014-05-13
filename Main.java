/*
* Bitrader Alpha CLI Tests
* ----- Authors: Tyler McCabe && John O'Rourke-----
* Last Edited 5-12-2014 -T
* Main Class of Bitrader
* See README for more information
*
*/




package bitrader; //Arbitray package, change at will



import java.util.Scanner;

public class Main {
   public static void main(String[] args) throws Exception {
        System.out.println("--------------Bitrader PRE-ALPHA-------------");  //ASCII fun
        System.out.println("Enter Command to begin trading or type 'Man' for help");  //Main command prompt
        boolean keepAlive=true;
        
        while(keepAlive){
        System.out.println("------------------------------------------");
        Scanner userIn = new Scanner(System.in);
        System.out.print(">");String userInput=userIn.next();
        
        if(userInput.equals("Man")){ //Opens Man page
            System.out.println("\n++++++++++++++++++++++++++++++++++++++++++");  
            System.out.println("GetPrice: Gets the current buy or sell price");
            System.out.println("------------------------------------------");
            System.out.println("Buy: Enter into Bitcoin purchase mode");
            System.out.println("------------------------------------------");
            System.out.println("Sell: Enter into Bitcoin sell mode");
            System.out.println("------------------------------------------");
            System.out.println("GetBalance: Grabs account funds");
            System.out.println("------------------------------------------");
            System.out.println("PaymentMethods: Current bank accounts");
            System.out.println("------------------------------------------");           
            System.out.println("(Not avalible yet) AutoTrade: Enters Autotrade mode....PREALPHA! Caution.");
             System.out.println("------------------------------------------");
            System.out.println("Quit: Exits program");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++\n");
       
        }
        
         if(userInput.equals("GetBalance")){  //Grabs account balance
            Connection balance = new Connection();
            System.out.println(balance.getHttp("https://coinbase.com/api/v1/accounts", ""));
        }
         
         
          if(userInput.equals("GetPrice")){  //Gets current price reference info
              
              System.out.println("\nBuy or Sell Price?:"); //Gets current Price
              Scanner priceIn = new Scanner(System.in);
              System.out.print(">");String priceInput=priceIn.next();
              
             if(priceInput.equals("Buy")){  
            Connection buyPrice = new Connection();
            System.out.println("Buy Price:" + buyPrice.getHttp("https://coinbase.com/api/v1/prices/buy", ""));
        }
              if(priceInput.equals("Sell")){  
            Connection sellPrice = new Connection();
            System.out.println("Sell Price:" + sellPrice.getHttp("https://coinbase.com/api/v1/prices/sell", ""));
        }
        }
          
          if(userInput.equals("PaymentMethods")){  //Grabs account balance
            Connection paymentMeth = new Connection();
            System.out.println(paymentMeth.getHttp("https://coinbase.com/api/v1/payment_methods", ""));
        }
         
         if(userInput.equals("Buy")){    //Enters purchase mode
            Scanner confirmBuy = new Scanner(System.in);
            System.out.println("ARE YOU SURE YOU WANT TO BUY? Y/N");
            System.out.print(">");String userActionBuy=confirmBuy.next();
            
            
            if(userActionBuy.equals("Y")){
             Connection buy = new Connection();
             Scanner userQty = new Scanner(System.in);
             System.out.println("Quantity?");
             System.out.print(">");String qty=userQty.next();
             System.out.println(buy.getHttp("https://coinbase.com/api/v1/buys", "{\"qty\":\""+qty+"\"}" )); //Must Be in JSON Format!
            }
            
            
        }
        
          if(userInput.equals("Sell")){    //Enters sell mode
            Scanner confirmSell = new Scanner(System.in);
            System.out.println("ARE YOU SURE YOU WANT TO Sell? Y/N");
            System.out.print(">");String userActionBuy=confirmSell.next();
            
            
            if(userActionBuy.equals("Y")){
             Connection sell = new Connection();
             Scanner userQty = new Scanner(System.in);
             System.out.println("Quantity?");
             System.out.print(">");String qty=userQty.next();
             System.out.println(sell.getHttp("https://coinbase.com/api/v1/sells", "{\"qty\":\""+qty+"\"}" )); //Must Be in JSON Format!
            }
            
            
        }
          
        if(userInput.equals("Quit")){  //Kills program
            keepAlive=false;
        }
        
       
        } //end Loop
        System.out.println("--------------------DONE---------------------");
    }   //end Main
} //end class


// -------------------NOTES-------------------------
   /*
        Connection balance = new Connection();
     System.out.println(balance.getHttp("https://coinbase.com/api/v1/accounts", "")); Example Connection class call, note " " space character needed for 
     proper execution

  */