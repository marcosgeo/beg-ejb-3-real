package geo.marcos.chp7.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@NamedQueries({
  @NamedQuery(name="Wine.findAll", query="select object o from Wine o"),
  @NamedQuery(name="Wine.findByYear", query="select object(wine) from Wine wine where wine.year=:year"),
  @NamedQuery(name="Wine.findByCountry", query="select object(wine) from Wine wine where wine.country=:country"),
  @NamedQuery(name="Wine.findByVarietal", query="select object(wine) from Wine wine where wine.varietal=:varietal")
})
@Table(name="wine")
public class Wine implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Column(length=4000)
  private String country;
  @Column(length=4000)
  private String description;
  @Id
  @Column(nullable=false)
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  @Column(nullable=false, length=4000)
  private String name;
  private int rating;
  @Column(length=4000)
  private String region;
  @Column(name="RETAIL_PRICE")
  private Float retailPrice;
  @Column(length=4000)
  private String varietal;
  private Integer version;
  @Column(name="YEAR")
  private int year;
  @ManyToMany
  @JoinTable(name="wine_supplier",
    joinColumns={@JoinColumn(name="WINE_ID", referencedColumnName="ID")},
    inverseJoinColumns={@JoinColumn(name="SUPPLIER_ID", referencedColumnName="ID")}
  )
  private List<Supplier> supplierList;

  public Wine() {}
  
  public Wine(String country, String description, String name, int rating, String region,
      Float retailPrice, String varietal, int year) {
    setCountry(country);
    setDescription(description);
    setName(name);
    setRating(rating);
    setRegion(region);
    setRetailPrice(retailPrice);
    setVarietal(varietal);
    setYear(year); 
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public Float getRetailPrice() {
    return retailPrice;
  }

  public void setRetailPrice(Float retailPrice) {
    this.retailPrice = retailPrice;
  }

  public String getVarietal() {
    return varietal;
  }

  public void setVarietal(String varietal) {
    this.varietal = varietal;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public List<Supplier> getSupplierList() {
    if (supplierList == null) {
      supplierList = new ArrayList<Supplier>();
    }
    return supplierList;
  }

  public void setSupplierList(List<Supplier> supplierList) {
    this.supplierList = supplierList;
  }
  
  

}
