package geo.marcos.chp6.services;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

@Stateless(name="CreditCheckEndpointBean")
@WebService(serviceName="CreditService", targetNamespace="https://github.com/marcosgeo")
public class CreditCheckEndpointBean {
  
  public CreditCheckEndpointBean() {}
  
  @WebMethod(operationName="CreditCheck")
  public boolean validCC(String cc) {
    return true;
  }

  public boolean creditCheck(String ccnum) {
    // TODO Auto-generated method stub
    return true;
  }

}
