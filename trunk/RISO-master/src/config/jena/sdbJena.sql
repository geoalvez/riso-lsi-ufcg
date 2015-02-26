DROP TABLE triples;
CREATE TABLE triples (
  s   bigint,
  p   bigint,
  o   bigint,
  PRIMARY KEY(s,p,o)
);

CREATE INDEX I_triples_PO ON triples (p,o);
CREATE INDEX I_triples_OS ON triples (o,s);

DROP TABLE quads;
CREATE TABLE quads (
  g   bigint,
  s   bigint,
  p   bigint,
  o   bigint,
  PRIMARY KEY(g,s,p,o)
);

CREATE INDEX I_quads_GPO ON quads (g,p,o);
CREATE INDEX I_quads_GOS ON quads (g,o,s);
CREATE INDEX I_quads_SPO ON quads (s,p,o);
CREATE INDEX I_quads_OS ON quads (o,s);
CREATE INDEX I_quads_PO ON quads (p,o);


DROP TABLE nodes;

CREATE TABLE nodes (
  hash bigint, 
  lex varchar(255),  
  lang varchar(255), 
  datatype varchar(255),  
  type varchar(255), 
  PRIMARY KEY(hash) 
);
CREATE INDEX I_nodes_hash ON nodes (hash);

DROP TABLE prefixes;

CREATE TABLE prefixes (
  prefix text,
  uri varchar(255),    
  PRIMARY KEY(prefix) 
);

CREATE INDEX I_prefixes_prefixes ON prefixes (uri);


select * from prefixes;

select * from triples;

select * from nodes;

update prefixes set uri = 'rel' where uri='rel:';