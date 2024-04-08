package jogayjoga.sport;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "sport")
@EqualsAndHashCode(of = "id")
@Builder @Getter @Setter @Accessors(chain = true, fluent = true)
@NoArgsConstructor @AllArgsConstructor
public class SportModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_sport")
    private String id;

    @Column(name = "tx_name")
    private String name;

    public SportModel(Sport o) {
        this.id = o.id();
        this.name = o.name();
    }
    
    public Sport to() {
        return new Sport()
            .id(id)
            .name(name);
    }
    
}