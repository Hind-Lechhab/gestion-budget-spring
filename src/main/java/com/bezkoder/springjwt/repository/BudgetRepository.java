package com.bezkoder.springjwt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Budget;
import com.bezkoder.springjwt.models.Laboratoire;
import com.bezkoder.springjwt.models.User;
@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {

	//@Query(value="SELECT * FROM budget b where b.laboratoire_id=?1 and b.annee=?2",nativeQuery=true)
	Budget findByAnneeAndLaboratoire(String annee,Laboratoire lab);
	
	@Query(value="SELECT b.annee,l.name ,b.somme,b.somme_db,b.somme_dr,b.id FROM budget b , laboratoire l where b.laboratoire_id=l.id",nativeQuery=true)
	List<Object[]> listerBudget();

	Budget findByAnnee(String annee);
	
}
