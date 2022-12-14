package com.carrot.mattro.controller;

import com.carrot.mattro.domain.entity.Output;
import com.carrot.mattro.service.MongoDBService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/subway/recommendation")
@RequiredArgsConstructor
public class SubwayApiController {

    private final MongoDBService mds;
    private final Logger logger = LoggerFactory.getLogger(SubwayApiController.class);
//    @GetMapping("/find/{subway_name}")
//    public ResponseEntity getPlaceBySubwayName(@PathVariable(name = "subway_name") String name){
//        Optional<Output> response = mds.findPlaceBySubwayName(name);
//        System.out.println(response);
//        if(response.get() != null)
//            return new ResponseEntity(response, HttpStatus.OK);
//        else
//            return new ResponseEntity("데이터가 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//    http://localhost:8080/api/subway/recommendation/18455932
    @GetMapping("/{store_index}")
    public ResponseEntity getPlaceByStoreIndex(@PathVariable(name = "store_index") String storeIndex){
        long before_time = System.currentTimeMillis();
        Output response = mds.findPlaceByStoreIndex(storeIndex);
        long after_time = System.currentTimeMillis();
        logger.info("소요 시간 : "+ (after_time - before_time) +"ms");
        if(response != null) {
            return new ResponseEntity(response, HttpStatus.OK);
        }else {
            return new ResponseEntity("데이터가 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
