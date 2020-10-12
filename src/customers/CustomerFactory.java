package customers;

public class CustomerFactory
{
    public Customer createCustomer(String type)
    {
        if(type.equals("casual"))
        {
            return new CasualCustomer();
        }
        else if(type.equals("business"))
        {
            return new BusinessCustomer();
        }
        else if(type.equals("catering"))
        {
            return new CasualCustomer();
        }
        else
        {
            System.out.println(type+" is not a known customer type");
            return null;
        }
    }
}
