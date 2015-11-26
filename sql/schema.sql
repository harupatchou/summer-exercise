--テーブルがあった場合は事前に削除
DROP TABLE members;
DROP TABLE events;
DROP TABLE master_cast;

-- メンバー情報テーブル
CREATE TABLE members
(
  member_id serial NOT NULL,
  name text NOT NULL,
  mail_address text NOT NULL,
  password text NOT NULL,
  CONSTRAINT members_pkc PRIMARY KEY (member_id)
  );
-- イベント情報テーブル
CREATE TABLE events
(
  member_id integer NOT NULL,
  event_id serial NOT NULL,
  name text NOT NULL,
  main_cast text,
  place text NOT NULL,
  price integer NOT NULL,
  year integer NOT NULL,
  month integer NOT NULL,
  day integer NOT NULL,
  CONSTRAINT events_pkc PRIMARY KEY (event_id)
  )
--声優情報テーブル  
CREATE TABLE master_cast
  (
  	cast_id serial NOT NULL,
	cast_name text NOT NULL,
	reading text NOT NULL,
	CONSTRAINT cast_pkc PRIMARY KEY (cast_id)
)