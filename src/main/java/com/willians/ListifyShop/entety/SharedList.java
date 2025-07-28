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
    private String id;

    @Enumerated(value = EnumType.STRING)
    private PermissionLevel permission;

    @Column(nullable = false)
    private String shareToken;

    @Column(nullable = false)
    private Boolean activeSharing;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "owner", nullable = false)
    private User owner;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "shoppingList", nullable = false)
    private ShoppingList shoppingList;
}
