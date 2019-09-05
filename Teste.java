package br.com.marley.statemachine;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;

@Builder
@Data
@Table("Teste")
public class Teste {

    @PrimaryKey("id")
    private UUID id;

    @Column("date")
    private Date date;

    @Column("body")
    private String text;

    @Column("fase")
    private FaseEvento fase;
}
