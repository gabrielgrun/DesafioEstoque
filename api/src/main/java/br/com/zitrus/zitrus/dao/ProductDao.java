package br.com.zitrus.zitrus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import br.com.zitrus.zitrus.model.ProductProfitDto;
import br.com.zitrus.zitrus.model.ProductSellQuantityDto;

public class ProductDao {

	public Collection<ProductSellQuantityDto> findProductByType (Connection conn, Integer type)
			throws SQLException
	{
		Collection<ProductSellQuantityDto> rows = new ArrayList<>();
		String query = "SELECT "
				+ "	PRODUCT.ID, PRODUCT.DESCRIPTION,  PRODUCT.QUANTITY, SUM(STOCKMOVEMENT.QUANTITY) AS SELL_QUANTITY "
				+ "	FROM PRODUCT "
				+ "	LEFT JOIN STOCKMOVEMENT ON (STOCKMOVEMENT.IDPRODUCT = PRODUCT.ID) " 
				+ "	WHERE STOCKMOVEMENT.TYPE = 1 "
				+ "	AND PRODUCT.TYPE = ? "
				+ "	GROUP BY PRODUCT.ID, PRODUCT.DESCRIPTION,  PRODUCT.QUANTITY";
		try(PreparedStatement ps = conn.prepareStatement(query))
		{
			ps.setInt(1, type);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				ProductSellQuantityDto product = new ProductSellQuantityDto();
				product.setId(rs.getInt("ID"));
				product.setDescription(rs.getString("DESCRIPTION"));
				product.setSellQuantity(rs.getInt("QUANTITY"));
				product.setQuantity(rs.getInt("SELL_QUANTITY"));
				rows.add(product);
			}
			rs.close();
		} catch (SQLException e)
		{
			throw new SQLException(e);
		}
		return rows;
	}
	
	public Collection<ProductProfitDto> findProfitByProduct (Connection conn, Integer id)
			throws SQLException
	{
		Collection<ProductProfitDto> rows = new ArrayList<>();
		String query = "SELECT "
				+ " PRODUCT.ID, PRODUCT.DESCRIPTION, SUM(STOCKMOVEMENT.QUANTITY) AS SELL_QUANTITY, SUM(STOCKMOVEMENT.VALUE - PRODUCT.PRICE) AS PROFIT"
				+ " FROM PRODUCT "
				+ " LEFT JOIN STOCKMOVEMENT ON (STOCKMOVEMENT.IDPRODUCT = PRODUCT.ID)"
				+ " WHERE STOCKMOVEMENT.TYPE = 1"
				+ " AND PRODUCT.ID = ?"
				+ " GROUP BY PRODUCT.ID, PRODUCT.DESCRIPTION";
		try(PreparedStatement ps = conn.prepareStatement(query))
		{
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				ProductProfitDto product = new ProductProfitDto();
				product.setId(rs.getInt("ID"));
				product.setDescription(rs.getString("DESCRIPTION"));
				product.setSellQuantity(rs.getInt("SELL_QUANTITY"));
				product.setProfit(rs.getDouble("PROFIT"));
				rows.add(product);
			}
			rs.close();
		} catch (SQLException e)
		{
			throw new SQLException(e);
		}
		return rows;
	} 
}
