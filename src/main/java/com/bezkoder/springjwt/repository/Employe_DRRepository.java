package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Employe_DR;
@Repository
public interface Employe_DRRepository extends JpaRepository<Employe_DR, Integer>{
	
	@Query(value="SELECT ebr.id,u.first_name,u.last_name,ebr.part_empdr,(b.somme-b.somme_dr) as budget_recherche FROM budget b,employe_dr ebr,users u,user_roles ur where b.annee=:annee and b.laboratoire_id =:laboratoire_id and b.id=ebr.budget_dr_id and ebr.employe_id=u.id and ur.user_id=u.id and ur.role_id=1",nativeQuery=true)
	List<Object[]> listerAffectationBudget(@Param("annee") String annee,@Param("laboratoire_id")Long idLab);

}
