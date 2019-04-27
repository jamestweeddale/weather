package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.entity.ReadingKeyEntity;
import server.entity.ReadingValueEntity;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface ReadingValueRepository extends JpaRepository<ReadingValueEntity, Long> {

    @Query("select distinct e.readingKey from ReadingValueEntity e where e.station.id=:stationId")
    List<ReadingKeyEntity> findReadingKeysForStation(@Param("stationId") Long stationId);

    ReadingValueEntity findFirstByStationIdAndReadingKeyIdAndTimeAfterOrderByIdDesc(@Param("stationId") Long stationId, @Param("readingKeyId") Long readingKeyId, @Param("time") ZonedDateTime readingTime);
}

