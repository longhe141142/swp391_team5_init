package com.fu.swp391.repository;

import com.fu.swp391.entities.Company;
import lombok.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    @Query(value = "SELECT * FROM company", nativeQuery = true)
    List<Company> findAllCompany();
//    List<Company> findAllCompany(Pageable pageable);
    Optional<Company> findById(Long id);
    @Transactional
    @Modifying
    @Query(value = "update Company c set c.company_name = :name," +
            " c.address = :address,c.phone = :phone, c.email = :email," +
            " c.personnel_size = :personnel_size, c.description = :description," +
            " c.founding_at = :founding_at, c.company_intro = :company_intro where c.id = :id", nativeQuery=true)
    void update(@Param(value = "id") long id, @Param(value = "name") String name,
                     @Param(value = "address") String address, @Param(value = "phone") String phone,
                     @Param(value = "email") String email, @Param(value = "personnel_size") Integer personnel_size,
                     @Param(value = "description") String description, @Param(value = "founding_at") Date founding_at,
                     @Param(value = "company_intro") String company_intro);



    @Query("SELECT c FROM Company c WHERE c.name LIKE %?1%")
    ArrayList<Company> search(String keyword);


    Company findCompanyByEmail(String email);
}
