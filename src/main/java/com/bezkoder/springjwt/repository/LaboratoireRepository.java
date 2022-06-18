package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Laboratoire;

@Repository
public interface LaboratoireRepository extends JpaRepository<Laboratoire, Long> {
	//public Laboratoire findByName(String name);
	//public Laboratoire findById(Laboratoire lab);
	public boolean existsByName(String name);
	
	public  List<Laboratoire>  findByIsResponsable(Boolean isResponsable);
	
	//@Query("select l.name, l.adress,l.phoneNumber,u.firstName , u.lastName from laboratoire l join l.listeUsers u join u.roles r where r.name=ROLE_RESPONSABLE")
	@Query(value="SELECT l.* , CONCAT(respo.first_name ,\" \", respo.last_name) as responsable  FROM laboratoire l,users respo,users empl,roles r1,roles r2,user_roles ur,user_roles ur2 WHERE (l.id=respo.laboratoire_id and respo.id=ur.user_id and r1.id=ur.role_id and r1.name='ROLE_RESPONSABLE') or (l.id!=empl.laboratoire_id and empl.id=ur2.user_id and r2.id=ur2.role_id and r2.name='ROLE_RESPONSABLE') GROUP BY l.name",nativeQuery=true)
	List<Object[]> listertous2();
	/*@Query(value="select l.name ,l.is_responsable from laboratoire l  where l.is_responsable = 0 ",nativeQuery=true)
	List<Object[]> listertou();*/
	
	
}
