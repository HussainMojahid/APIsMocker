package com.APIsMocker.mockserver.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.APIsMocker.mockserver.entity.HttpRoundTrip;

@Repository
public interface HttpRoundTripRepo extends JpaRepository<HttpRoundTrip,Integer> {


    HttpRoundTrip findByRequest(String req);

    List<HttpRoundTrip> findByPath(String path);

    HttpRoundTrip findByPathAndRequest(String path,String req);



    
}
