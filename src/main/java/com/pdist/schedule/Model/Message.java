package com.pdist.schedule.Model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id_origin")
    private Usuario userIdOrigin;

    @NotNull
    @Column(name = "dateTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Timestamp dateTime;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id_destination")
    private Usuario userIdDestination;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private boolean isRead;

    public Message(Usuario userIdOrigin, Usuario userIdDestination, String title, String description) {
        this.userIdOrigin = userIdOrigin;
        this.dateTime = new Timestamp(System.currentTimeMillis());
        this.userIdDestination = userIdDestination;
        this.title = title;
        this.description = description;
        this.isRead = false;
    }
}
