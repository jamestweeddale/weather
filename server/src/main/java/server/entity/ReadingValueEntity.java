package server.entity;

import reading.Reading;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity(name="reading")
@Table(indexes = {@Index(name = "reading_type_idx", columnList = "reading_key_id"),@Index(name = "reading_station_idx", columnList = "station_id")})
public class ReadingValueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private ZonedDateTime time;

    @ManyToOne(optional = false)
    private ReadingKeyEntity readingKey;

    @ManyToOne(optional = false)
    private StationEntity station;


    public ReadingValueEntity() {
        //for hibernate
    }

    public ReadingValueEntity(ReadingKeyEntity readingKey, StationEntity station, Double value, ZonedDateTime time) {
        this.readingKey = readingKey;
        this.station = station;
        this.value = value;
        this.time = time;
    }

    public ReadingValueEntity(ReadingKeyEntity readingKey, StationEntity station, Reading reading) {
        this.readingKey = readingKey;
        this.station = station;
        this.value = reading.getValue();
        this.time = reading.getTime();
    }


    public Long getId() {
        return id;
    }

    public Double getValue() {
        return value;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public ReadingKeyEntity getReadingKeyEntity() {
        return readingKey;
    }

    public StationEntity getStation() {
        return station;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadingValueEntity that = (ReadingValueEntity) o;
        return getValue().equals(that.getValue()) &&
                getTime().equals(that.getTime()) &&
                getReadingKeyEntity().equals(that.getReadingKeyEntity()) &&
                getStation().equals(that.getStation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getTime(), getReadingKeyEntity(), getStation());
    }
}
