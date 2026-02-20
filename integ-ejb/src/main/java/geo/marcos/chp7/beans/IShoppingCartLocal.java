package geo.marcos.chp7.beans;

import geo.marcos.chp7.entities.Customer;
import geo.marcos.chp7.entities.Wine;

public interface IShoppingCartLocal {

  Customer findCustomer(String toEmailAddress);

  void addWineItem(Wine wine, int i);

  String sendOrderToOPC();

}
