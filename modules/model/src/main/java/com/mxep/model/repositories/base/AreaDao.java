package com.mxep.model.repositories.base;

import com.mxep.model.BaseDao;
import com.mxep.model.base.Area;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Dao - 地区
 */
public interface AreaDao extends BaseDao<Area, Integer> {

	@Query("select a from Area a")
	List<Area> findAreaList();

	/**
	 * 查询所有省份数据
	 *
	 * @return 省份数据
	 */
    @Query(value = "from Area where level = 1 ")
	List<Area> findProvinceList();

	/**
	 * 查询所有已开放省份
	 * @return
	 */
	@Query(value = "from Area where level = 1 and status=1")
	List<Area> findProvinceOpenList();


    @Query(value = "from Area where level = 2 and pid = ?1")
    public List<Area> findCityListByProvinceId(Integer provinceId);
    
    
    @Query(value = "from Area where level = 2 and pid = ?1 and status = 1")
    public List<Area> findCityOpenListByProvinceId(Integer provinceId);


    @Query(value = "from Area where level = 3 and pid = ?1 ")
    public List<Area> findDistrictListByCityId(Integer cityId);
    
    
    @Query(value = "from Area where level = 3 and pid = ?1 and status = 1 order by id desc " )
    public List<Area> findDistrictOpenListByCityId(Integer cityId);
    
	@Query("select a from Area a where a.pid =?1 and a.status=1 order by a.pingyin asc")
	List <Area> findOpenAreaByPid(Integer pid);
	
	@Query(value = "select * from sys_area where pid in (select a.id from sys_area a where pid = 5588) and status =1",nativeQuery = true)
	List <Area> findOpenChinaArea();
	
	
	@Query("select a from Area a where a.pid =?1 and a.status=1 and a.hot>0 order by a.pingyin asc")
	List <Area> findHotArea(Integer pid);

	List<Area> findByPid(Integer pid);
	
	
	/**
	 * 查找省份
	 * @param countryId
	 * @return
	 */
	@Query(value = "select * from sys_area a where a.pid = ?1 and a.level = 1 and a.status=1 ",nativeQuery = true)
	List <Area> findProvinces(Integer countryId);
	

	/**
	 * 查找城市
	 * @param provinceId
	 * @return
	 */
	@Query(value = "select * from sys_area a where a.pid = ?1 and a.level = 2 and a.status=1 ",nativeQuery = true)
	List <Area> findCitys(Integer provinceId);
	
	/**
	 * 查找地区
	 * @param cityId
	 * @return
	 */
	@Query(value = "select * from sys_area a where a.pid = ?1 and a.level = 3 and a.status=1 ",nativeQuery = true)
	List <Area> findCountys(Integer cityId);
	

	
	


}
