package com.willians.ListifyShop.entety;

import com.willians.ListifyShop.enums.PermissionLevel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shared_list_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SharedList {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(value = EnumType.STRING)
    private PermissionLevel permission;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "shoppingList", nullable = false)
    private ShoppingList shoppingList;
}
