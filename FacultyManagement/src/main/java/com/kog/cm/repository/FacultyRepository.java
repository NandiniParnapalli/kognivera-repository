package com.kog.cm.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kog.cm.entity.Faculty;
@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer>{

	@Query("select f from Faculty f where f.facultyId=:id")
	Faculty findByOne(@Param("id") Long id);
	
	
	
	
}
