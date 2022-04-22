package com.pdist.schedule.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String subject;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_teacher")
    private Usuario teacher;

    @OneToMany(mappedBy = "id")
    @JsonIgnore
    private List<Usuario> students;

    @NotNull
    @Column(name = "dateTimeBegin")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Timestamp dateTimeBegin;

    @NotNull
    @Column(name = "dateTimeEnd")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Timestamp dateTimeEnd;

    private String description;
}
