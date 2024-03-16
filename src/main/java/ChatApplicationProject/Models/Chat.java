package ChatApplicationProject.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String chatname;

    private String chatimage;

    private Boolean isgroup;

    @ManyToOne
    private User createdby;
    @ManyToMany
    private Set<User> users;

    @OneToMany(mappedBy = "chat")
    @JsonIgnore
    private List<Message> messages;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return id == chat.id && Objects.equals(chatname, chat.chatname) && Objects.equals(chatimage, chat.chatimage) && Objects.equals(isgroup, chat.isgroup) && Objects.equals(createdby, chat.createdby) && Objects.equals(users, chat.users) && Objects.equals(messages, chat.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatname, chatimage, isgroup, createdby, users, messages);
    }
}
