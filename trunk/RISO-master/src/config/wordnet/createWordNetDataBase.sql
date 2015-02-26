CREATE TABLE IndexWord (
  index_word_id   bigint,
  lemma           varchar(255),
  pos             char
);

CREATE INDEX I_IndexWord ON IndexWord (pos, lemma);

CREATE TABLE Synset (
  synset_id      bigint,
  file_offset    bigint,
  pos             char,
  is_adj_cluster  boolean,
  gloss           text
);

CREATE INDEX I_Synset ON Synset (pos, file_offset);

CREATE TABLE SynsetWord (
  synset_word_id bigint,
  synset_id      bigint,
  word            varchar(255),
  word_index     bigint
);

CREATE INDEX I_SynsetWord On SynsetWord (synset_id);

CREATE TABLE SynsetPointer (
  synset_pointer_id bigint,
  synset_id        bigint,
  pointer_type      varchar(2),
  target_offset    bigint,
  target_pos        char,
  source_index     bigint,
  target_index     bigint
);

CREATE INDEX I_SynsetPointer On SynsetPointer (synset_id);
  
CREATE TABLE SynsetVerbFrame (
  synset_verb_frame_id bigint,
  synset_id            bigint,
  frame_number         bigint,
  word_index           bigint
);

CREATE INDEX I_SynsetVerbFrame On SynsetVerbFrame (synset_id);

CREATE TABLE IndexWordSynset (
  index_word_synset_id bigint,
  index_word_id        bigint,
  synset_id            bigint
);

CREATE INDEX I_IndexWordSynset On IndexWordSynset (index_word_id, synset_id);

CREATE TABLE SynsetException (
  exception_id   bigint,
  pos             char,
  s_exception	  varchar(255),
  lemma           varchar(255)
);

CREATE INDEX I_Exception On SynsetException (pos, s_exception);