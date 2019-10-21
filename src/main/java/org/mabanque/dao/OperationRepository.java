package org.mabanque.dao;

import org.mabanque.metier.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OperationRepository extends JpaRepository<Operation, Long>{
	
	@Query("select o from Operation o where o.compte.codeCompte = :x or o.compte2.codeCompte = :y order by o.dateOperation desc")
	public Page<Operation> ConsulterOperation(@Param("x")String codeCompte,@Param("y") String codeCompte2, Pageable pageable);
	

}
