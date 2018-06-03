package com.poslovna.beans;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDnevnoStanje is a Querydsl query type for DnevnoStanje
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDnevnoStanje extends EntityPathBase<DnevnoStanje> {

    private static final long serialVersionUID = 2125150303L;

    public static final QDnevnoStanje dnevnoStanje = new QDnevnoStanje("dnevnoStanje");

    public final DateTimePath<java.util.Date> datumPromene = createDateTime("datumPromene", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SetPath<AnalitikaIzvoda, QAnalitikaIzvoda> izvodi = this.<AnalitikaIzvoda, QAnalitikaIzvoda>createSet("izvodi", AnalitikaIzvoda.class, QAnalitikaIzvoda.class, PathInits.DIRECT2);

    public final NumberPath<Double> novoStanje = createNumber("novoStanje", Double.class);

    public final NumberPath<Double> prethodnoStanje = createNumber("prethodnoStanje", Double.class);

    public final NumberPath<Double> promeneNaTeret = createNumber("promeneNaTeret", Double.class);

    public final NumberPath<Double> promeneUKorist = createNumber("promeneUKorist", Double.class);

    public QDnevnoStanje(String variable) {
        super(DnevnoStanje.class, forVariable(variable));
    }

    public QDnevnoStanje(Path<? extends DnevnoStanje> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDnevnoStanje(PathMetadata metadata) {
        super(DnevnoStanje.class, metadata);
    }

}

