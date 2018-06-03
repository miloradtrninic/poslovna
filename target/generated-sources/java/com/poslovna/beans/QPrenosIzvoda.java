package com.poslovna.beans;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPrenosIzvoda is a Querydsl query type for PrenosIzvoda
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPrenosIzvoda extends EntityPathBase<PrenosIzvoda> {

    private static final long serialVersionUID = -1221194928L;

    public static final QPrenosIzvoda prenosIzvoda = new QPrenosIzvoda("prenosIzvoda");

    public final NumberPath<Integer> brojPreseka = createNumber("brojPreseka", Integer.class);

    public final NumberPath<Integer> brojPromena = createNumber("brojPromena", Integer.class);

    public final DateTimePath<java.util.Date> datumNaloga = createDateTime("datumNaloga", java.util.Date.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final NumberPath<Double> ukupnoUKorist = createNumber("ukupnoUKorist", Double.class);

    public QPrenosIzvoda(String variable) {
        super(PrenosIzvoda.class, forVariable(variable));
    }

    public QPrenosIzvoda(Path<? extends PrenosIzvoda> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPrenosIzvoda(PathMetadata metadata) {
        super(PrenosIzvoda.class, metadata);
    }

}

