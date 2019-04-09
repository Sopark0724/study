package com.sopark.cache;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.CacheConfig;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Cache(region = "GlobalConfig",usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User {

    @Id @GeneratedValue @NonNull
    private Long id;

    @NonNull
    private String name;

    @Cache(region = "GlobalConfig", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    List<Member> members = new ArrayList<>();

    public void addMember(Member member){
        this.members.add(member);
    }
}
