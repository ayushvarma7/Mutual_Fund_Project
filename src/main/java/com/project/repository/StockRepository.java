//package com.project.repository;
//
//import java.util.ArrayList;
//import java.util.Date;
//
//import org.springframework.data.repository.CrudRepository;
//
//import com.project.model.Stock;
//
//public interface StockRepository extends CrudRepository<Stock, Integer> {
//public Stock findByStockIdAndDateOfRecord(int stockid, Date dateOfRecord);
//
//public ArrayList<Stock> findByDateOfRecord(String dateOfRecord);
//}
//

package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.model.Stock;

public interface StockRepository extends CrudRepository<Stock, Integer> {

	@Query("SELECT s FROM Stock s WHERE TO_CHAR(s.stockId) LIKE '%1'")
	List<Stock> findStocksByCustomQuery();

	@Query("SELECT s.openingPrice FROM Stock s WHERE s.dateOfRecord = TO_DATE('2023-07-24', 'YYYY-MM-DD') AND s.stockId = :stock_id")
	Double findStocksPriceById(@Param("stock_id") int stock_id);
}
