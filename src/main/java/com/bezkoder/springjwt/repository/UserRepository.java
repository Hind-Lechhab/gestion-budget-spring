package com.bezkoder.springjwt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
  
  //select u.* from users u,roles r ,user_roles ur WHERE u.id=ur.user_id and ur.role_id=r.id and r.name='ROLE_RESPONSABLE'
  @Query(value="select u.*,l.name from users u,roles r ,user_roles ur,laboratoire l WHERE u.id=ur.user_id and ur.role_id=r.id and r.name='ROLE_RESPONSABLE' and l.id=u.laboratoire_id",nativeQuery=true)
  List<Object[]> listRespo();
  
  
  
  @Query(value="select u.*,l.name from users u,roles r ,user_roles ur,laboratoire l WHERE u.id=ur.user_id and ur.role_id=r.id and r.name='ROLE_USER' and l.id=u.laboratoire_id and l.id=?1",nativeQuery=true)
  List<Object[]> listEmployee(Long lab);
  
}
