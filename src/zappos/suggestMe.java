/**
 * Author: Rohan D. Shah, MS in CS student at utah state university
 * 
 * Description: 
 * This is the brain of the application. it is used to compute the various combinations of items that are within the
 * users' budget. it mainly is practical application of a mixture of knapsack and linear sub sequence problems.
 * 
 * Input:
 * It takes the array of product that has been retrieved based on the search term using the zappos search API, 
 * and the number of items that the user want to buy, through the GUI, as well as the budget he has for the purchase.
 * 
 * Output:
 * Based on these inputs, the combination of items are created with is within the budget. I wanted to keep the 
 * suggestions based on search terms i.e. within a bounded domain since there may be so many items that may fall into
 * the criteria for combinations, that the user may be exhausted to view all of them. An alternative to this problem 
 * is limiting the number of combinations which is in the future scope of this project.
 */

package zappos;

import java.util.Scanner;

public class suggestMe 
{

    /**
     * @param args the command line arguments
     */
    // used to compute the indexes of other arrays
    public static int [] indexes = new int[10];
    
    // stores the item taken
    public static boolean [] itemTaken = new boolean[10];
    
    // to move onto next index and find new combinations
    public static void next(int max, int length)
    {
        int pos = length -1;
        while(pos >= 0)
        {
            if(indexes[pos] == (max - length + 1+ pos))
                pos--;
            
            else
                break;
        }
        indexes[pos]++;
        
        for(int a = pos+1 ; a<length; a++)
            indexes[a] = indexes[a-1]+1;
    }

    /**
     *
     * @param budget
     * @param numberItems
     */
    // takes an array of items, the budget and the number of items to buy
    public void suggestMeItems(ZapposItem[] items, String budg, String numberItems) 
    {
        double price[] = new double[items.length];//{1,2,3,4,5};//double[5];
        // for converting string to int
        String priceString = "";
        String temp = "";
        
        // converting price from string to int and storing in the price array
        // the index of the price array is the ith item.
        for(int i=0 ; i<items.length ; i++)
        {          
           temp = items[i].getPrice();
           priceString=temp.substring(1,temp.length()-1);           
           price[i] = Double.parseDouble(priceString);
        }
        //String items[] = {"item1","item2","item3","item4","item5"};//String[5];
        double sum = 0;
        Scanner sc = new Scanner(System.in);
        // converting from string to double and integer respectively for calculations
        double budget = Double.parseDouble(budg);
        int noOfItems = Integer.parseInt(numberItems);
        
        int max = price.length - 1;
        
        // initializing the indexes array with the indexes
        for(int i=0 ; i<noOfItems ;i++)
            indexes[i] = i;
        
        for(int a = 0; ; a++)
        {
            // computing the combinations
            for(int b=0 ; b<noOfItems ; b++)
            {
                sum+=price[indexes[b]];
                itemTaken[indexes[b]] = true;
            }
            if(sum<=budget)
            {
                for(int z=0 ; z<noOfItems ;z++)
                {
                    // displayng the items that can be bought together
                    System.out.println(items[indexes[z]].toString());
                }
                System.out.println("Total cost = $"+sum);
                System.out.println("");
                System.out.println("==============================");
            }
            else
            {
                System.out.println("No items found, change your budget or number of items");
            }
            sum=0;
            if(indexes[0]==(max-noOfItems+1))
                break;
            
            else
                next(max,noOfItems);
        }
    }
    
}
