package com.poslovna.beans;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QVrstaPlacanja is a Querydsl query type for VrstaPlacanja
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QVrstaPlacanja extends EntityPathBase<VrstaPlacanja> {

    private static final long serialVersionUID = 1695529996L;

    public static final QVrstaPlacanja vrstaPlacanja = new QVrstaPlacanja("vrstaPlacanja");

    public final StringPath nazivVrstePlacanja = createString("nazivVrstePlacanja");

    public final NumberPath<Long> oznakaVrste = createNumber("oznakaVrste", Long.class);

    public QVrstaPlacanja(String variable) {
        super(VrstaPlacanja.class, forVariable(variable));
    }

    public QVrstaPlacanja(Path<? extends VrstaPlacanja> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVrstaPlacanja(PathMetadata metadata) {
        super(VrstaPlacanja.class, metadata);
    }

}

