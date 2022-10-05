package com.carrot.mattro.Repository;
import com.carrot.mattro.domain.entity.Crawling;
import com.carrot.mattro.domain.entity.Output;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CrawlingRepository extends MongoRepository<Crawling, String> {

    Optional<Crawling> findByName(String storeName);
    List<Crawling> findByFoodCategoryIn(List<String> koreanFoodList);


    @Query("{'keywordReviewList.15' : {$exists : ?0}, 'keywordReviewList.7':{$exists:?1}, 'keywordReviewList.9':{$exists:?2},'foodCategory':{$in:?3}}")
    List<Output> findByUserPrefer(Boolean index0, Boolean index1, Boolean index2, List<String> selectedFoodCategoryList);

//    16599307,20024639,12612310,16587147,12617426
    List<Output> findByStoreIdxIn(String[] storeIndexList);

}