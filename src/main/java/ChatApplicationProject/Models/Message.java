package ChatApplicationProject.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String content;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Chat chat;

    @CreationTimestamp
    private LocalDateTime creationDate;
}
