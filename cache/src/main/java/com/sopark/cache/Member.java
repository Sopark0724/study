package com.sopark.cache;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@ToString
@Cache(region = "GlobalConfig",usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class Member {

    @Id @GeneratedValue
    private Long id;

    @NonNull
    private String Type;
}
