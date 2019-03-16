package server.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity(name="reading_type")
public class ReadingTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String units;

    public ReadingTypeEntity(Long id, String name, String units) {
        this.id = id;
        this.name = name;
        this.units = units;
    }

    public ReadingTypeEntity() {
        //for hibernate
    }


    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadingTypeEntity that = (ReadingTypeEntity) o;
        return name.equals(that.name) &&
                Objects.equals(units, that.units);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, units);
    }
}
