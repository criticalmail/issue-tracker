DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS ISSUE;

CREATE TABLE USER (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY,
  username varchar(50) not null
);

CREATE TABLE ISSUE (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY,
  title varchar(255) not null,
  description varchar(255) not null,
  status varchar(20) not null,
  reporter BIGINT not null,
  assignee BIGINT,
  created DATE NOT NULL,
  completed DATE
);

ALTER TABLE ISSUE
ADD FOREIGN KEY (reporter)
REFERENCES USER(id);

ALTER TABLE ISSUE
ADD FOREIGN KEY (assignee)
REFERENCES USER(id);

INSERT INTO USER (username) VALUES ('j.jones');
INSERT INTO USER (username) VALUES ('h.humble');

INSERT INTO ISSUE (title, description, status, reporter, created)
VALUES (
    'Issue number one',
    'This is issue number one',
    'backlog',
    (select id from USER where USER.username = 'j.jones'),
    (select id from USER where USER.username = 'j.jones'),
    '2017-08-01'
);

INSERT INTO ISSUE (title, description, status, reporter, created)
VALUES (
  'Issue number two',
  'This is issue number two',
  'backlog',
  (select id from USER where USER.username = 'h.humble'),
  (select id from USER where USER.username = 'h.humble'),
  '2017-08-01'
);

INSERT INTO ISSUE (title, description, status, reporter, assignee, created, completed)
VALUES (
  'Issue number three',
  'This is issue number three',
  'done',
  (select id from USER where USER.username = 'h.humble'),
  (select id from USER where USER.username = 'h.humble'),
  '2017-07-22',
  '2017-08-02'
);

INSERT INTO ISSUE (title, description, status, reporter, assignee, created, completed)
VALUES (
  'Issue number four',
  'This is issue number four',
  'done',
  (select id from USER where USER.username = 'h.humble'),
  (select id from USER where USER.username = 'j.jones'),
  '2017-08-02',
  '2017-08-02'
);

INSERT INTO ISSUE (title, description, status, reporter, assignee, created)
VALUES (
  'Issue number five',
  'This is issue number five',
  'in_progress',
  (select id from USER where USER.username = 'j.jones'),
  (select id from USER where USER.username = 'j.jones'),
  '2017-08-02'
);

