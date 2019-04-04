package server.entity;

import reading.Reading;
import reading.ReadingUnits;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name="reading", indexes = {@Index(name = "reading_type_idx", columnList = "reading_key_id"),@Index(name = "reading_station_idx", columnList = "station_id")})
public class ReadingValueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double value;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReadingUnits units;

    @Column(nullable = false)
    private ZonedDateTime time;

    @ManyToOne(optional = false)
    private ReadingKeyEntity readingKey;

    @ManyToOne(optional = false)
    private StationEntity station;


    public ReadingValueEntity() {
        //for hibernate
    }

    public ReadingValueEntity(ReadingKeyEntity readingKey, StationEntity station, Double value, ReadingUnits units, ZonedDateTime time) {
        this.readingKey = readingKey;
        this.station = station;
        this.value = value;
        this.units = units;
        this.time = time;
    }

    public ReadingValueEntity(ReadingKeyEntity readingKey, StationEntity station, Reading reading) {
        this.readingKey = readingKey;
        this.station = station;
        this.value = reading.getValue();
        this.time = reading.getTime();
        this.units = reading.getUnits();
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

    public String getUnits() {
        return units.getAbbreviation();
    }

    public ReadingKeyEntity getReadingKey() {
        return readingKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadingValueEntity that = (ReadingValueEntity) o;
        return getValue().equals(that.getValue()) &&
                getUnits() == that.getUnits() &&
                getTime().equals(that.getTime()) &&
                getReadingKey().equals(that.getReadingKey()) &&
                getStation().equals(that.getStation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getUnits(), getTime(), getReadingKey(), getStation());
    }
}
