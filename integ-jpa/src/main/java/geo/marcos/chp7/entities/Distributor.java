package geo.marcos.chp7.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name="Distributor.findAll", query="select o from Distributor o")
})
@Table(name="distributor")
public class Distributor extends Customer {
  private static final long serialVersionUID = 1L;
  @Column(name="COMPANY_NAME", length=4000)
  private String companyName;
  private Integer discount;
  @Column(name="MEMBER_STATUS", length=30)
  private String memberStatus;
  
  public Distributor() {}
  
  public Distributor(String firstName, String lastName, String phone, String email,
      Address defaultShippingAddress, Address defaultBillingAddress, String companyName,
      Integer discount, String memberStatus) {
    super(firstName, lastName, phone, email, defaultShippingAddress, defaultBillingAddress);
    setCompanyName(companyName);
    setDiscount(discount);
    setMemberStatus(memberStatus);
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public Integer getDiscount() {
    return discount;
  }

  public void setDiscount(Integer discount) {
    this.discount = discount;
  }

  public String getMemberStatus() {
    return memberStatus;
  }

  public void setMemberStatus(String memberStatus) {
    this.memberStatus = memberStatus;
  }
}
