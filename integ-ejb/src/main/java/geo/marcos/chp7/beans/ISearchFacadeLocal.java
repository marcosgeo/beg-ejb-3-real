package geo.marcos.chp7.beans;

import java.util.List;

import geo.marcos.chp7.entities.Wine;


public interface ISearchFacadeLocal {

  List<Wine> getWineFindByYear(int i);

}
