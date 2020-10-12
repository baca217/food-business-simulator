package customers;

public class CustomerFactory
{
    public Customer createCustomer(String type)
    {
        if(type.equals("casual"))
        {
            return new Casual();
        }
        else if(type.equals("business"))
        {
            return new Business();
        }
        else if(type.equals("catering"))
        {
            return new Casual();
        }
        else
        {
            System.out.println(type+" is now a know customer type");
            return null;
        }
    }
}
