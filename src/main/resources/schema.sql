DROP TABLE IF EXISTS article;
CREATE TABLE IF NOT EXISTS article (
  id INTEGER PRIMARY KEY NOT NULL,
  content VARCHAR(8192) NOT NULL
);

DROP TABLE IF EXISTS image;
CREATE TABLE IF NOT EXISTS image (
  id INTEGER NOT NULL AUTO_INCREMENT,
  filename VARCHAR(36) NOT NULL,
  content_type VARCHAR(16) NOT NULL,
  bin BINARY(16777216) NOT NULL,
  PRIMARY KEY (id)
);