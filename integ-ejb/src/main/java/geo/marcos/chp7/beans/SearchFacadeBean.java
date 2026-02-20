package geo.marcos.chp7.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import geo.marcos.chp7.entities.Wine;

@Stateless(name="SearchFacade", mappedName="ServiceIntegration-ejb-Search-Facade")
public class SearchFacadeBean implements ISearchFacadeLocal {
	@PersistenceContext(unitName="Chapter07-WineAppUnit-JTA")
	private EntityManager em;
	
	public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
		Query query = em.createQuery(jpqlStmt);
		if (firstResult > 0) {
			query = query.setFirstResult(firstResult);
		}
		if (maxResults > 0) {
			query = query.setMaxResults(maxResults);
		}
		return query.getResultList();
	}	
	
	public <T> T persistEnity(T entity) {
		em.persist(entity);
		return entity;
	}
	
	public <T> T mergeEntity(T entity) {
		return em.merge(entity);
	}
	
	public void removeWine(Wine wine) {
		wine = em.find(Wine.class, wine.getId());
		em.remove(wine);
	}
	
	/**
	 * <code>select object(o) from Wine o</code>
	 */
	public List<Wine> getWineFindAll(){
		return em.createNamedQuery("Wine.findAll", Wine.class).getResultList();
	}
	
	/**
	 * <code>select object(wine) from Wine wine where wine.year = :year</code>
	 */
	public List<Wine> getWineFindByYear(Integer year){
		return em.createNamedQuery("Wine.findByYear", Wine.class)
				.setParameter("year", year).getResultList();
	}
	
	/**
	 * <code>select object(wine) from Wine wine where wine.country = :country</code>
	 */
	public List<Wine> getWineFindByCountry(String contry){
		return em.createNamedQuery("Wime.findByCountry", Wine.class)
				.setParameter("country", contry).getResultList();
	}
	
	/**
	 * <code>select object(wine) from Wine wine where wine.varietal = :varietal</code>
	 */
	public List<Wine> getWineFindByVarietal(String varietal){
		return em.createNamedQuery("Wine.findByVarietal", Wine.class)
				.setParameter("varietal", varietal).getResultList();
	}

  @Override
  public List<Wine> getWineFindByYear(int i) {
    // TODO Auto-generated method stub
    return null;
  } 
}
