import java.util.*;
class Product {
    float costPrice, sellingPrice;
    int quantity;
    Product(float cp, float sp){
        this.costPrice = cp;
        this.sellingPrice = sp;
        this.quantity = 0;
    }
    public float getCostPrice(){
        return this.costPrice;
    } 
    public float getSellingPrice(){
        return this.sellingPrice;
    } 
    public void changeSellingPrice(float sp){
        this.sellingPrice = sp;
    } 
    public int getQuantity(){
        return this.quantity;
    } 
    public float changeQuantity(int q){
    /** function takes as the input the quantity of items
        q is positive indicates that the elements are added to the inventory else if it is negative, it shows that the elements are sold        
    */
        this.quantity += q;
        if (q > 0)
            return q*this.costPrice; // elements are bought
        return q*this.sellingPrice;  // elements are sold
    }
    
}
public class Inventory{
    
    public static float printReport(Map<String, Product> map, float oldProfit, float newProfit)
    {
        TreeMap<String, Product> sorted = new TreeMap<>(map);
        float sum = 0;
        System.out.println("\t\t\tINVENTORY REPORT");
        System.out.println("Item Name \t Bought At \t Sold At \t AvailableQty \t Value");
        System.out.println("--------- \t --------- \t ------- \t ------------ \t -----");
             
        for (Map.Entry<String, Product> entry : sorted.entrySet()) {
            
            Product p = entry.getValue();
            float tot = p.getCostPrice()*p.getQuantity();
            sum += tot;
            System.out.printf("%s\t\t%.2f\t\t%.2f\t\t%d\t\t%.2f\n",entry.getKey() ,p.getCostPrice(), p.getSellingPrice(),p.getQuantity(),tot);

        }
        System.out.println("----------------------------------------------------------------------------------");
        
        System.out.printf("Total value\t\t\t\t\t\t\t%.2f\n", sum);
        float diffFromPrevious = sum - oldProfit - newProfit;
        System.out.printf("Profit since previous report\t\t\t\t\t%.2f\n", diffFromPrevious );
        
        return diffFromPrevious;
    }

    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        
        HashMap inv = new HashMap();
        String a;
        Product p;
        
        float profit = 0;
        float earning = 0;
        
        while(true)
        {
            a = sc.nextLine();
            if (a.equals("#"))
            break;
            else{
                String cmd[] = a.split(" ");
                
                switch(cmd[0]){
                    case "create":
                        inv.put(cmd[1],new Product(Float.parseFloat(cmd[2]), Float.parseFloat(cmd[3]))); // add to dictionary with key as the itemName
                        break;
                    case "delete":
                        inv.remove(cmd[1]); // remove the key from the dictionary
                        break;
                    case "updateBuy":
                        p =(Product)inv.get(cmd[1]);
                        earning += p.changeQuantity(Integer.parseInt(cmd[2])); // add products to the inventory
                        break;
                    case "updateSell":
                        p =(Product)inv.get(cmd[1]);
                        earning += p.changeQuantity(-Integer.parseInt(cmd[2])); // sell products from the inventory
                        break;
                    case "updateSellPrice":
                        p =(Product)inv.get(cmd[1]);
                        p.changeSellingPrice(Integer.parseInt(cmd[2])); // update the selling price
                        break;
                    case "report":
                        profit = printReport(inv, profit, earning);
                        break;
                    default:
                        System.out.println("Wrong command");
                        break;

                }
            }
        }

     }
     
}
