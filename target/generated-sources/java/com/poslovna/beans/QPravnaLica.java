package com.poslovna.beans;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPravnaLica is a Querydsl query type for PravnaLica
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPravnaLica extends EntityPathBase<PravnaLica> {

    private static final long serialVersionUID = -717661857L;

    public static final QPravnaLica pravnaLica = new QPravnaLica("pravnaLica");

    public final StringPath adresa = createString("adresa");

    public final StringPath email = createString("email");

    public final StringPath fax = createString("fax");

    public final BooleanPath firma = createBoolean("firma");

    public final StringPath naziv = createString("naziv");

    public final StringPath PIB = createString("PIB");

    public final StringPath telefon = createString("telefon");

    public final StringPath web = createString("web");

    public QPravnaLica(String variable) {
        super(PravnaLica.class, forVariable(variable));
    }

    public QPravnaLica(Path<? extends PravnaLica> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPravnaLica(PathMetadata metadata) {
        super(PravnaLica.class, metadata);
    }

}

