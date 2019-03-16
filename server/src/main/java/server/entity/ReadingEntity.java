package server.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity(name="reading")
@Table(indexes = {@Index(name = "station_reading_idx", columnList = "station_id"), @Index(name = "reading_type_idx", columnList = "reading_type_id")})
public class ReadingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private StationEntity station;

    @ManyToOne(optional = false)
    private ReadingTypeEntity readingType;

    @Column(nullable = false)
    private Long value;

    @Column(nullable = false)
    private ZonedDateTime time;

    public ReadingEntity() {
        //for hibernate
    }

    public ReadingEntity(Long id, Long value, ZonedDateTime time) {
        this.id = id;
        this.value = value;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public Long getValue() {
        return value;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadingEntity that = (ReadingEntity) o;
        return getId().equals(that.getId()) &&
                getValue().equals(that.getValue()) &&
                getTime().equals(that.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getValue(), getTime());
    }
}
